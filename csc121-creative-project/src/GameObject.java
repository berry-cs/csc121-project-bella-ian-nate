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
//    public boolean collidesWithCircle(GameObject other) {
//        return position.distanceTo(other.position) <= (this.size / 2 + other.size / 2);
//    }
//    public boolean collidesWithRect(GameObject other) {
//        return new Posn(this.position.x - (this.size), this.position.y - (this.size)).distanceTo(other.position) <= (this.size / 2 + other.size / 2);
//    }
    
    // new hitbox checking, more accurate and generalized
    public boolean collidesWith(GameObject other) {
        float dx = this.position.getX() - other.position.getX();
        float dy = this.position.getY() - other.position.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (this.size / 2 + other.size / 2);
    }


    public Posn getPosition() {
        return position;
    }
}
