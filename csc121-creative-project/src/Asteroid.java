import processing.core.PApplet;
import processing.core.PImage;

/* represents an asteroid object in the game */
public class Asteroid extends GameObject {
	private PImage image;
	private float height = 400;
	private float bufferSpawn = -25;
	private float imgSize = getSize() + 15;
    private float noImgSize = getSize() - 90;

    public Asteroid(Posn position, int size, PImage image) {
        super(position, size);
        this.image = image;
    }

    @Override
    // asteroids are red squares right now
    public void draw(PApplet p) {
    	if (image != null) {
            p.image(image, getPosition().getX(), getPosition().getY(), imgSize, imgSize); 
        } else { // fallback
            p.fill(255, 0, 0);
            p.ellipse(getPosition().getX(), getPosition().getY(), noImgSize, noImgSize);
        }
    }

    // moves asteroid downwards based on the thrust
    public void move(int thrust) {
        setPosition(getPosition().translate(new Posn(0, thrust / 10f)));
        
		if (getPosition().getY() >= height) {
            resetPosition();
        }
    }

    public void resetPosition() {
        setPosition(new Posn((int) (Math.random() * 380), bufferSpawn));
    }
}
