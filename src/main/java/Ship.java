public class Ship {
    public int typeShip;
    public boolean orientation = true;
    public int damage;

    /**
     * Constructor for the ship class
     *
     * @param typeShip the type of ship (dependent on squares)
     * @param orientation horizontal or vertical ship
     * @param damage represents how many squares have been hit
     */
    public Ship(int typeShip, boolean orientation, int damage){
        this.typeShip = typeShip;
        this.orientation = orientation;
        this.damage = damage;
    }

    /**
     * Method for when a ship is hit by the opponent
     */
    public void hitShip(){
        damage--;
    }

//    public boolean notHit(){
//        return damage > 0;
//    }

}
