import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.util.ArrayList;

/* represents the game world. run this to play  */
public class SpaceWorld implements IWorld {
    Ship ship;
    Alien alien;
    ArrayList<Asteroid> asteroids;
    ArrayList<Star> stars;
    int score;
    boolean gameOver;
    int thrust;
    float backgroundSpeed;
    char fL = 'A';
	char sL = 'A';
	char tL = 'A';
	int aLetter = 0;
	int cursorPos = 241;
    PImage shipImage, alienImage, asteroidImage;

    public SpaceWorld(PApplet app) {
    	this.shipImage = app.loadImage("shipSpaceGame.png");
        this.alienImage = app.loadImage("alienSpaceGame.png");
        this.asteroidImage = app.loadImage("asteroidSpaceGame.png");
    	
        this.ship = new Ship(new Posn(200, 200), 30, shipImage);
        this.alien = new Alien(new Posn((int) (Math.random() * 380), -25), 50, alienImage);
        
        this.asteroids = new ArrayList<>();
        NumAsteroids(3); // CHANGE NUM OF ASTEROIDS IN RESET CALL FOR CONSISTANCY
        
        this.stars = new ArrayList<>();
        NumStars(100);

        this.score = 0;
        this.gameOver = false;
        this.thrust = 10;
        this.backgroundSpeed = 1;
    }


    @Override
    // updates game for each frame
    public IWorld update() {
        if (!gameOver) {
        	// makes sure the background speed is always at least 1 (0.1 increases speed every 10%)
            backgroundSpeed = Math.max(1, thrust * 0.1f); // the 'f' makes it a float
            
            // moves stars in background
            for (Star star : stars) {
            	star.move(backgroundSpeed);
            }
        	
            // collision between ship and alien
            if (ship.collidesWith(alien)) { // was ship.collidesWithRect(alien)
                score += 1 * thrust;
                alien.resetPosition();
            }

            // collisions for each asteroid
            for (Asteroid asteroid : asteroids) {
                if (ship.collidesWith(asteroid)) { // was ship.collidesWithCircle(asteroid)
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
        	for (Star star : stars) {
            	star.draw(p);
            }
        	
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
            p.text("Game Over!", 115, 100);
            p.text("Enter Name:", 60, 150);
            p.text(fL, 240, 150);
            p.text(sL, 270, 150);
            p.text(tL, 300, 150);
            p.text("_", cursorPos, 155);
            p.text("Score: " + score, 135, 200);
            p.text("Press 'r' to reset", 90, 250);
            p.text("Press Enter to save scores", 30, 300);
        }
    }


    @Override
    public IWorld keyPressed(KeyEvent kev) {
        ship.handleKeyPressed(kev);
        if (kev.getKey() == 'w' && thrust < 100) {
            thrust++;
        } else if (kev.getKey() == 's' && thrust > 1) {
            thrust--;
        } else if (kev.getKey() == 'r') {
        	ResetGame();
        } else if (gameOver) {
        	if (kev.getKeyCode() == PApplet.UP) {
        		changeLetterUp();
        	} else if (kev.getKeyCode() == PApplet.DOWN) {
        		changeLetterDown();
        	} else if (kev.getKeyCode() == PApplet.LEFT) {
        		changeLetterIndexDown();
        	} else if (kev.getKeyCode() == PApplet.RIGHT) {
        		changeLetterIndexUp();
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
        this.ship = new Ship(new Posn(200, 200), 30, shipImage);
        this.alien = new Alien(new Posn((int) (Math.random() * 380), -25), 50, alienImage);
        
        NumAsteroids(3);
        NumStars(100);
        
        this.score = 0;
        this.gameOver = false;
        this.thrust = 10;
        this.backgroundSpeed = 1;
    }
    
    public void changeLetterUp() {
    	if (aLetter == 0) {
    		fL++;
    	}
    	if (aLetter == 1) {
    		sL++;
    	}
    	if (aLetter == 2) {
    		tL++;
    	}
    }
    public void changeLetterDown() {
    	if (aLetter == 0) {
    		fL--;
    		
    	}
    	if (aLetter == 1) {
    		sL--;
    	}
    	if (aLetter == 2) {
    		tL--;
    	}
    }
    
    public void changeLetterIndexUp() {
    	if (aLetter >= 0 && aLetter < 2) {
    		aLetter++;
    		cursorPos += 30;
    	}
    }
    
    public void changeLetterIndexDown() {
    	if (aLetter > 0 && aLetter <= 2) {
    		aLetter--;
    		cursorPos -= 30;
    	}
    }
    
    // num of asteroids to appear on screen
    public void NumAsteroids(int count) {
        this.asteroids.clear(); // clear existing asteroids (if they're there)
        for (int i = 0; i < count; i++) {
        	this.asteroids.add(new Asteroid(new Posn((int) (Math.random() * 380), (int) (Math.random() * -150)), 50, asteroidImage));
        }
    }
    
    public void NumStars(int count) {
    	this.stars.clear();
    	for (int i = 0; i < count; i++) {
    		int x = (int) (Math.random() * 400);
    		int y = (int) (Math.random() * 400);
    		stars.add(new Star(x, y));
    	}
    }

}

