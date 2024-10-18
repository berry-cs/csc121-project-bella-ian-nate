import processing.core.PApplet;

public class Alien extends GameObject {

    public Alien(Posn position, float size) {
        super(position, size);
    }

    @Override
    public void draw(PApplet p) {
        p.fill(0, 255, 0);
        p.rect(position.getX(), position.getY(), size, size);
    }

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
