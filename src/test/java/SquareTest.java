import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareTest {

    private transient Square square;
    private transient Board board;

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
        Ship ship = new Ship(5, false);
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
    public void shootMis() {
        square.shoot();
        boolean actual = square.isShooted();
        assertEquals(true, actual);
    }

    @Test
    public void shootShip() {
        Ship placedShip = new Ship(1, true);
        square.setShip(placedShip);
        square.shoot();
        boolean actual = square.isShooted();
        assertEquals(true, actual);
    }



}