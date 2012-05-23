package ch.bbbaden.space.entities;

import ch.bbbaden.space.Game;
import java.util.Random;
import org.newdawn.slick.GameContainer;

/**
 * @author avi
 */
public class Meteor extends Entity {
    
    private float mSpeed;
    
    public Meteor(float x, float y, int which, float speed) {
        super(x, y, "images/meteor" + which + ".png");
        mSpeed = speed;
    }
    
    public static Meteor createMeteor() {
        Random rand = new Random();
        int x = rand.nextInt(Game.SCREEN_WIDTH - 40) + 20;
        int y = -50;
        int which = rand.nextInt(3);
        float speed = rand.nextInt(50) / 500.f + Game.METEOR_SPEED;
        return new Meteor(x, y, which, speed);
    }

    @Override
    public Type getType() {
        return Entity.Type.Meteor;
    }

    public void update(GameContainer container, int delta) {
        mY += delta * mSpeed;
        
        if (mY > Game.SCREEN_HEIGHT + 50) {
            destroy();
        }
    }
    
}
