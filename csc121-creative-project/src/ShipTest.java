import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import processing.core.PImage;

class ShipTest {

	@Test
	public void testShipCollidesWithAsteroid() {
		PImage shipImage = new PImage();
        PImage asteroidImage = new PImage();
        Ship ship = new Ship(new Posn(100, 100), 30, shipImage);
        
	    Asteroid asteroid = new Asteroid(new Posn(115, 100), 50, asteroidImage);
	    assertEquals(true, asteroid.collidesWith(ship));  // collision
	    
	    Asteroid asteroid2 = new Asteroid(new Posn(200, 100), 50, asteroidImage);
	    assertEquals(false, asteroid2.collidesWith(ship));  // no collision
	}
	
	@Test
	public void testShipCollidesWithAlien() {
		PImage shipImage = new PImage();
        PImage alienImage = new PImage();
        Ship ship = new Ship(new Posn(100, 100), 30, shipImage);
        
	    Alien alien = new Alien(new Posn(110, 110), 20, alienImage);
	    assertEquals(true, alien.collidesWith(ship));  // collision
	    
	    Alien alien2 = new Alien(new Posn(150, 150), 20, alienImage);
	    assertEquals(false, alien2.collidesWith(ship));  // no collision
	    
	    
	}

}
