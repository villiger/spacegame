package ch.bbbaden.space;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

/**
 * @author avi
 */
public class Game extends BasicGame {

    public final static int SCREEN_WIDTH = 640;
    public final static int SCREEN_HEIGHT = 480;
    public final static float PLAYER_SPEED = 0.2f;
    public final static float SHOT_SPEED = 0.4f;
    public final static float METEOR_SPEED = 0.05f;
    public final static int SHOT_DELAY = 200;
    public final static int METEOR_DELAY = 500;
    
    Space mSpace;

    public Game() {
        super("Space");
    }
    
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Game());
            app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
            app.setMouseGrabbed(false);
            app.start();
        } catch (SlickException e) {
            Log.error("Fehler: " + e.getMessage());
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        mSpace = new Space();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        mSpace.update(container, delta);
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        mSpace.render(container, graphics);
    }

}
