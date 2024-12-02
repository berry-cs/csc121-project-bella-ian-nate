import processing.core.PApplet;

/* represents a star object in the game */
class Star {
	private int x, y;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* moves the stars */
    public void move(float speed) {
        y += speed;
        if (y > 400) {  // wrap a star around when it goes off screen (remove magic number later)
            y = 0;
            x = (int) (Math.random() * 400);  // reset to a random x position
        }
    }
    /* draws the stars */
    public void draw(PApplet p) {
        p.fill(255);
        p.ellipse(x, y, 3, 3);
    }
}