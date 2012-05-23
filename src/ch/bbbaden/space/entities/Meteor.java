package ch.bbbaden.space.entities;

import ch.bbbaden.space.Game;
import ch.bbbaden.space.Space;
import java.util.Random;
import org.newdawn.slick.GameContainer;

/**
 * @author avi
 */
public class Meteor extends Entity {
    
    private float mSpeed;
    private Space mSpace;

    @Override
    public Type getType() {
        return Type.Meteor;
    }
    
    public Meteor(float x, float y, int which, float speed, Space space) {
        super(x, y, "images/meteor" + which + ".png");
        mSpeed = speed;
        mSpace = space;
    }
    
    public static Meteor createMeteor(Space space) {
        Random rand = new Random();
        int x = rand.nextInt(Game.SCREEN_WIDTH - 40) + 20;
        int y = -50;
        int which = rand.nextInt(3);
        
        // Random speed between METEOR_SPEED and METEOR_SPEED + 0.1f
        float speed = rand.nextInt(100) / 1000.f + Game.METEOR_SPEED;
        
        return new Meteor(x, y, which, speed, space);
    }

    public void update(GameContainer container, int delta) {
        mY += delta * mSpeed;
        
        // Check if Y-position out of screen boundaries
        if (mY > Game.SCREEN_HEIGHT + 50) {
            // Don't use the destroy-method, which would add score points
            mDestroyed = true;
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
        
        // Add score points in relation to the size of the meteor
        mSpace.addScorePoints(100 / mSize);
    }
    
}
