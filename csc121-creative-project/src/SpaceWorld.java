import processing.core.PApplet;
import processing.event.KeyEvent;

public class SpaceWorld implements IWorld {
    private Ship ship;
    private Alien alien;
    private Asteroid asteroid;
    private int score;
    private boolean gameOver;
    private int thrust;

    public SpaceWorld() {
        this.ship = new Ship(new Posn(200, 200), 30);
        this.alien = new Alien(new Posn((int) (Math.random() * 380), -25), 50);
        this.asteroid = new Asteroid(new Posn((int) (Math.random() * 380), -10), 50);
        this.score = 0;
        this.gameOver = false;
        this.thrust = 1;
    }

    @Override
    public IWorld update() {
        if (!gameOver) {
            if (ship.collidesWith(alien)) {
                score += 1 * thrust;
                alien.resetPosition();
            }

            if (ship.collidesWith(asteroid)) {
                gameOver = true;
            }

            asteroid.move(thrust);
            alien.move(thrust);
            ship.updateMovement();
        }
        return this;
    }

    @Override
    public void draw(PApplet p) {
        p.background(0);
        if (!gameOver) {
            ship.draw(p);
            alien.draw(p);
            asteroid.draw(p);
            p.fill(255);
            p.textSize(16);
            p.text("Score: " + score, 10, 20);
            p.text("Thrust: " + thrust + "%", 10, 40);
        } else {
            p.fill(255);
            p.textSize(32);
            p.text("Game Over", 120, 200);
            p.text("Score: " + score, 120, 250);
        }
    }

    @Override
    public IWorld keyPressed(KeyEvent kev) {
        ship.handleKeyPressed(kev);
        if (kev.getKey() == 'w' && thrust < 100) {
            thrust++;
        } else if (kev.getKey() == 's' && thrust > 1) {
            thrust--;
        }
        return this;
    }

    @Override
    public IWorld keyReleased(KeyEvent kev) {
        ship.handleKeyReleased(kev);
        return this;
    }
}

