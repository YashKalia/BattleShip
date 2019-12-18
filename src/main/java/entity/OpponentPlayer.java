package entity;

import gui.HelloWorld;

import java.util.List;
import java.util.Random;

public class OpponentPlayer {
    private transient Random random = new Random();

    /**Creates a enemy shot.
     *
     * @param playerBoard the player board.
     */
    public void enemyShot(Board playerBoard) {
        while (HelloWorld.opponentTurn) {

            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Square square = playerBoard.getSquare(x, y);
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

    /**Places opponent ships on board.
     *
     * @param opponentBoard the opp. board.
     */
    @SuppressWarnings("PMD")
    public void placeShipsOpponent(Board opponentBoard) {
        List<Ship> ships = Board.makeListWithShips();

        int allShipsPlaced = 4;
        while (!ships.isEmpty()) {

            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (opponentBoard.placeShip(ships.get(allShipsPlaced), x, y)) {
                allShipsPlaced--;
            }
            if (allShipsPlaced < 0) {
                return;
            }
        }
    }
}


