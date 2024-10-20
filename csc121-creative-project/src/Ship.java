import processing.event.KeyEvent;
import processing.core.PApplet;

/* represents a ship in the game, controlled by player  */
public class Ship extends GameObject {
    private boolean moveUp, moveDown, moveLeft, moveRight;
    private final float moveSpeed = 5;

    public Ship(Posn position, float size) {
        super(position, size);
        this.moveUp = false;
        this.moveDown = false;
        this.moveLeft = false;
        this.moveRight = false;
    }

    @Override
    // draws ship as a blue circle
    public void draw(PApplet p) {
        p.fill(0, 0, 255);
        p.ellipse(position.getX(), position.getY(), size, size);
    }

    // start movement
    public void handleKeyPressed(KeyEvent kev) {
        switch (kev.getKeyCode()) {
            case PApplet.UP -> moveUp = true;
            case PApplet.DOWN -> moveDown = true;
            case PApplet.LEFT -> moveLeft = true;
            case PApplet.RIGHT -> moveRight = true;
        }
    }

    // stop movement
    public void handleKeyReleased(KeyEvent kev) {
        switch (kev.getKeyCode()) {
            case PApplet.UP -> moveUp = false;
            case PApplet.DOWN -> moveDown = false;
            case PApplet.LEFT -> moveLeft = false;
            case PApplet.RIGHT -> moveRight = false;
        }
    }

    // move ship based on key states from above ^
    public void updateMovement() {
        if (moveUp) position = position.translate(new Posn(0, -moveSpeed));
        if (moveDown) position = position.translate(new Posn(0, moveSpeed));
        if (moveLeft) position = position.translate(new Posn(-moveSpeed, 0));
        if (moveRight) position = position.translate(new Posn(moveSpeed, 0));
    }
}
