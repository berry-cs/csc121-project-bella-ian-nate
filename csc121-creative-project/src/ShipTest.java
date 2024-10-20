import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShipTest {
	Ship ship = new Ship(new Posn(100, 100), 30);

	@Test
	public void testShipCollidesWithAsteroid() {
	    Asteroid asteroid = new Asteroid(new Posn(115, 100), 50);
	    assertEquals(true, ship.collidesWith(asteroid));  // collision
	    
	    Asteroid asteroid2 = new Asteroid(new Posn(200, 100), 50);
	    assertEquals(false, ship.collidesWith(asteroid2));  // no collision
	    
	    // why is this edge case passing when the collision method doesn't actually work??
	    Asteroid asteroid3 = new Asteroid(new Posn(130, 100), 30);
	    assertEquals(true, ship.collidesWith(asteroid3));  // edge collision
	}
	
	@Test
	public void testShipCollidesWithAlien() {
	    Alien alien = new Alien(new Posn(110, 110), 20);
	    assertEquals(true, ship.collidesWith(alien));  // collision
	    
	    Alien alien2 = new Alien(new Posn(150, 150), 20);
	    assertEquals(false, ship.collidesWith(alien2));  // no collision
	}

}
