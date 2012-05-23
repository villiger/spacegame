package ch.bbbaden.space.entities;

import ch.bbbaden.space.Game;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;

/**
 * @author avi
 */
public class Explosion extends Entity {
 	
    private Animation mAnimation;
    private Sound mSound;
    
    /**
     * Explosion is not solid, nothing can collide with it.
     */
    @Override
    public boolean isSolid() {
        return false;
    }
    
    /**
     * Explosion is only and ever destroyed if animation is over.
     */
    @Override
    public boolean isDestroyed() {
        return mAnimation.isStopped();
    }
    
    @Override
    public Type getType() {
        return Type.Explosion;
    }

    public Explosion(float x, float y) {
        super(x, y, "images/explosion.png");
        
        mSize = 32;
        
        SpriteSheet sprite = new SpriteSheet(mImage, mSize * 2, mSize * 2);
        mAnimation = new Animation(sprite, Game.EXPLOSION_FRAME_DURATION);
        mAnimation.setLooping(false);
        mAnimation.stopAt(15);
        
        try {
            mSound = new Sound("sounds/explosion.wav");
            mSound.play();
        } catch (SlickException e) {
            Log.error("Fehler: " + e.getMessage());
        }
    }
    
    public void update(GameContainer container, int delta) {
        
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) {
        int width = mAnimation.getWidth();
        int height = mAnimation.getHeight();
        mAnimation.draw(mX - (height / 2), mY - (width / 2));
    }
    
}
