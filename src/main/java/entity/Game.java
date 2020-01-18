package entity;

import entity.board.Board;

public class Game {
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
}
