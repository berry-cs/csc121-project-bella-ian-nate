import processing.core.PApplet;

/* abstract class representing a generic game object for the game */
public abstract class GameObject {
    protected Posn position;
    protected float size;

    public GameObject(Posn position, float size) {
        this.position = position;
        this.size = size;
    }

    // draw the game object
    public abstract void draw(PApplet p);

    // hitboxes right now, not accurate >:/
    // checks collision based on position and size
    public boolean collidesWith(GameObject other) {
        return position.distanceTo(other.position) <= (this.size / 2 + other.size / 2);
    }


    public Posn getPosition() {
        return position;
    }
}
