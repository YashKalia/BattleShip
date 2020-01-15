package entity.ships;

public class Destroyer extends Ship {

    /**
     * Creation of the ship Destroyer.
     *
     * @param typeShip    The number of squares the Destroyer occupies is 2.
     * @param orientation The orientation of the ship.
     */
    public Destroyer(String shipName, int typeShip, boolean orientation) {
        super("Destroyer", 2, orientation);
    }

    /**
     * Getting the name of the ship.
     * @return The name of the ship "Destroyer"
     */
    public String getShipName() {
        return "Destroyer";
    }

    /**
     * Getting the type of the ship.
     * @return The type/size of the ship which is 2
     */
    public int getTypeShip() {
        return 2;
    }


}
