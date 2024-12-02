import processing.event.KeyEvent;
import processing.core.PImage;
import processing.core.PApplet;

/* represents a ship in the game, controlled by player  */
public class Ship extends GameObject {
    private boolean moveUp, moveDown, moveLeft, moveRight;
    private final float moveSpeed = 5;
    public int width = 400;
    public int height = 400;
    PImage image;

    public Ship(Posn position, int size, PImage image) {
        super(position, size);
        this.moveUp = false;
        this.moveDown = false;
        this.moveLeft = false;
        this.moveRight = false;
        this.image = image;
        
    }

    @Override
    // draws ship as a blue circle
    public void draw(PApplet p) {
    	
    	if (image != null) {
            p.image(image, position.x, position.y, size + 45, size + 45);
        } else {
            // fallback
            p.fill(255);
            p.ellipse(position.x, position.y, size + 15, size + 15);
        }
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
		    if (moveUp) position = checkMovement(position, 0, -moveSpeed);
		    if (moveDown) position = checkMovement(position, 0, moveSpeed);
		    if (moveLeft) position = checkMovement(position, -moveSpeed, 0);
		    if (moveRight) position = checkMovement(position, moveSpeed, 0);
    }
    
    public Posn checkMovement(Posn pos, float translateX, float translateY) {
    	Posn newPos = pos.translate(new Posn(translateX, translateY));
    	if (newPos.x <= width - size/2 - 30 && newPos.x >= 0 + size/2 - 15 && newPos.y >= 0 + size/2 - 30 && newPos.y <= height - size/2 - 15) {
    		return newPos;
    	} else {
    		return pos;
    	}
    
    }
}
