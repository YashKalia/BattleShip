public class Ship {
    public int typeShip;
    public boolean orientation = true;
    public int damage;

    public Ship(int typeShip, boolean orientation, int damage){
        this.typeShip = typeShip;
        this.orientation = orientation;
        this.damage = damage;
    }


    public void hitShip(){
        damage--;
    }

//    public boolean notHit(){
//        return damage > 0;
//    }

}
