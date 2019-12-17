import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class HelloWorld extends Application {

    protected static boolean inProgress = false;
    protected static Board opponentBoard;
    protected static Board playerBoard;
    private int allShipsPlaced = 4;
    protected static boolean opponentTurn = false;

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

    /**
     * Getting the board of the opponent.
     * @return The board of the opponent.
     */
    public Board getOpponentBoard() {
        return opponentBoard;
    }

    /**
     * Setting the board of the opponent.
     * @param opponentBoard Specifying the board of the opponent.
     */
    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    /**
     * Getting the board of the player.
     * @return The board of the player.
     */
    public Board getPlayerBoard() {
        return playerBoard;
    }

    /**
     * Setting the board of the player.
     * @param playerBoard Specifying the board of the player.
     */
    public void setPlayerBoard(Board playerBoard) {
        this.playerBoard = playerBoard;
    }

    /**
     * Getting the amount of ships that still need to be placed.
     * @return The amount of the ships the player still needs to place.
     */
    public int getAllShipsPlaced() {
        return allShipsPlaced;
    }

    /**
     * Setting the amount of ships the player must place.
     * @param allShipsPlaced The required amount of ships that must be placed.
     */
    public void setAllShipsPlaced(int allShipsPlaced) {
        this.allShipsPlaced = allShipsPlaced;
    }

    /**
     * Getting whose turn it is.
     * @return Whether it is the turn of the opponent.
     */
    public boolean isOpponentTurn() {
        return opponentTurn;
    }

    /**
     * Setting whose turn it is.
     * @param opponentTurn Whether it is the turn of the opponent.
     */
    public void setOpponentTurn(boolean opponentTurn) {
        this.opponentTurn = opponentTurn;
    }

    private Parent setUp() {
        BorderPane root = new BorderPane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        opponentBoard = new Board(true, event -> {
            if (!inProgress) {
                return;
            }

            Square square = (Square) event.getSource();
            if (square.shooted) {
                return;
            }

            opponentTurn = !square.shoot();

            if (opponentBoard.ships == 0) {
                System.out.println("YOU WIN");
                //System.exit(0);
            }

            if (opponentTurn) {
                opponentBoard.opponentPlayer.enemyShot(playerBoard);
            }

        });

        playerBoard = new Board(false, event -> {
            if (inProgress) {
                return;
            }

            List<Ship> ships = playerBoard.makeListWithShips();

            Square square = (Square) event.getSource();

            //TESTING PURPOSES
            Ship battleShipTest = new Ship("BattleShip", 4, true);
            opponentBoard.placeShip(battleShipTest,9,0);

            if (playerBoard.placeShip(ships.get(allShipsPlaced), square.coordinateX,
                    square.coordinateY)) {
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
        root.setCenter(vbox);

        return root;
    }



    private void startGame() {
        opponentBoard.opponentPlayer.placeShipsOpponent(opponentBoard);
        inProgress = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(setUp());
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}