/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.space.entities;

import ch.bbbaden.space.IGameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author avi
 */
public abstract class Entity implements IGameObject {
    
    public enum Type {
        Player,
        Meteor,
        Shot
    }
    
    protected float mX;
    protected float mY;
    protected int mSize;
    protected Image mImage;
    protected boolean mDestroyed;
    
    public abstract Type getType();
    
    public float getX() { return mX; }
    public float getY() { return mY; }
    public int getSize() { return mSize; }
    public boolean isDestroyed() { return mDestroyed; }
    
    public void destroy() {
        mDestroyed = true;
    }

    public void render(GameContainer container, Graphics graphics) {
        int imageWidth = mImage.getWidth();
        int imageHeight = mImage.getHeight();
        mImage.draw(mX - (imageWidth / 2), mY - (imageHeight / 2));
    }
    
}
