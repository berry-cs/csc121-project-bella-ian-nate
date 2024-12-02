import processing.core.PApplet;
import processing.core.PImage;

/* represents an alien object in the game */
public class Alien extends GameObject {
	private PImage image;
	private float bufferSpawn = -25;
	private float imgSize = getSize() + 15;

    public Alien(Posn position, int size, PImage image) {
        super(position, size);
        this.image = image;
    }

    @Override
    /* draws the alien */
    public void draw(PApplet p) {
    	if (image != null) {
            p.image(image, getPosition().getX(), getPosition().getY(), imgSize, imgSize);
        } else { // fallback
            p.fill(0, 255, 0);
            p.rect(getPosition().getX(), getPosition().getY(), imgSize, imgSize);
        }
    }

    /* moves alien downwards based on the thrust */
    public void move(int thrust) {
        setPosition(getPosition().translate(new Posn(0, thrust / 10f)));
        if (getPosition().getY() >= 400 + getSize()) {
            resetPosition();
        }
    }

    /* resets the alien's position */
    public void resetPosition() {
		setPosition(new Posn((int) (Math.random() * 380), bufferSpawn));
    }
}
