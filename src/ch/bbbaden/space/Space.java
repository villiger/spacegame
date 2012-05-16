/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.space;

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
    
    Image mBackgroundImage;
    ArrayList<IGameObject> mEntities = new ArrayList<IGameObject>();
    
    public Space() {
        try {
            mBackgroundImage = new Image("images/background0.png");
        } catch (SlickException e) {
            Log.error("Fehler: " + e.getMessage());
        }
    }

    public void update(GameContainer container, int delta) {
        for (IGameObject entity : mEntities) {
            entity.update(container, delta);
        }
    }

    public void render(GameContainer container, Graphics graphics) {
        mBackgroundImage.draw();
        
        for (IGameObject entity : mEntities) {
            entity.render(container, graphics);
        }
    }
    
}
