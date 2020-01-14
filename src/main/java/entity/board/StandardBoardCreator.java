package entity.board;

import java.util.List;
import java.util.Random;

import entity.Game;
import entity.ships.Ship;
import entity.Square;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class StandardBoardCreator implements BoardCreator {

    protected static boolean inProgress = false;
    protected static Board opponentBoard;
    protected static Board playerBoard;
    protected static Game game;
    private static int allShipsPlaced = 4;

    /**
     * Verifying whether the application is running.
     *
     * @return Whether the game is in progress.
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * Setting whether the application is running.
     *
     * @param inProgress Whether the game is progress.
     */
    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }


    /**
     * Creation of an Standard Board.
     * @return Parent root.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public Parent createBord() {
        opponentBoard = new StandardBoard(true, event -> {
            if (!inProgress) {
                return;
            }
            Square square = (Square) event.getSource();
            if (square.shooted) {
                return;
            }
            game.opponentTurn = !square.shoot();
            if (opponentBoard.ships == 0) {
                System.out.println("YOU WIN");
                //System.exit(0);
            }
            if (game.opponentTurn) {
                opponentBoard.opponentPlayer.enemyShot(playerBoard.getBoard(), new Random());
            }

        });

        playerBoard = new StandardBoard(false, event -> {
            if (inProgress) {
                return;
            }

            List<Ship> ships = playerBoard.makeListWithShips();

            Square square = (Square) event.getSource();

            if (playerBoard.placeShip(ships.get(allShipsPlaced), square.coordinateX,
                    square.coordinateY, playerBoard.getBoard())) {
                allShipsPlaced--;
                if (allShipsPlaced < 0) {
                    startGame();
                }
            }
        });

        VBox player = new VBox(playerBoard);
        VBox opponent = new VBox(opponentBoard);
        player.setAlignment(Pos.CENTER);
        opponent.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(30, player, opponent);
        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        return root;
    }

    private static void startGame() {
        opponentBoard.opponentPlayer.placeShipsOpponent(opponentBoard.getBoard(), new Random());
        inProgress = true;
    }

}
