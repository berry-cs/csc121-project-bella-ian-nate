import processing.core.PApplet;
import processing.event.KeyEvent;
import java.util.ArrayList;

/* represents the game world. run this to play  */
public class SpaceWorld implements IWorld {
    Ship ship;
    Alien alien;
    ArrayList<Asteroid> asteroids;
    int score;
    boolean gameOver;
    int thrust;

    public SpaceWorld() {
        this.ship = new Ship(new Posn(200, 200), 30);
        this.alien = new Alien(new Posn((int) (Math.random() * 380), -25), 50);
        
        this.asteroids = new ArrayList<>();
        NumAsteroids(2); // CHANGE NUM OF ASTEROIDS IN RESET CALL FOR CONSISTANCY

        this.score = 0;
        this.gameOver = false;
        this.thrust = 1;
    }


    @Override
    // updates game for each frame
    public IWorld update() {
        if (!gameOver) {
            // collision between ship and alien
            if (ship.collidesWithRect(alien)) {
                score += 1 * thrust;
                alien.resetPosition();
            }

            // collisions for each asteroid
            for (Asteroid asteroid : asteroids) {
                if (ship.collidesWithCircle(asteroid)) {
                    gameOver = true;
                    break;  // end loop if game over
                }
                asteroid.move(thrust);
            }
            
            alien.move(thrust);

            ship.updateMovement();
        }
        return this;
    }


    @Override
    // draws game
    public void draw(PApplet p) {
        p.background(0);
        if (!gameOver) {
            ship.draw(p);
            alien.draw(p);
            
            for (Asteroid asteroid : asteroids) {
                asteroid.draw(p);
            }

            p.fill(255);
            p.textSize(16);
            p.text("Score: " + score, 10, 20);
            p.text("Thrust: " + thrust + "%", 10, 40);
        } else {
            p.fill(255);
            p.textSize(32);
            p.text("Game Over!", 115, 150);
            p.text("Score: " + score, 135, 200);
            p.text("Press 'r' to reset", 90, 250);
        }
    }


    @Override
    public IWorld keyPressed(KeyEvent kev) {
        ship.handleKeyPressed(kev);
        if (kev.getKey() == 'w' && thrust < 100) {
            thrust++;
        } else if (kev.getKey() == 's' && thrust > 1) {
            thrust--;
        } else {
        	if (kev.getKey() == 'r') {
        		ResetGame();
        	}
        }
        return this;
    }

    @Override
    public IWorld keyReleased(KeyEvent kev) {
        ship.handleKeyReleased(kev);
        return this;
    }
    
    // resets the game (fresh start, not the same)
    public void ResetGame() {
        this.ship = new Ship(new Posn(200, 200), 30);
        this.alien = new Alien(new Posn((int) (Math.random() * 380), -25), 50);
        NumAsteroids(2);
        this.score = 0;
        this.gameOver = false;
        this.thrust = 1;
    }
    
    // num of asteroids to appear on screen
    public void NumAsteroids(int count) {
        this.asteroids.clear(); // clear existing asteroids (if they're there)
        for (int i = 0; i < count; i++) {
            this.asteroids.add(new Asteroid(new Posn((int) (Math.random() * 380), -10), 50));
        }
    }

}

