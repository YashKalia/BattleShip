package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoresystem.Scoring;

class SquareTest {

    private transient Square square;
    private Board board;
    private transient Board opponentBoard;

    @BeforeEach
    public void setUpEnvironment() {
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new Board(opponent, handler);
        square = new Square(3, 4, board);
    }

    @Test
    public void getXCoordinate() {
        int actual = square.getCoordinateX();
        assertEquals(3, actual);
    }

    @Test
    public void setXCoordinate() {
        square.setCoordinateX(8);
        int actual = square.getCoordinateX();
        assertEquals(8, actual);
    }

    @Test
    public void getYCoordinate() {
        int actual = square.getCoordinateY();
        assertEquals(4, actual);
    }

    @Test
    public void setYCoordinate() {
        square.setCoordinateY(5);
        int actual = square.getCoordinateY();
        assertEquals(5, actual);
    }

    @Test
    public void getShip() {
        Ship actual = square.getShip();
        assertEquals(null, actual);
    }

    @Test
    public void setShip() {
        Ship ship = new Ship("Carrier", 5, false);
        square.setShip(ship);
        Ship actual = square.getShip();
        assertEquals(ship, actual);
    }

    @Test
    public void getShot() {
        boolean actual = square.isShooted();
        assertEquals(false, actual);
    }

    @Test
    public void setShot() {
        square.setShooted(true);
        boolean actual = square.isShooted();
        assertEquals(true, actual);
    }

    @Test
    public void getBoard() {
        Board actual = square.getBoard();
        assertEquals(board, actual);
    }

    @Test
    public void setBoard() {
        square.setBoard(null);
        Board actual = square.getBoard();
        assertEquals(null, actual);
    }

    @Test
    public void getCoordinates() {
        Point2D point = new Point2D(0, 0);
        Point2D actual = square.getCoordinates();
        assertEquals(point, actual);
    }

    @Test
    public void setCoordinates() {
        Point2D point = new Point2D(3, 7);
        square.setCoordinates(point);
        Point2D actual = square.getCoordinates();
        assertEquals(point, actual);
    }

    @Test
    public void score() {
        Scoring score = new Scoring();
        square.setObjectScore(score);
        Scoring actual = square.getObjectScore();
        assertEquals(score, actual);
    }

    @Test
    public void shootMis() {
        square.shoot();
        boolean actual = square.isShooted();
        assertEquals(true, actual);
    }

    @Test
    public void shootShip() {
        Ship placedShip = new Ship("Destroyer", 1, true);
        square.setShip(placedShip);
        board.setMisses(1);
        square.shoot();
        boolean actual = square.isShooted();
        assertEquals(true, actual);
    }

    @Test
    public void getSquareLeft() {
        square = new Square(3, 4, board);
        Square result = square.getSquareLeft(square);
        Square actual = Board.squaresInGrid.get(42);
        assertEquals(result, actual);
    }

    @Test
    public void getSquareRight() {
        square = new Square(3, 4, board);
        Square result = square.getSquareRight(square);
        Square actual = Board.squaresInGrid.get(44);
        assertEquals(result, actual);
    }

    @Test
    public void getSquareUp() {
        square = new Square(3, 4, board);
        Square result = square.getSquareUp(square);
        Square actual = Board.squaresInGrid.get(33);
        assertEquals(result, actual);
    }

    @Test
    public void getSquareBelow() {
        square = new Square(3, 4, board);
        Square result = square.getSquareBelow(square);
        Square actual = Board.squaresInGrid.get(53);
        assertEquals(result, actual);
    }

    @Test
    public void setColorSquareLeft() {
        Ship carrier = new Ship("Carrier", 3, true);
        board.placeShip(carrier,3, 4);
        Square leftSquare = new Square(3, 4, board);
        square.setSquareColorLeft(leftSquare);
        assertTrue(square.getSquareUp(leftSquare).isShooted());
        assertTrue(square.getSquareBelow(leftSquare).isShooted());
    }

    @Test
    public void setColorSquareRight() {
        Ship submarine = new Ship("Submarine", 3, true);
        board.placeShip(submarine,6, 7);
        Square leftSquare = new Square(6, 7, board);
        square.setSquareColorRight(leftSquare);
        assertTrue(square.getSquareUp(leftSquare).isShooted());
        assertTrue(square.getSquareBelow(leftSquare).isShooted());
    }

    @Test
    public void setColorSquareUp() {
        Ship carrier = new Ship("Carrier", 3, true);
        board.placeShip(carrier,4, 2);
        Square leftSquare = new Square(4, 2, board);
        square.setSquareColorUp(leftSquare);
        assertTrue(square.getSquareLeft(leftSquare).isShooted());
        assertTrue(square.getSquareRight(leftSquare).isShooted());
    }

    @Test
    public void setColorSquareBelow() {
        Ship submarine = new Ship("Submarine", 3, true);
        board.placeShip(submarine,7, 3);
        Square leftSquare = new Square(7, 3, board);
        square.setSquareColorBelow(leftSquare);
        assertTrue(square.getSquareLeft(leftSquare).isShooted());
        assertTrue(square.getSquareRight(leftSquare).isShooted());
    }



}