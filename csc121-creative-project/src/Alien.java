import processing.core.PApplet;

/* represents an alien object in the game */
public class Alien extends GameObject {

    public Alien(Posn position, float size) {
        super(position, size);
    }

    @Override
    // alien is a green square right now for simplicity
    public void draw(PApplet p) {
        p.fill(0, 255, 0);
        p.rect(position.getX(), position.getY(), size, size);
    }

    // moves alien downwards based on the thrust
    // i think we should cahnge this and the asteroid speed to be random
    // and have thrust only control the ship
    public void move(int thrust) {
        position = position.translate(new Posn(0, thrust / 10f));
        if (position.getY() >= 400 + size) {
            resetPosition();
        }
    }

    public void resetPosition() {
        position = new Posn((int) (Math.random() * 380), -25);
    }
}
