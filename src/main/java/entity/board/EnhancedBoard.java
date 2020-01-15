package entity.board;

import entity.Square;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EnhancedBoard extends Board {

    /**
     * Creation of a board.
     *
     * @param opponent Presence of an opponent.
     * @param handler  Click of the mouse.
     */
    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public EnhancedBoard(boolean opponent, EventHandler<? super MouseEvent> handler) {
        super(opponent,handler);
    }

    /**
     * Verifying the point is valid.
     *
     * @param point The point, location, the user wants to place the ship.
     * @return Whether the point is valid.
     */
    public boolean isValidPoint(Point2D point, Board board) {
        return inRange((int) point.getX(), (int) point.getY(), board);
    }

    /**
     * Verifying whether the specified point is lying inside the board.
     *
     * @param x The X-Coordinate of the specified location.
     * @param y The Y-Coordinate of the specified location.
     * @return Whether the specified point is within the boundaries of the board.
     */
    public boolean inRange(int x, int y, Board board) {
        return x >= 0 && x < 10 && y >= 0 && y < 10 && !faded(board.getBoard().getSquare(x, y));
    }

    /**
     * Removing squares to create new shape.
     * @param board Board with new shape.
     */
    public void reshape(Board board) {
        board.getBoard().getSquare(3, 0).setFill(Color.WHITE);
        board.getBoard().getSquare(3, 1).setFill(Color.WHITE);
        board.getBoard().getSquare(4, 0).setFill(Color.WHITE);
        board.getBoard().getSquare(4, 1).setFill(Color.WHITE);
        board.getBoard().getSquare(4, 2).setFill(Color.WHITE);
        board.getBoard().getSquare(5, 0).setFill(Color.WHITE);
        board.getBoard().getSquare(5, 1).setFill(Color.WHITE);
        board.getBoard().getSquare(5, 2).setFill(Color.WHITE);
        board.getBoard().getSquare(6, 0).setFill(Color.WHITE);
        board.getBoard().getSquare(6, 1).setFill(Color.WHITE);
    }

    /**
     * Verifying whether square is part of the board.
     * @param square The square in quest.
     * @return Whether the square is part of the board.
     */
    public boolean faded(Square square) {
        if (square.getFill() == Color.WHITE) {
            return true;
        }
        return false;
    }
}




