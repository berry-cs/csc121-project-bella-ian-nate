import processing.core.*;
import processing.event.*;

/**
 * Main class for the game, initializing the Processing window
 */
public class SpaceGame extends PApplet {
    private IWorld world;

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        world = new SpaceWorld();
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
    public void keyPressed(KeyEvent kev) {
        world = world.keyPressed(kev);
    }

    @Override
    public void keyReleased(KeyEvent kev) {
        world = world.keyReleased(kev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { SpaceGame.class.getName() }, new SpaceGame());
    }
}
