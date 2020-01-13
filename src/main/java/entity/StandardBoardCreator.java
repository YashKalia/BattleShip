package entity;

import entity.ships.BattleShip;

import java.util.List;
import java.util.Random;

public class StandardBoardCreator implements BoardCreator  {

    protected static boolean inProgress = false;
    protected static Board opponentBoard;
    protected static Board playerBoard ;

    /**
     * Verifying whether the application is running.
     * @return Whether the game is in progress.
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * Setting whether the application is running.
     * @param inProgress Whether the game is progress.
     */
    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }


    public void createBord() {
        opponentBoard = new StandardBoard(false,  event -> {
            if (isInProgress()) {
                return;
            }
            Square square = (Square) event.getSource();
            if (square.shooted) {
                return;
            }
            opponentBoard.getGame().setOpponentTurn(!square.shoot());
            if (opponentBoard.ships == 0) {
                System.out.println("YOU WIN");
                //System.exit(0);
            }
            if (opponentBoard.getGame().isOpponentTurn()) {
                opponentBoard.opponentPlayer.enemyShot(playerBoard, new Random());
            }
        });

        playerBoard = new StandardBoard(false, event -> {
            if (inProgress) {
                return;
            }

            List<Ship> ships = playerBoard.makeListWithShips();

            Square square = (Square) event.getSource();

            //TESTING PURPOSES
            Ship battleShipTest = new BattleShip(4, true);
            opponentBoard.placeShip(battleShipTest,9,0, opponentBoard.getBoard());

            int allShipsPlaced = 4;
            if (playerBoard.placeShip(ships.get(allShipsPlaced), square.coordinateX,
                    square.coordinateY, playerBoard.getBoard())) {
                allShipsPlaced--;
                if (allShipsPlaced < 0) {
                    startGame();
                }
            }
        });

    }

    private static void startGame() {
        opponentBoard.opponentPlayer.placeShipsOpponent(opponentBoard.getBoard(), new Random());
        inProgress = true;
    }

}
