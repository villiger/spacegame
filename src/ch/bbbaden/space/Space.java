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
        for (int i = mEntities.size() - 1; i >= 0; i--) {
            Entity entity = mEntities.get(i);
            entity.update(container, delta);
        }
        
        mTimeSinceLastMeteor += delta;
        if (mTimeSinceLastMeteor > Game.METEOR_DELAY) {
            mTimeSinceLastMeteor = 0;
            addEntity(Meteor.createMeteor());
        }
    }

    public void render(GameContainer container, Graphics graphics) {
        mBackgroundImage.draw();
        
        for (Entity entity : mEntities) {
            entity.render(container, graphics);
        }
    }
    
    public void addEntity(Entity entity) {
        mEntities.add(entity);
    }
    
}
