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

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        
    }

}
