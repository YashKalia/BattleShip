package entity.ships;

import entity.Ship;

public class BattleShip extends Ship {

    /**
     * Creation of the ship Battleship.
     *
     * @param typeShip    The number of squares the BattleShip occupies is 4.
     * @param orientation The orientation of the ship.
     */
    public BattleShip(int typeShip, boolean orientation) {
        super(4, orientation);
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
