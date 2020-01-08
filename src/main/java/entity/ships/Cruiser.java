package entity.ships;

import entity.Ship;

public class Cruiser extends Ship {

    /**
     * Creation of the ship Cruiser.
     *
     * @param typeShip    The number of squares the Cruiser occupies is 3.
     * @param orientation The orientation of the ship.
     */
    public Cruiser(int typeShip, boolean orientation) {
        super(3, orientation);
    }

    /**
     * Getting the name of the ship.
     * @return The name of the ship "Cruiser"
     */
    public String getShipName() {
        return "Cruiser";
    }

    /**
     * Getting the type of the ship.
     * @return The type/size of the ship which is 3
     */
    public int getTypeShip() {
        return 3;
    }


}
