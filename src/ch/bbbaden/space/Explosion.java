package ch.bbbaden.space;

import ch.bbbaden.space.entities.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;

/**
 * @author avi
 */
public class Explosion extends Animation {
 	
    private float mX;
    private float mY;
    private Sound mSound;

    public Explosion(float x, float y, SpriteSheet sprite, Sound sound) {
        super(sprite, Game.EXPLOSION_FRAME_DURATION);
        mX = x;
        mY = y;
        setLooping(false);
        stopAt(15);
        
        mSound = sound;
        mSound.play();
    }
    
    public static Explosion createExplosion(float x, float y) {
        try {
            SpriteSheet sprite = new SpriteSheet("images/explosion.png", 64, 64);
            Sound sound = new Sound("sounds/explosion.wav");
            return new Explosion(x, y, sprite, sound);
        } catch (SlickException e) {
            Log.error("Fehler: " + e.getMessage());
        }
        
        return null;
    }

    public void render() {
        draw(mX - (getWidth()/2), mY - (getHeight()/2));
    }
}
