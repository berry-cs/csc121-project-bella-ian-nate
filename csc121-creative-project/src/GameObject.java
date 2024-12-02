import processing.core.PApplet;

/* abstract class representing a generic game object for the game */
public abstract class GameObject {
	private Posn position;
	private float size;

    public GameObject(Posn position, float size) {
        this.position = position;
        this.setSize(size);
    }

    /* draw the game object */
    public abstract void draw(PApplet p);
    
    /* new hitbox checking, more accurate and generalized */
    // checks collision based on position and size
    public boolean collidesWith(GameObject other) {
        float dx = this.position.getX() - other.position.getX();
        float dy = this.position.getY() - other.position.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (this.getSize() / 2 + other.getSize() / 2);
    }

    /* get the object's position */
    public Posn getPosition() {
        return position;
    }

    /* set the object's potsition to something */
    public void setPosition(Posn newPos) {
        this.position = newPos;
    }
    
    /* get the object's size */
	public float getSize() {
		return size;
	}

	/* set the object's size */
	public void setSize(float size) {
		this.size = size;
	}
}
