package entity.ships;

import entity.Ship;

public class Submarine extends Ship {

    /**
     * Creation of the ship Destroyer.
     *
     * @param typeShip    The number of squares the Submarine occupies is 3.
     * @param orientation The orientation of the ship.
     */
    public Submarine(int typeShip, boolean orientation) {
        super(3, orientation);
    }

    /**
     * Getting the name of the ship.
     * @return The name of the ship "Submarine"
     */
    public String getShipName() {
        return "Submarine";
    }

    /**
     * Getting the type of the ship.
     * @return The type/size of the ship which is 3
     */
    public int getTypeShip() {
        return 3;
    }


}
