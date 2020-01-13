package entity;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * Class entity.Board, representing the entire grid of the board.
 * The board consists of squares.
 * The squaresInGrid ArrayList is a list containing all squares in the grid.
 */
public class StandardBoard extends Board  {

    /**
     * Creation of a board.
     *
     * @param opponent Presence of an opponent.
     * @param handler  Click of the mouse.
     */
    public StandardBoard(boolean opponent,EventHandler<? super MouseEvent> handler) {
        super(opponent, handler);
    }

    /**
     * Verifying the point is valid.
     *
     * @param point The point, location, the user wants to place the ship.
     * @return Whether the point is valid.
     */
    public boolean isValidPoint(Point2D point, Board board) {
        return inRange((int)point.getX(), (int)point.getY(), board);
    }

    /**
     * Verifying whether the specified point is lying inside the board.
     *
     * @param x The X-Coordinate of the specified location.
     * @param y The Y-Coordinate of the specified location.
     * @return Whether the specified point is within the boundaries of the board.
     */
    public boolean inRange(int x, int y, Board board) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

}