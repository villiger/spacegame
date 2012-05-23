/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.space;

import ch.bbbaden.space.entities.Entity;
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
        for (Entity entity : mEntities) {
            entity.update(container, delta);
        }
    }

    public void render(GameContainer container, Graphics graphics) {
        mBackgroundImage.draw();
        
        for (Entity entity : mEntities) {
            entity.render(container, graphics);
        }
    }
    
}
