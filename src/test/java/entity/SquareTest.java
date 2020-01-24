package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import entity.board.Board;
import entity.board.StandardBoard;
import entity.ships.Carrier;
import entity.ships.Destroyer;
import entity.ships.Ship;
import entity.ships.Submarine;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareTest {

    private transient Square square;
    private transient Board board;
    private transient Board boardOpponent;
    private transient String carrierName;

    @BeforeEach
    public void setUpEnvironment() {
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new StandardBoard(opponent, handler);
        boardOpponent = new StandardBoard(true, handler);
        square = new Square(3, 4, board);
        carrierName = "Carrier";
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
        Ship ship = new Carrier(carrierName, 5, false);
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
    public void shootMis() {
        square.shoot(square);
        boolean actual = square.isShooted();
        assertEquals(true, actual);
    }

    @Test
    public void shootShip() {
        Ship placedShip = new Destroyer("Destroyer", 1, true);
        square.setShip(placedShip);
        board.setMisses(1);
        square.shoot(square);
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
    public void getSquareLeftFail() {
        square = new Square(0, 1, board);
        Square result = square.getSquareLeft(square);
        Square actual = new Square(11,11, board);
        assertEquals(result.getCoordinateX(), actual.getCoordinateX());
    }

    @Test
    public void getSquareLeftOpponent() {
        square = new Square(3, 4, boardOpponent);
        Square result = square.getSquareLeft(square);
        int part1 = 10 * square.getCoordinateY();
        int part2 = square.getCoordinateX() - 1;
        Square actual = Board.squaresInGrid.get(part1 + part2);
        assertEquals(result.getCoordinateX(), actual.getCoordinateX());
    }

    @Test
    public void getSquareRight() {
        square = new Square(3, 4, board);
        Square result = square.getSquareRight(square);
        Square actual = Board.squaresInGrid.get(44);
        assertEquals(result, actual);
    }

    @Test
    public void getSquareRightFail() {
        square = new Square(9, 1, board);
        Square result = square.getSquareRight(square);
        Square actual = new Square(-1,-1, board);
        assertEquals(result.getCoordinateX(), actual.getCoordinateX());
    }

    @Test
    public void getSquareRightOpponent() {
        square = new Square(3, 4, boardOpponent);
        Square result = square.getSquareRight(square);
        int part1 = 10 * square.getCoordinateY();
        int part2 = square.getCoordinateX() + 1;
        Square actual = Board.squaresInGrid.get(part1 + part2);
        assertEquals(result.getCoordinateX(), actual.getCoordinateX());
    }

    @Test
    public void getSquareUp() {
        square = new Square(3, 4, board);
        Square result = square.getSquareUp(square);
        Square actual = Board.squaresInGrid.get(33);
        assertEquals(result, actual);
    }

    @Test
    public void getSquareUpFail() {
        square = new Square(3, 0, board);
        Square result = square.getSquareUp(square);
        Square actual = new Square(-1,-1, board);
        assertEquals(result.getCoordinateX(), actual.getCoordinateX());
    }

    @Test
    public void getSquareUpOpponent() {
        square = new Square(3, 4, boardOpponent);
        Square result = square.getSquareUp(square);
        int part1 = 10 * (square.getCoordinateY() - 1);
        Square actual = Board.squaresInGrid.get(part1 + square.getCoordinateX());
        assertEquals(result.getCoordinateX(), actual.getCoordinateX());
    }

    @Test
    public void getSquareBelow() {
        square = new Square(3, 4, board);
        Square result = square.getSquareBelow(square);
        Square actual = Board.squaresInGrid.get(53);
        assertEquals(result, actual);
    }

    @Test
    public void getSquareBelowFail() {
        square = new Square(3, 9, board);
        Square result = square.getSquareBelow(square);
        Square actual = new Square(-1,-1, board);
        assertEquals(result.getCoordinateX(), actual.getCoordinateX());
    }

    @Test
    public void getSquareBelowOpponent() {
        square = new Square(3, 4, boardOpponent);
        Square result = square.getSquareBelow(square);
        int part1 = 10 * (square.getCoordinateY() - 1);
        Square actual = Board.squaresInGrid.get(part1 + square.getCoordinateX());
        assertEquals(result.getCoordinateX(), actual.getCoordinateX());
    }

    @Test
    public void setColorSquareLeft() {
        Ship carrier = new Carrier(carrierName, 3, true);
        board.placeShip(carrier,3, 4, board);
        Square leftSquare = new Square(3, 4, board);
        square.setSquareColorLeft(leftSquare);
        assertTrue(square.getSquareUp(leftSquare).isShooted());
        assertTrue(square.getSquareBelow(leftSquare).isShooted());
    }

    @Test
    public void setColorSquareLeftFirstIf() {
        Ship carrier = new Carrier(carrierName, 3, true);
        Square square = new Square(3, 4, board);
        int x = square.getSquareLeft(square).getCoordinateX();
        int y = square.getSquareLeft(square).getCoordinateY();
        board.placeShip(carrier,x, y, board);
        square.setSquareColorLeft(square);
        assertTrue(square.getSquareUp(square).isShooted());
        assertTrue(square.getSquareBelow(square).isShooted());
    }

    @Test
    public void setColorSquareRight() {
        Ship submarine = new Submarine("Submarine", 3, true);
        board.placeShip(submarine,6, 7, board);
        Square leftSquare = new Square(6, 7, board);
        square.setSquareColorRight(leftSquare);
        assertTrue(square.getSquareUp(leftSquare).isShooted());
        assertTrue(square.getSquareBelow(leftSquare).isShooted());
    }

    @Test
    public void setColorSquareRightFirstIf() {
        Ship carrier = new Carrier(carrierName, 3, true);
        Square square = new Square(3, 4, board);
        int x = square.getSquareRight(square).getCoordinateX();
        int y = square.getSquareRight(square).getCoordinateY();
        board.placeShip(carrier,x, y, board);
        square.setSquareColorRight(square);
        assertTrue(square.getSquareUp(square).isShooted());
        assertTrue(square.getSquareBelow(square).isShooted());
    }

    @Test
    public void setColorSquareUp() {
        Ship carrier = new Carrier(carrierName, 3, true);
        board.placeShip(carrier,4, 2, board);
        Square leftSquare = new Square(4, 2, board);
        square.setSquareColorUp(leftSquare);
        assertTrue(square.getSquareLeft(leftSquare).isShooted());
        assertTrue(square.getSquareRight(leftSquare).isShooted());
    }

    @Test
    public void setColorSquareUpFirstIf() {
        Ship carrier = new Carrier(carrierName, 3, true);
        Square square = new Square(3, 4, board);
        int x = square.getSquareUp(square).getCoordinateX();
        int y = square.getSquareUp(square).getCoordinateY();
        board.placeShip(carrier,x, y, board);
        square.setSquareColorUp(square);
        assertTrue(square.getSquareLeft(square).isShooted());
        assertTrue(square.getSquareRight(square).isShooted());
    }

    @Test
    public void setColorSquareBelow() {
        Ship submarine = new Submarine("Submarine", 3, true);
        board.placeShip(submarine,7, 3, board);
        Square leftSquare = new Square(7, 3, board);
        square.setSquareColorBelow(leftSquare);
        assertTrue(square.getSquareLeft(leftSquare).isShooted());
        assertTrue(square.getSquareRight(leftSquare).isShooted());
    }

    @Test
    public void setColorSquareBelowFirstIf() {
        Ship carrier = new Carrier(carrierName, 3, true);
        Square square = new Square(3, 4, board);
        int x = square.getSquareBelow(square).getCoordinateX();
        int y = square.getSquareBelow(square).getCoordinateY();
        board.placeShip(carrier,x, y, board);
        square.setSquareColorBelow(square);
        assertTrue(square.getSquareLeft(square).isShooted());
        assertTrue(square.getSquareRight(square).isShooted());
    }



}