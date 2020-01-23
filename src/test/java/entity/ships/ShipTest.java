package entity.ships;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

    private transient Ship destroyer;
    private transient Ship battleship;
    private transient Ship carrier;


    @BeforeEach
    public void setUpEnvironment() {
        destroyer = new Destroyer("Destroyer", 2, true);
        battleship = new BattleShip("BattleShip", 4, true);
        carrier = new Carrier("Carrier", 5, false);

    }


    @Test
    public void getshipName() {
        String actual = destroyer.getShipName();
        assertEquals("Destroyer", actual);
    }

    @Test
    public void getShipType() {
        int actual = destroyer.getTypeShip();
        assertEquals(2, actual);
    }

    @Test
    public void getOrientation() {
        boolean actual = destroyer.isOrientation();
        assertEquals(true, actual);
    }

    @Test
    public void setOrientation() {
        destroyer.setOrientation(false);
        boolean actual = destroyer.isOrientation();
        assertEquals(false, actual);
    }

    @Test
    public void getDamage() {
        int actual = destroyer.getDamage();
        assertEquals(destroyer.getTypeShip(), actual);
    }

    @Test
    public void setDamage() {
        destroyer.setDamage(5);
        int actual = destroyer.getDamage();
        assertEquals(5, actual);
    }

    @Test
    public void shot() {
        destroyer.shot();
        int actual = destroyer.getDamage();
        assertEquals(1, actual);
    }

    @Test
    public void shipDestroyed() {
        destroyer.setDamage(1);
        assertEquals(true, destroyer.isNotDestroyed());
    }

    @Test
    public void shipIsDestroyed() {
        destroyer.setDamage(0);
        assertEquals(false, destroyer.isNotDestroyed());
    }





}