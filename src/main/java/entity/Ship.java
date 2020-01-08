package entity;

/**
 * Class entity.Ship.
 */
public abstract class Ship  {
    public boolean orientation = false;
    private int damage;

    public abstract String getShipName();

    /**
     * Getting the type of the ship.
     * The type is specified by the amount of squares it occupies.
     * @return the amount of squares a ship occupies.
     */
    public abstract int getTypeShip();

    /**
     * Getting the orientation of the ship.
     * False will be vertical, and true will be horizontal.
     * @return the the orientation of the ship.
     */
    public boolean isOrientation() {
        return orientation;
    }

    /**
     * Setting the orientation of the ship.
     * @param orientation Required orientation of the ship.
     */
    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    /**
     * Getting the total amount of shots a ship needs to be destroyed.
     * @return Value of the shots that are needed to destroy a ship.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Setting the total amount of shots a ship needs to be destroyed.
     * @param damage The number of squares a ship occupies.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Creation of a ship.
     * @param typeShip The number of squares the ship occupies.
     * @param orientation The orientation of the ship.
     */
    public Ship(int typeShip, boolean orientation) {
        this.orientation = orientation;
        damage = typeShip;
    }

    /**
     * Decrease the amount of shots that are needed to destroy the whole ship.
     */
    public void shot() {
        damage--;
    }

    /**
     * Verifying whether the whole ship is discovered.
     * @return Whether the ship is destroyed.
     */
    public boolean isNotDestroyed() {
        return damage > 0;
    }
}