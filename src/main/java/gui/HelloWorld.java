package gui;

import entity.Board;
import entity.Ship;
import entity.Square;
import entity.StandardBoard;
import entity.ships.BattleShip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class HelloWorld extends Application {

    protected static boolean inProgress = false;
    protected static Board opponentBoard;
    protected static Board playerBoard;
    private static int allShipsPlaced = 4;
    public static boolean opponentTurn = false;

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

    /**Set up the game screen.
     *
     * @return a parent object.
     */
    public static Parent setUp() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        opponentBoard = new StandardBoard(true, event -> {
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
            opponentBoard.placeShip(battleShipTest,9,0, opponentBoard);

            if (playerBoard.placeShip(ships.get(allShipsPlaced), square.coordinateX,
                    square.coordinateY, playerBoard)) {
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
        opponentBoard.opponentPlayer.placeShipsOpponent(opponentBoard, new Random());
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

