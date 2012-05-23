package ch.bbbaden.space;

import ch.bbbaden.space.entities.Explosion;
import ch.bbbaden.space.entities.Entity;
import ch.bbbaden.space.entities.Meteor;
import ch.bbbaden.space.entities.Player;
import com.sun.tools.javac.comp.Enter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;
import java.util.ArrayList;
import org.newdawn.slick.Color;

/**
 * @author avi
 */
public final class Space implements IGameObject {
    
    private Image mBackgroundImage;
    private Image mGameOverImage;
    private int mTimeSinceLastMeteor;
    private ArrayList<Entity> mEntities = new ArrayList<Entity>();
    private Player mPlayer;
    private int mScore = 0;
    
    public Space() {
        try {
            mBackgroundImage = new Image("images/background0.png");
            mGameOverImage = new Image("images/gameover.png");
        } catch (SlickException e) {
            Log.error("Fehler: " + e.getMessage());
        }
        
        mPlayer = new Player(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT - 50, this);
        addEntity(mPlayer);
    }

    public void update(GameContainer container, int delta) {
        // Update all entities
        for (int i = mEntities.size() - 1; i >= 0; i--) {
            Entity entity = mEntities.get(i);
            
            if (entity.isDestroyed()) {
                removeEntity(entity);
            } else {
                entity.update(container, delta);
            
                // Flag them as destroyed if they collide with another entity
                for (int j = mEntities.size() - 1; j >= 0; j--) {
                   Entity other = mEntities.get(j);
                   if (entity.collides(other)) {
                       entity.destroy();
                       other.destroy();

                       float x = (entity.getX() + other.getX()) / 2.f;
                       float y = (entity.getY() + other.getY()) / 2.f;

                       addEntity(new Explosion(x, y));
                       
                       if (entity.getType() == Entity.Type.Meteor || 
                           entity.getType() == Entity.Type.Meteor) {
                           // Add score
                           addScorePoints(entity.getSize());
                       }
                   }
                }
            }
        }
        
        // Clean up all destroyed entities
        for (int i = mEntities.size() - 1; i >= 0; i--) {
            Entity entity = mEntities.get(i);
            if (entity.isDestroyed()) {
                removeEntity(entity);
            }
        }
        
        // Add new meteors
        mTimeSinceLastMeteor += delta;
        if (mTimeSinceLastMeteor > Game.TIME_BETWEEN_METEORS) {
            mTimeSinceLastMeteor = 0;
            addEntity(Meteor.createMeteor(this));
        }
    }

    public void render(GameContainer container, Graphics graphics) {
        mBackgroundImage.draw();
        
        // Render all entities
        for (Entity entity : mEntities) {
            entity.render(container, graphics);
        }
        
        // If the player is destroyed, we show the game over picture
        if (mPlayer.isDestroyed()) {
            mGameOverImage.draw();
        }
        
        graphics.setColor(Color.magenta);
        graphics.drawString("Score: " + mScore, 9 , 30);
    }
    
    public void addEntity(Entity entity) {
        mEntities.add(entity);
    }
    
    public void removeEntity(Entity entity) {
        mEntities.remove(entity);
    }
    
    public void addScorePoints(int points) {
        mScore += points;
    }
    
}
