import processing.core.PApplet;
import processing.event.KeyEvent;

public class Space implements IWorld {
	Posn shipPos;
	Posn alienPos;
	Posn asteroidPos;
	float shipSize = 30;
	float objectSize = 50;
	int score = 0;
	boolean gameOver = false;
	boolean moveUp = false;
	boolean moveDown = false;
	boolean moveLeft = false;
	boolean moveRight = false;

	public Space() {
		shipPos = new Posn(200, 200);
		alienPos = new Posn((int) (Math.random() * 380), -25);
		asteroidPos = new Posn((int) (Math.random() * 380), -10);
	}

	@Override
	public IWorld update() {
		if (shipPos.x - shipSize / 4 <= alienPos.x + objectSize && 
				shipPos.x + shipSize / 4 >= alienPos.x && 
				shipPos.y - shipSize / 4 >= alienPos.y &&
				shipPos.y + shipSize / 4 <= alienPos.y + objectSize) {
			score += 1;
			alienPos = new Posn((int) (Math.random() * 380), -25);
		}

		if (shipPos.x - shipSize / 4 <= asteroidPos.x + objectSize && 
				shipPos.x + shipSize / 4 >= asteroidPos.x && 
				shipPos.y - shipSize / 4 >= asteroidPos.y &&
				shipPos.y + shipSize / 4 <= asteroidPos.y + objectSize) {
			gameOver = true;
		}
		if (asteroidPos.y >= 400) {
			asteroidPos = new Posn((int) (Math.random() * 380), -25);
		}
		asteroidPos.y = asteroidPos.y + 5;

		alienPos.y = alienPos.y + 1;

		if (moveUp) {
			shipPos = shipPos.translate(new Posn(0, -5));
		}
		if (moveDown) {
			shipPos = shipPos.translate(new Posn(0, 5));
		}
		if (moveLeft) {
			shipPos = shipPos.translate(new Posn(-5, 0));
		}
		if (moveRight) {
			shipPos = shipPos.translate(new Posn(5, 0));
		}


		return this;
	}

	@Override
	public PApplet draw(PApplet p) {
		p.background(0);
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
			p.fill(255);
			p.textSize(16);
			p.text("Score: " + score, 10, 20);
		} else {
			p.fill(255);
			p.textSize(32);
			p.text("Game Over", 120, 200);
			p.text("Score: " + score, 120, 250);
		}
		return p;
	}

	@Override
	public IWorld keyPressed(KeyEvent kev) {
		switch (kev.getKeyCode()) {
		case PApplet.UP:
			moveUp = true;
			break;
		case PApplet.DOWN:
			moveDown = true;
			break;
		case PApplet.LEFT:
			moveLeft = true;
			break;
		case PApplet.RIGHT:
			moveRight = true;
			break;
		}
		return this;
	}

	/** produce an updated state of this world after a key release event */
	public IWorld keyReleased(KeyEvent kev) { 
		switch (kev.getKeyCode()) {
		case PApplet.UP:
			moveUp = false;
			break;
		case PApplet.DOWN:
			moveDown = false;
			break;
		case PApplet.LEFT:
			moveLeft = false;
			break;
		case PApplet.RIGHT:
			moveRight = false;
			break;

		}
		return this; 
	}
}
