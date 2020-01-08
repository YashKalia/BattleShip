package entity.ships;

import entity.Ship;

public class Carrier extends Ship {

    /**
     * Creation of the ship Carrier.
     *
     * @param typeShip    The number of squares the Carrier occupies is 5.
     * @param orientation The orientation of the ship.
     */
    public Carrier(int typeShip, boolean orientation) {
        super(5, orientation);
    }

    /**
     * Getting the name of the ship.
     * @return The name of the ship "Carrier"
     */
    public String getShipName() {
        return "Carrier";
    }

    /**
     * Getting the type of the ship.
     * @return The type/size of the ship which is 5
     */
    public int getTypeShip() {
        return 5;
    }


}
