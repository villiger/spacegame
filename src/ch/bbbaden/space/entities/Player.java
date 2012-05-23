/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.space.entities;

import ch.bbbaden.space.Game;
import ch.bbbaden.space.Space;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 *
 * @author avi
 */
public class Player extends Entity {
    
    protected Space mSpace;
    
    public Player(int x, int y, Space space) {
        super(x, y, "images/player.png");
        mSpace = space;
    }

    @Override
    public Type getType() {
        return Entity.Type.Player;
    }

    public void update(GameContainer container, int delta) {
        Input input = container.getInput();
        
        if (input.isKeyDown(Input.KEY_LEFT)) {
            mX -= delta * Game.PLAYER_SPEED;
        }
        
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            mX += delta * Game.PLAYER_SPEED;
        }
        
        if (input.isKeyDown(Input.KEY_UP)) {
            mY -= delta * Game.PLAYER_SPEED;
        }
        
        if (input.isKeyDown(Input.KEY_DOWN)) {
            mY += delta * Game.PLAYER_SPEED;
        }
    }
    
}
