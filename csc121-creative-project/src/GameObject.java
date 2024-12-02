import processing.core.PApplet;

/* abstract class representing a generic game object for the game */
public abstract class GameObject {
	private Posn position;
	private float size;

    public GameObject(Posn position, float size) {
        this.position = position;
        this.setSize(size);
    }

    // draw the game object
    public abstract void draw(PApplet p);
    // checks collision based on position and size
    
    // new hitbox checking, more accurate and generalized
    public boolean collidesWith(GameObject other) {
        float dx = this.position.getX() - other.position.getX();
        float dy = this.position.getY() - other.position.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (this.getSize() / 2 + other.getSize() / 2);
    }


    public Posn getPosition() {
        return position;
    }

    public void setPosition(Posn newPos) {
        this.position = newPos;
    }
    
	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}
}
