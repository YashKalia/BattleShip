import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

    private transient Ship ship;

    @BeforeEach
    public void setUpEnvironment() {
        ship = new Ship(4, true);
    }

    @Test
    public void getShipType() {
        int actual = ship.getTypeShip();
        assertEquals(4, actual);
    }

    @Test
    public void setShipType() {
        ship.setTypeShip(2);
        int actual = ship.getTypeShip();
        assertEquals(2, actual);
    }

    @Test
    public void getOrientation() {
        boolean actual = ship.isOrientation();
        assertEquals(true, actual);
    }

    @Test
    public void setOrientation() {
        ship.setOrientation(false);
        boolean actual = ship.isOrientation();
        assertEquals(false, actual);
    }

    @Test
    public void getDamage() {
        int actual = ship.getDamage();
        assertEquals(ship.getTypeShip(), actual);
    }

    @Test
    public void setDamage() {
        ship.setDamage(5);
        int actual = ship.getDamage();
        assertEquals(5, actual);
    }

    @Test
    public void shot() {
        ship.shot();
        int actual = ship.getDamage();
        assertEquals(3, actual);
    }

    @Test
    public void shipDestroyed() {
        ship.setDamage(1);
        assertEquals(true, ship.isNotDestroyed());
    }

    @Test
    public void shipIsDestroyed() {
        ship.setDamage(0);
        assertEquals(false, ship.isNotDestroyed());
    }





}