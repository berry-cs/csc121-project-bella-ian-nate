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
	    assertEquals(true, asteroid.collidesWithCircle(ship));  // collision
	    
	    Asteroid asteroid2 = new Asteroid(new Posn(200, 100), 50, asteroidImage);
	    assertEquals(false, asteroid2.collidesWithCircle(ship));  // no collision
	    
	    // why is this edge case passing when the collision method doesn't actually work??
	    Asteroid asteroid3 = new Asteroid(new Posn(130, 100), 30, asteroidImage);
	    assertEquals(true, asteroid3.collidesWithCircle(ship));  // edge collision
	}
	
	@Test
	public void testShipCollidesWithAlien() {
		PImage shipImage = new PImage();
        PImage alienImage = new PImage();
        Ship ship = new Ship(new Posn(100, 100), 30, shipImage);
        
	    Alien alien = new Alien(new Posn(110, 110), 20, alienImage);
	    assertEquals(true, alien.collidesWithRect(ship));  // collision
	    
	    Alien alien2 = new Alien(new Posn(150, 150), 20, alienImage);
	    assertEquals(false, alien2.collidesWithRect(ship));  // no collision
	    
	    
	}

}
