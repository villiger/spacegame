package ch.bbbaden.space.entities;

import ch.bbbaden.space.Game;
import java.util.Random;
import org.newdawn.slick.GameContainer;

/**
 * @author avi
 */
public class Meteor extends Entity {
    
    private float mSpeed;
    
    @Override
    public Type getType() {
        return Type.Meteor;
    }
    
    public Meteor(float x, float y, int which, float speed) {
        super(x, y, "images/meteor" + which + ".png");
        mSpeed = speed;
    }
    
    public static Meteor createMeteor() {
        Random rand = new Random();
        int x = rand.nextInt(Game.SCREEN_WIDTH - 40) + 20;
        int y = -50;
        int which = rand.nextInt(3);
        
        // Random speed between METEOR_SPEED and METEOR_SPEED + 0.1f
        float speed = rand.nextInt(100) / 1000.f + Game.METEOR_SPEED;
        
        return new Meteor(x, y, which, speed);
    }

    public void update(GameContainer container, int delta) {
        mY += delta * mSpeed;
        
        // Check if Y-position out of screen boundaries
        if (mY > Game.SCREEN_HEIGHT + 50) {
            destroy();
        }
    }
    
}
