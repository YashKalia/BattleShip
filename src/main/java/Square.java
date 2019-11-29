import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class Square, representing a single block of the board.
 */
public class Square extends Rectangle {
    public int coordinateX;
    public int coordinateY;
    public Ship ship = null;
    public boolean shooted = false;
    private Board board;

    /**
     * Getting the X coordinate of the square.
     * @return The x coordinate of the square.
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Setting the X coordinate of the square.
     * @param coordinateX The specified X coordinate.
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Getting the Y coordinate of the square.
     * @return The Y coordinate of the square.
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * Setting the Y coordinate of the square.
     * @param coordinateY The specified Y coordinate.
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * Getting of the ship.
     * @return specified ship.
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Setting the ship.
     * @param ship Specified ship to place.
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Verifying whether block a click event has already occurred on block.
     * @return Whether square is already touched.
     */
    public boolean isShooted() {
        return shooted;
    }

    /**
     * Setting whether block is already touched.
     * @param shooted The current status of the block.
     */
    public void setShooted(boolean shooted) {
        this.shooted = shooted;
    }

    /**
     * Getting the board.
     * @return The board.
     */
    public Board getBoard() {
        return board;
    }



    /**
     * Setting the board.
     * @param board The board of the game.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Constructor of square class.
     * @param x X-Coordinate of square.
     * @param y Y-Coordinate of square.
     * @param board The board of the game.
     */
    public Square(int x, int y, Board board) {
        super(30, 30);
        this.coordinateX = x;
        this.coordinateY = y;
        this.board = board;
        setFill(Color.DARKCYAN);
        setStroke(Color.LIGHTCYAN);
    }

    /**
     * Set the color of a square, not containing a ship, that has been shot.
     * Set the color of a square, containing a ship, that has been shot.
     * @return Whether square is shot.
     */
    public boolean shoot() {
        shooted = true;
        setFill(Color.BLACK);

        if (ship != null) {
            ship.shot();
            setFill(Color.RED);
            if (!ship.isNotDestroyed()) {
                board.ships--;
            }
            return true;
        }

        return false;
    }
}