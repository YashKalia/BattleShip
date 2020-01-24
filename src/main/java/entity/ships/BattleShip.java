package entity.ships;

public class BattleShip extends Ship {

    /**
     * Creation of the ship Battleship.
     *
     * @param typeShip    The number of squares the BattleShip occupies is 4.
     * @param orientation The orientation of the ship.
     */
    public BattleShip(String shipName, int typeShip, boolean orientation) {
        super("BattleShip", 4, orientation);
    }

    /**
     * Getting the name of the ship.
     * @return The name of the ship "BattleShip"
     */
    public String getShipName() {
        return "BattleShip";
    }

    /**
     * Getting the type of the ship.
     * @return The type/size of the ship which is 4
     */
    public int getTypeShip() {
        return 4;
    }
}
