import processing.core.PApplet;

public abstract class GameObject {
    protected Posn position;
    protected float size;

    public GameObject(Posn position, float size) {
        this.position = position;
        this.size = size;
    }

    public abstract void draw(PApplet p);

    public boolean collidesWith(GameObject other) {
        return position.distanceTo(other.position) <= (this.size / 2 + other.size / 2);
    }

    public Posn getPosition() {
        return position;
    }
}
