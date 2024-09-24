import processing.core.PApplet;
import processing.event.KeyEvent;

public class Space implements IWorld {
    Posn shipPos;
    Posn alienPos;
    Posn asteroidPos;
    float shipSize = 30;
    float objectSize = 20;
    int score = 0;
    boolean gameOver = false;

    public Space() {
        shipPos = new Posn(200, 200);
        alienPos = new Posn((int) (Math.random() * 380), (int) (Math.random() * 380));
        asteroidPos = new Posn((int) (Math.random() * 380), (int) (Math.random() * 380));
    }

    @Override
    public IWorld update() {
        if (shipPos.distanceTo(alienPos) < shipSize / 2 + objectSize / 2) {
            score += 1;
            alienPos = new Posn((int) (Math.random() * 380), (int) (Math.random() * 380));
        }
        
        if (shipPos.distanceTo(asteroidPos) < shipSize / 2 + objectSize / 2) {
            gameOver = true;
        }

        return this;
    }

    @Override
    public PApplet draw(PApplet p) {
        p.background(255);
        if (!gameOver) {
            // Draw spaceship
            p.fill(0, 0, 255);
            p.ellipse(shipPos.getX(), shipPos.getY(), shipSize, shipSize);

            // Draw alien
            p.fill(0, 255, 0);
            p.rect(alienPos.getX(), alienPos.getY(), objectSize, objectSize);

            // Draw asteroid
            p.fill(255, 0, 0);
            p.rect(asteroidPos.getX(), asteroidPos.getY(), objectSize, objectSize);

            // Draw score
            p.fill(0);
            p.textSize(16);
            p.text("Score: " + score, 10, 20);
        } else {
            p.fill(0);
            p.textSize(32);
            p.text("Game Over", 120, 200);
        }
        return p;
    }

    @Override
    public IWorld keyPressed(KeyEvent kev) {
        switch (kev.getKeyCode()) {
            case PApplet.UP:
                shipPos = shipPos.translate(new Posn(0, -10));
                break;
            case PApplet.DOWN:
                shipPos = shipPos.translate(new Posn(0, 10));
                break;
            case PApplet.LEFT:
                shipPos = shipPos.translate(new Posn(-10, 0));
                break;
            case PApplet.RIGHT:
                shipPos = shipPos.translate(new Posn(10, 0));
                break;
        }
        return this;
    }
}
