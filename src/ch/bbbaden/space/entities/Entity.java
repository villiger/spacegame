package ch.bbbaden.space.entities;

import ch.bbbaden.space.IGameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

/**
 * @author avi
 */
public abstract class Entity implements IGameObject {
    
    public enum Type {
        Player,
        Meteor,
        Shot,
        Explosion
    }
    
    protected float mX;
    protected float mY;
    protected int mSize;
    protected Image mImage;
    protected boolean mDestroyed;
    
    public abstract Type getType();
    
    public boolean isSolid() {
        return true;
    }
    
    public float getX() { return mX; }
    public float getY() { return mY; }
    public int getSize() { return mSize; }
    public boolean isDestroyed() { return mDestroyed; }
    
    public void destroy() {
        mDestroyed = true;
    }
    
    public Entity(float x, float y, String image) {
        mX = x;
        mY = y;
        mDestroyed = false;
        
        try {
            mImage = new Image(image);
            mSize = (mImage.getWidth() + mImage.getHeight()) / 4;
        } catch (SlickException e) {
            Log.error("Fehler: " + e.getMessage());
        }
    }
    
    /**
     * Check if this Entity collides with another Entity.
     * They collide only if the types of both Entities are different
     * and both of them are solid.
     * 
     * @param other The other entity
     * @return boolean true if they collide, otherwise false
     */
    public boolean collides(Entity other) {
        if (getType() != other.getType() && isSolid() && other.isSolid()) {
            float dx = getX() - other.getX();
            float dy = getY() - other.getY();
            float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            float sizes = getSize() + other.getSize();
            if (sizes > distance) {
                return true;
            }
        }
        
        return false;
    }

    public void render(GameContainer container, Graphics graphics) {
        int imageWidth = mImage.getWidth();
        int imageHeight = mImage.getHeight();
        mImage.draw(mX - (imageWidth / 2), mY - (imageHeight / 2));
    }
    
}
