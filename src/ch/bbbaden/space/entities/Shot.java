package ch.bbbaden.space.entities;

import ch.bbbaden.space.Game;
import org.newdawn.slick.GameContainer;

/**
 * @author avi
 */
public class Shot extends Entity {
    
    public Shot(float x, float y) {
        super(x, y, "images/shot.png");
    }

    @Override
    public Type getType() {
        return Entity.Type.Shot;
    }

    public void update(GameContainer container, int delta) {
        mY -= delta * Game.SHOT_SPEED;
        
        // check if y position out of screen boundaries
        if (mY < -50) {
            destroy();
        }
    }
    
}
