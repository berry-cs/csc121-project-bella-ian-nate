import processing.core.PApplet;

/* represents an asteroid object in the game */
public class Asteroid extends GameObject {

    public Asteroid(Posn position, float size) {
        super(position, size);
    }

    @Override
    // asteroids are red squares right now
    public void draw(PApplet p) {
        p.fill(255, 0, 0);
        p.rect(position.getX(), position.getY(), size, size);
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
