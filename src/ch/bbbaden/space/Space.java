package ch.bbbaden.space;

import ch.bbbaden.space.entities.Explosion;
import ch.bbbaden.space.entities.Entity;
import ch.bbbaden.space.entities.Meteor;
import ch.bbbaden.space.entities.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;
import java.util.ArrayList;

/**
 * @author avi
 */
public final class Space implements IGameObject {
    
    private Image mBackgroundImage;
    private int mTimeSinceLastMeteor;
    private ArrayList<Entity> mEntities = new ArrayList<Entity>();
    
    public Space() {
        try {
            mBackgroundImage = new Image("images/background0.png");
        } catch (SlickException e) {
            Log.error("Fehler: " + e.getMessage());
        }
        
        addEntity(new Player(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT - 50, this));
    }

    public void update(GameContainer container, int delta) {
        // Update all entities
        for (int i = mEntities.size() - 1; i >= 0; i--) {
            Entity entity = mEntities.get(i);
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
            addEntity(Meteor.createMeteor());
        }
    }

    public void render(GameContainer container, Graphics graphics) {
        mBackgroundImage.draw();
        
        // Render all entities
        for (Entity entity : mEntities) {
            entity.render(container, graphics);
        }
    }
    
    public void addEntity(Entity entity) {
        mEntities.add(entity);
    }
    
    public void removeEntity(Entity entity) {
        mEntities.remove(entity);
    }
    
}
