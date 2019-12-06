import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Class Board, representing the entire grid of the board.
 * The board consists of squares.
 * The squaresInGrid ArrayList is a list containing all squares in the grid.
 */
public class Board extends Parent {
    private VBox rows = new VBox();
    private boolean opponent = false;
    public int ships = 5;
    public static ArrayList<Square> squaresInGrid = new ArrayList<Square>();
    public int misses = 0;
    Map<String,Point2D> frontShip = new HashMap<String, Point2D>();

    /**
     * Getting the rows of the board.
     *
     * @return The rows of the board.
     */
    public VBox getRows() {
        return rows;
    }

    /**
     * Setting the rows of the boards.
     *
     * @param rows The number of rows.
     */
    public void setRows(VBox rows) {
        this.rows = rows;
    }

    /**
     * Getting the opponent.
     *
     * @return Whether there is an opponent.
     */
    public boolean isOpponent() {
        return opponent;
    }

    /**
     * Setting the opponent.
     *
     * @param opponent Specify whether there is an opponent.
     */
    public void setOpponent(boolean opponent) {
        this.opponent = opponent;
    }

    /**
     * Getting the number of ships.
     *
     * @return The number of ships.
     */
    public int getShips() {
        return ships;
    }

    /**
     * Setting the number of ships.
     *
     * @param ships Amount of ships wanted.
     */
    public void setShips(int ships) {
        this.ships = ships;
    }

    /**
     * Getting the misses during the game.
     * @return the amount of click-events on squares not containing a piece of a ship.
     */
    public int getMisses() {
        return misses;
    }

    /**
     * Setting the misses during the game.
     * @param misses The amount of click-events on squares not containing a piece of a ship.
     */
    public void setMisses(int misses) {
        this.misses = misses;
    }

    /**
     * Get all the locations of the fronts of the ship.
     * @return A map of coordinates containing a pair of the name and front coordinate of the ship.
     */
    public Map<String, Point2D> getFrontShip() {
        return frontShip;
    }

    /**
     * Set all the locations of the fronts of the ship.
     * @param frontShip A map of coordinates containing a pair of the name and
     *                  front coordinate of the ship.
     */
    public void setFrontShip(Map<String, Point2D> frontShip) {
        this.frontShip = frontShip;
    }

    /**
     * Creation of a board.
     *
     * @param opponent Presence of an opponent.
     * @param handler  Click of the mouse.
     */
    public Board(boolean opponent, EventHandler<? super MouseEvent> handler) {
        this.opponent = opponent;
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Square s = new Square(x, y, this);
                squaresInGrid.add(s);
                s.setOnMouseClicked(handler);
                row.getChildren().add(s);
            }

            rows.getChildren().add(row);
        }

        getChildren().add(rows);
    }

    /**
     * The placement of the ships by the user.
     *
     * @param ship Specified ship the user may place.
     * @param x    The X-coordinate where the user has placed the ship.
     * @param y    The Y-coordinate where the user has placed the ship.
     * @return Whether the ship can be placed.
     */
    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.typeShip;

            if (ship.orientation) {
                for (int i = y; i < y + length; i++) {
                    Square square = getSquare(x, i);
                    square.ship = ship;
                    int frontX = square.getCoordinateX();
                    int frontY = square.getCoordinateY();
                    Point2D front = new Point2D(frontX, frontY);
                    frontShip.put(square.ship.getShipName(), front);
                    if (!opponent) {
                        square.setFill(Color.GRAY);
                        square.setStroke(Color.GRAY);
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Square square = getSquare(i, y);
                    square.ship = ship;
                    int frontX = square.getCoordinateX();
                    int frontY = square.getCoordinateY();
                    Point2D front = new Point2D(frontX, frontY);
                    frontShip.put(square.ship.getShipName(), front);
                    if (!opponent) {
                        square.setFill(Color.GRAY);
                        square.setStroke(Color.GRAY);
                    }
                }
            }

            return true;
        }

        return false;
    }

    /**
     * Getting the square of the specified coordinates.
     *
     * @param x The X-Coordinate of the square.
     * @param y The Y-Coordinate of the square.
     * @return The square on the board corresponding to the specified coordinates.
     */
    public Square getSquare(int x, int y) {
        return (Square) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    /**
     * The squares surrounding of a specific square.
     *
     * @param x The X-coordinate of the square.
     * @param y The Y-coordinate of the square.
     * @return List of squares around the specified square.
     */
    protected Square[] getNeighbourSquares(double x, double y) {
        Point2D[] points = new Point2D[]{
            new Point2D(x - 1, y),
            new Point2D(x + 1, y),
            new Point2D(x, y - 1),
            new Point2D(x, y + 1)
        };

        List<Square> neighbors = new ArrayList<Square>();


        for (int p = 0; p < points.length; p++) {
            if (isValidPoint(points[p])) {
                neighbors.add(getSquare((int) points[p].getX(), (int) points[p].getY()));
            }
        }

        return neighbors.toArray(new Square[0]);
    }

    /**
     * Verifying whether ship can be placed at specified position.
     *
     * @param ship The ship to be placed.
     * @param x    The X-coordinate of the specified location.
     * @param y    The Y-coordinate of the specified location.
     * @return Whether the ship can be placed at a certain location.
     */
    protected boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.typeShip;

        if (ship.orientation) {
            for (int i = y; i < y + length; i++) {
                if (!inRange(x, i)) {
                    return false;
                }

                Square square = getSquare(x, i);
                if (square.ship != null) {
                    return false;
                }

                Square[] neighboursX = getNeighbourSquares(x, i);
                for (int neighbourSquare = 0; neighbourSquare < neighboursX.length;
                     neighbourSquare++) {

                    if (neighboursX[neighbourSquare].ship != null) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!inRange(i, y)) {
                    return false;
                }

                Square square = getSquare(i, y);
                if (square.ship != null) {
                    return false;
                }

                Square[] neighboursY = getNeighbourSquares(i, y);
                for (int neighbour = 0; neighbour < neighboursY.length; neighbour++) {

                    if (neighboursY[neighbour].ship != null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Verifying the point is valid.
     *
     * @param point The point, location, the user wants to place the ship.
     * @return Whether the point is valid.
     */
    protected boolean isValidPoint(Point2D point) {
        return inRange(point.getX(), point.getY());
    }

    /**
     * Verifying whether the specified point is lying inside the board.
     *
     * @param x The X-Coordinate of the specified location.
     * @param y The Y-Coordinate of the specified location.
     * @return Whether the specified point is within the boundaries of the board.
     */
    protected boolean inRange(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
}