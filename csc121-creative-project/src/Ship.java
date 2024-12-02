import processing.event.KeyEvent;
import processing.core.PImage;
import processing.core.PApplet;

/* represents a ship in the game, controlled by player  */
public class Ship extends GameObject {
    private boolean moveUp, moveDown, moveLeft, moveRight;
    private final float moveSpeed = 5;
    private int width = 400;
    private int height = 400;
    private float imgSize = getSize() + 45;
    private float noImgSize = getSize() + 15;
    
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
            p.image(image, getPosition().getX(), getPosition().getY(), imgSize, imgSize);
        } else {
            // fallback
            p.fill(255);
            p.ellipse(getPosition().getX(), getPosition().getY(), noImgSize, noImgSize);
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
			if (moveUp) setPosition(checkMovement(getPosition(), 0, -moveSpeed));
		    if (moveDown) setPosition(checkMovement(getPosition(), 0, moveSpeed));
		    if (moveLeft) setPosition(checkMovement(getPosition(), -moveSpeed, 0));
		    if (moveRight) setPosition(checkMovement(getPosition(), moveSpeed, 0));
    }
    
    public Posn checkMovement(Posn pos, float translateX, float translateY) {
    	Posn newPos = pos.translate(new Posn(translateX, translateY));
    	if (newPos.getX() <= width - imgSize && newPos.getX() >= 0 && newPos.getY() >= 0 && newPos.getY() <= height - imgSize) {
    		return newPos;
    	} else {
    		return pos;
    	}
    
    }
}
