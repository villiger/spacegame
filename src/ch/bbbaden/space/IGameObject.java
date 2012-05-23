package ch.bbbaden.space;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * @author avi
 */
public interface IGameObject {
    void update(GameContainer container, int delta);
    void render(GameContainer container, Graphics graphics);
}
