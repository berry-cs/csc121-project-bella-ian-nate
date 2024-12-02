import processing.core.*;
import processing.event.*;

/**
 * Main class for the game, initializing the Processing window
 */
public class SpaceGame extends PApplet {
    private IWorld world;
    private int width = 400;
    private int height = 400;

    public void settings() {
        size(width, height);
    }

    public void setup() {
        world = new SpaceWorld(this);
    }

    public void draw() {
        world = world.update();
        world.draw(this);
    }

//    @Override
//    public void mousePressed(MouseEvent mev) {
//        world = world.mousePressed(mev);
//    }

    @Override
    /* detects the key pressed by user */
    public void keyPressed(KeyEvent kev) {
        world = world.keyPressed(kev);
    }

    @Override
    /* detects the key released by user */
    public void keyReleased(KeyEvent kev) {
        world = world.keyReleased(kev);
    }

    /* run the game */
    public static void main(String[] args) {
        PApplet.runSketch(new String[] { SpaceGame.class.getName() }, new SpaceGame());
    }
}
