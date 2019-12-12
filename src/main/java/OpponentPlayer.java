import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OpponentPlayer {
    private transient Random random = new Random();
    public int xCoord;
    public int yCoord;
    public List<Ship> ships = new ArrayList<>();
    public int allShipsPlaced;


    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    protected void enemyShot(Board playerBoard) {
        while (HelloWorld.opponentTurn) {

            setxCoord(random.nextInt(10));
            setyCoord(random.nextInt(10));

            Square square = playerBoard.getSquare(xCoord, yCoord);
            if (square.shooted) {
                continue;
            }

            HelloWorld.opponentTurn = square.shootEnemy();
            if (HelloWorld.opponentTurn) {
                enemyShot(playerBoard);
            }

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                // System.exit(0);

            }
        }
    }

    @SuppressWarnings("PMD")
    protected void placeShipsOpponent(Board opponentBoard) {
        setShips(Board.makeListWithShips());
        allShipsPlaced = ships.size()-1;

        while (!ships.isEmpty()) {

            xCoord = random.nextInt(10);
            yCoord = random.nextInt(10);

            if (opponentBoard.placeShip(ships.get(allShipsPlaced), xCoord, yCoord)) {
                allShipsPlaced--;
            }
            if (allShipsPlaced < 0) {
                return;
            }
        }
    }
}