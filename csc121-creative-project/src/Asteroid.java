import processing.core.PApplet;
import processing.core.PImage;

/* represents an asteroid object in the game */
public class Asteroid extends GameObject {
	PImage image;

    public Asteroid(Posn position, int size, PImage image) {
        super(position, size);
        this.image = image;
    }

    @Override
    // asteroids are red squares right now
    public void draw(PApplet p) {
    	if (image != null) {
            p.image(image, position.x, position.y, size, size); 
        } else { // fallback
            p.fill(255, 0, 0);
            p.ellipse(position.x, position.y, size, size);
        }
    }

    // moves asteroid downwards based on the thrust
    // i think we should cahnge this and the alein speed to be random
    // and have thrust only control the ship
    public void move(int thrust) {
        position = position.translate(new Posn(0, thrust / 10f));
        if (position.getY() >= 400) {
            resetPosition();
        }
    }

    public void resetPosition() {
        position = new Posn((int) (Math.random() * 380), -25);
    }
}
