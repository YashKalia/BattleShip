import javafx.geometry.Point2D;
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
    public Point2D coordinates = new Point2D(coordinateX, coordinateY);
    public Scoring objectScore = new Scoring();

    public Point2D getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D coordinates) {
        this.coordinates = coordinates;
    }

    public Scoring getObjectScore() {
        return objectScore;
    }

    public void setObjectScore(Scoring objectScore) {
        this.objectScore = objectScore;
    }



    /**
     * Getting the X coordinate of the square.
     *
     * @return The x coordinate of the square.
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Setting the X coordinate of the square.
     *
     * @param coordinateX The specified X coordinate.
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Getting the Y coordinate of the square.
     *
     * @return The Y coordinate of the square.
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * Setting the Y coordinate of the square.
     *
     * @param coordinateY The specified Y coordinate.
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * Getting of the ship.
     *
     * @return specified ship.
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Setting the ship.
     *
     * @param ship Specified ship to place.
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Verifying whether block a click event has already occurred on block.
     *
     * @return Whether square is already touched.
     */
    public boolean isShooted() {
        return shooted;
    }

    /**
     * Setting whether block is already touched.
     *
     * @param shooted The current status of the block.
     */
    public void setShooted(boolean shooted) {
        this.shooted = shooted;
    }

    /**
     * Getting the board.
     *
     * @return The board.
     */
    public Board getBoard() {
        return board;
    }


    /**
     * Setting the board.
     *
     * @param board The board of the game.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Constructor of square class.
     *
     * @param x     X-Coordinate of square.
     * @param y     Y-Coordinate of square.
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
     * Getting the square left of an given square.
     *
     * @param square square of which you want to receive the square to its left.
     * @return the square which is positioned on the left of the square.
     */
    public Square getSquareLeft(Square square) {
        int x = square.getCoordinateX();
        int y = square.getCoordinateY();
        Square squareNotVisible;

        if (!board.inRange(x, y) || x == 0) {
            squareNotVisible = new Square(11, 11, board);
            return squareNotVisible;
        } else {
            return Board.squaresInGrid.get(10 * y + x - 1);
        }
    }

    /**
     * Getting the square right of an given square.
     *
     * @param square square of which you want to receive the square to its right.
     * @return the square which is positioned on the right of the square.
     */
    public Square getSquareRight(Square square) {
        int x = square.getCoordinateX();
        int y = square.getCoordinateY();
        Square squareNotVisible;

        final int nine = 9; //To avoid pmd error

        if (!board.inRange(x, y) || x == nine) {
            squareNotVisible = new Square(-1, -1, board);
            return squareNotVisible;
        } else {
            return Board.squaresInGrid.get(10 * y + x + 1);
        }
    }

    /**
     * Getting the square above of an given square.
     *
     * @param square square of which you want to receive the square above.
     * @return the square which is positioned above the square.
     */
    public Square getSquareUp(Square square) {
        int x = square.getCoordinateX();
        int y = square.getCoordinateY();
        Square squareNotVisible;

        if (!board.inRange(x, y) || y == 0) {
            squareNotVisible = new Square(-1, -1, board);
            return squareNotVisible;
        } else {
            return Board.squaresInGrid.get(10 * (y - 1) + x);
        }
    }

    /**
     * Getting the square below of an given square.
     *
     * @param square square of which you want to receive the square below.
     * @return the square which is positioned below the square.
     */
    public Square getSquareBelow(Square square) {
        int x = square.getCoordinateX();
        int y = square.getCoordinateY();
        Square squareNotVisible;

        final int nine = 9; //To avoid pmd error

        if (!board.inRange(x, y) || y == nine) {
            squareNotVisible = new Square(-1, -1, board);
            return squareNotVisible;
        } else {
            return Board.squaresInGrid.get(10 * (y + 1) + x);
        }
    }

    /**
     * }
     * Getter for a square.
     *
     * @param x x-coordinate of the square in the board.
     * @param y y-coordinate of the square in the board.
     * @return the square which has those x and y coordinates.
     */
    public Square getSquare(int x, int y) {
        return Board.squaresInGrid.get(10 * y + x);
    }

    /**
     * When a ship is destroyed, it has to get a different color in the grid. To accomplish this,
     * we need to find which squares contain the ship that has been destroyed, we are doing this
     * by going to the left/right/up/down of the last square that was shot before the ship sunk,
     * if we find that there is a ship on that square, we change the color in the setSquareColor
     * methods (see below).Blocks around the ship have to be marked as well, in this case with
     * the color orange, this is what happens if we don't find a ship on the square
     * left/right/up/down.
     *
     * @param square The last square that was shot before the ship sunk.
     */
    public void setDestroyedShipColour(Square square) {
        if (getSquareLeft(square).getShip() != null) {
            setSquareColorLeft((square));
        } else {
            getSquareLeft(square).setFill(Color.ORANGE);
            getSquareLeft(square).shooted = true;
        }

        if (getSquareRight(square).getShip() != null) {
            setSquareColorRight((square));
        } else {
            getSquareRight(square).setFill(Color.ORANGE);
            getSquareRight(square).shooted = true;
        }

        if (getSquareUp(square).getShip() != null) {
            setSquareColorUp((square));
        } else {
            getSquareUp(square).setFill(Color.ORANGE);
            getSquareUp(square).shooted = true;
        }

        if (getSquareBelow(square).getShip() != null) {
            setSquareColorBelow((square));
        } else {
            getSquareBelow(square).setFill(Color.ORANGE);
            getSquareBelow(square).shooted = true;
        }
    }

    /**
     * We apply recursion until we discover a square without a ship part on it. When the square
     * left of the square given to this method does have a part of a ship on it, we rerun the
     * method until we find a square without a ship part on it.
     * We also give the squares up and down the colors orange, which are blocks around the
     * ships that have to be marked as well.
     *
     * @param square Square that has to be colored and for which you want to check squares
     *               to the left.
     */
    public void setSquareColorLeft(Square square) {
        square.setFill((Color.GREEN));
        if (getSquareLeft(square) != null && getSquareLeft(square).getShip() != null) {
            setSquareColorLeft(getSquareLeft(square));
            getSquareUp(square).setFill(Color.ORANGE);
            getSquareBelow(square).setFill(Color.ORANGE);
            getSquareUp(square).shooted = true;
            getSquareBelow(square).shooted = true;
        } else {
            getSquareLeft(square).setFill(Color.ORANGE);
            getSquareUp(square).setFill(Color.ORANGE);
            getSquareBelow(square).setFill(Color.ORANGE);
            getSquareLeft(square).shooted = true;
            getSquareUp(square).shooted = true;
            getSquareBelow(square).shooted = true;
        }
    }

    /**
     * We apply recursion until we discover a square without a ship part on it. When the
     * square right of the square given to this method does have a part of a ship on it, we
     * rerun the method until we find a square without a ship part on it.
     * We also give the squares up and down the colors orange, which are blocks around the
     * ships that have to be marked as well
     *
     * @param square Square that has to be colored and for which you want to check squares
     *               to the right.
     */
    public void setSquareColorRight(Square square) {
        square.setFill((Color.GREEN));
        if (getSquareRight(square) != null && getSquareRight(square).getShip() != null) {
            setSquareColorRight(getSquareRight(square));
            getSquareUp(square).setFill(Color.ORANGE);
            getSquareBelow(square).setFill(Color.ORANGE);
            getSquareUp(square).shooted = true;
            getSquareBelow(square).shooted = true;
        } else {
            getSquareRight(square).setFill(Color.ORANGE);
            getSquareUp(square).setFill(Color.ORANGE);
            getSquareBelow(square).setFill(Color.ORANGE);
            getSquareRight(square).shooted = true;
            getSquareUp(square).shooted = true;
            getSquareBelow(square).shooted = true;
        }
    }

    /**
     * We apply recursion until we discover a square without a ship part on it. When the
     * square above the square given to this method does have a part of a ship on it, we
     * rerun the method until we find a square without a ship part on it.
     * We also give the squares left and right the colors orange, which are blocks around
     * the ships that have to be marked as well
     *
     * @param square Square that has to be colored and for which you want to check
     *               squares above.
     */
    public void setSquareColorUp(Square square) {
        square.setFill((Color.GREEN));
        if (getSquareUp(square) != null && getSquareUp(square).getShip() != null) {
            setSquareColorUp(getSquareUp(square));
            getSquareLeft(square).setFill(Color.ORANGE);
            getSquareRight(square).setFill(Color.ORANGE);
            getSquareLeft(square).shooted = true;
            getSquareRight(square).shooted = true;
        } else {
            getSquareUp(square).setFill(Color.ORANGE);
            getSquareLeft(square).setFill(Color.ORANGE);
            getSquareRight(square).setFill(Color.ORANGE);
            getSquareLeft(square).shooted = true;
            getSquareRight(square).shooted = true;
            getSquareUp(square).shooted = true;
        }
    }

    /**
     * We apply recursion until we discover a square without a ship part on it. When the
     * square below the square given to this method does have a part of a ship on it, we
     * rerun the method until we find a square without a ship
     * part on it.
     * We also give the squares left and right the colors orange, which are blocks around
     * the ships that have to be marked as well
     *
     * @param square Square that has to be colored and for which you want to
     *               check squares below.
     */
    public void setSquareColorBelow(Square square) {
        square.setFill((Color.GREEN));
        if (getSquareBelow(square) != null && getSquareBelow(square).getShip() != null) {
            setSquareColorBelow(getSquareBelow(square));
            getSquareLeft(square).setFill(Color.ORANGE);
            getSquareRight(square).setFill(Color.ORANGE);
            getSquareLeft(square).shooted = true;
            getSquareRight(square).shooted = true;
        } else {
            getSquareBelow(square).setFill(Color.ORANGE);
            getSquareLeft(square).setFill(Color.ORANGE);
            getSquareRight(square).setFill(Color.ORANGE);
            getSquareBelow(square).shooted = true;
            getSquareLeft(square).shooted = true;
            getSquareRight(square).shooted = true;
        }
    }

    /**
     * Set the color of a square, not containing a ship, that has been shot for player.
     * Set the color of a square, containing a ship, that has been shot for player.
     * @return Whether square is shot.
     */
    public boolean shoot() {
        shooted = true;


        if (ship != null) {
            if (ship.getDamage() == ship.getTypeShip()) {
                int achieved= objectScore.scoreSystem(coordinates, board, ship);
                board.totalScore = board.totalScore + achieved;
                System.out.println("Achieved " + achieved);
                System.out.println("Total" + board.totalScore);
            }
            ship.shot();

            setFill(Color.RED);
            if (!ship.isNotDestroyed()) {
                setDestroyedShipColour(this);
                board.ships--;
            }
            return true;
        } else {
            this.setFill(Color.BLACK);
            int miss = board.getMisses() + 1;
            board.setMisses(miss);
            System.out.println(board.misses);
        }

        return false;
    }

    /**
     * Set the color of a square, not containing a ship, that has been shot for computer.
     * Set the color of a square, containing a ship, that has been shot for computer.
     *
     * @return Whether square is shot.
     */
    public boolean shootEnemy() {
        shooted = true;
        this.setFill(Color.BLACK);


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