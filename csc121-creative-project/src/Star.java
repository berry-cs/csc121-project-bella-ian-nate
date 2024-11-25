import processing.core.PApplet;

/* represents a star object in the game */
class Star {
        int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(float speed) {
            y += speed;
            if (y > 400) {  // wrap a star around when it goes off screen (remove magic number later)
                y = 0;
                x = (int) (Math.random() * 400);  // reset to a random x position
            }
        }

        public void draw(PApplet p) {
            p.fill(255);
            p.ellipse(x, y, 3, 3);
        }
    }