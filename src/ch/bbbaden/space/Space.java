/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.space;

import ch.bbbaden.space.entities.Entity;
import ch.bbbaden.space.entities.Meteor;
import ch.bbbaden.space.entities.Player;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

/**
 *
 * @author avi
 */
public class Space implements IGameObject {
    
    private Image mBackgroundImage;
    private ArrayList<Entity> mEntities = new ArrayList<Entity>();
    private ArrayList<Explosion> mExplosions = new ArrayList<Explosion>();
    private int mTimeSinceLastMeteor;
    
    public Space() {
        try {
            mBackgroundImage = new Image("images/background0.png");
        } catch (SlickException e) {
            Log.error("Fehler: " + e.getMessage());
        }
        
        Player player = new Player(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT - 50, this);
        mEntities.add(player);
    }

    public void update(GameContainer container, int delta) {
        // update all entities
        for (int i = mEntities.size() - 1; i >= 0; i--) {
            Entity entity = mEntities.get(i);
            entity.update(container, delta);
            
            // destroy them if they collide
            for (int j = mEntities.size() - 1; j >= 0; j--) {
               Entity other = mEntities.get(j);
               if (entity.collides(other)) {
                   entity.destroy();
                   other.destroy();
                   
                   float x = (entity.getX() + other.getX()) / 2.f;
                   float y = (entity.getY() + other.getY()) / 2.f;
                   
                   mExplosions.add(Explosion.createExplosion(x, y));
               }
            }
        }
        
        // clean up all destroyed entities
        for (int i = mEntities.size() - 1; i >= 0; i--) {
            Entity entity = mEntities.get(i);
            if (entity.isDestroyed()) {
                removeEntity(entity);
            }
        }
        
        // clean up all explosions
        for (int i = mExplosions.size() - 1; i >= 0; i--) {
            Explosion explosion = mExplosions.get(i);
            if (explosion.isStopped()) {
                mExplosions.remove(explosion);
            }
        }
        
        // add new meteors
        mTimeSinceLastMeteor += delta;
        if (mTimeSinceLastMeteor > Game.TIME_BETWEEN_METEORS) {
            mTimeSinceLastMeteor = 0;
            addEntity(Meteor.createMeteor());
        }
    }

    public void render(GameContainer container, Graphics graphics) {
        mBackgroundImage.draw();
        
        // render all entities
        for (Entity entity : mEntities) {
            entity.render(container, graphics);
        }
        
        // render all explosions
        for (Explosion explosion : mExplosions) {
            explosion.render();
        }
    }
    
    public void addEntity(Entity entity) {
        mEntities.add(entity);
    }
    
    public void removeEntity(Entity entity) {
        mEntities.remove(entity);
    }
    
}
