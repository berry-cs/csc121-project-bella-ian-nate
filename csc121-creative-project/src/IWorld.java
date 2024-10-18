import processing.core.PApplet;
import processing.event.KeyEvent;
//import processing.event.MouseEvent;

public interface IWorld {

    IWorld update();
    void draw(PApplet p);
   // IWorld mousePressed(MouseEvent mev);
    IWorld keyPressed(KeyEvent kev);
    IWorld keyReleased(KeyEvent kev);
}

