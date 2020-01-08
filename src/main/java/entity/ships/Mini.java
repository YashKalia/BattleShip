package entity.ships;

import entity.Ship;

/**
 * This type of ship is added for testing purpose.
 */
public class Mini extends Ship {

    /**
     * Creation of the ship Destroyer.
     *
     * @param typeShip    The number of squares the Destroyer occupies is 2.
     * @param orientation The orientation of the ship.
     */
    public Mini(int typeShip, boolean orientation) {
        super(1, orientation);
    }

    /**
     * Getting the name of the ship.
     * @return The name of the ship "Destroyer"
     */
    public String getShipName() {
        return "Mini";
    }

    /**
     * Getting the type of the ship.
     * @return The type/size of the ship which is 2
     */
    public int getTypeShip() {
        return 1;
    }


}
