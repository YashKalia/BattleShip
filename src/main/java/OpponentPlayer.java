import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpponentPlayer {
    private Random random = new Random();

    protected void move() {
        while (HelloWorld.opponentTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Square square = HelloWorld.playerBoard.getSquare(x, y);
            if (square.shooted) {
                continue;
            }

            HelloWorld.opponentTurn = square.shoot1();
            if (HelloWorld.opponentTurn) {
                move();
            }

            if (HelloWorld.playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    protected void placeShipsOpponent() {
        List<Ship> ships = Board.makeListWithShips();

        int allShipsPlaced = 4;

        while (!ships.isEmpty()) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (HelloWorld.opponentBoard.placeShip(ships.get(allShipsPlaced), x, y)) {
                allShipsPlaced--;
            }
            if (allShipsPlaced < 0) {
                return;
            }
        }
    }
}


