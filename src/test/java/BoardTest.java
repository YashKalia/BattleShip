import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
class BoardTest {

    private transient Board board;
    private transient String battleship;
    private transient String destroyer;

    @BeforeEach
    public void setUpEnvironment() {
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new Board(opponent, handler);
        battleship = "BattleShip";
        destroyer = "Destroyer";
    }

    @Test
    public void getOpponent() {
        boolean actual = board.isOpponent();
        assertEquals(false, actual);
    }

    @Test
    public void setOpponent() {
        board.setOpponent(true);
        boolean actual = board.isOpponent();
        assertEquals(true, actual);
    }

    @Test
    public void getShips() {
        int actual = board.getShips();
        assertEquals(5, actual);
    }

    @Test
    public void setShips() {
        board.setShips(6);
        int actual = board.getShips();
        assertEquals(6, actual);
    }

    @Test
    public void rowTest() {
        VBox vbox = new VBox();
        board.setRows(vbox);
        VBox actual = board.getRows();
        assertEquals(vbox, actual);
    }

    @Test
    public void pointInsideBoard() {
        Point2D point = new Point2D(4, 4);
        boolean actual = board.inRange(point.getX(), point.getY());
        assertEquals(true, actual);
    }

    @Test
    public void pointNotInsideBoard() {
        Point2D point = new Point2D(11, 4);
        boolean actual = board.inRange(point.getX(), point.getY());
        assertEquals(false, actual);
    }

    @Test
    public void isValidPoint() {
        Point2D point = new Point2D(6, 9);
        boolean actual = board.isValidPoint(point);
        assertEquals(true, actual);
    }

    @Test
    public void isNotValidPoint() {
        Point2D point = new Point2D(11, 4);
        boolean actual = board.isValidPoint(point);
        assertEquals(false, actual);
    }

    @Test
    public void getNeighbours() {
        Square[] expected = new Square[4];
        Square neighbour1 = new Square(3, 4, board);
        expected[0] = neighbour1;
        Square neighbour2 = new Square(5, 4, board);
        expected[1] = neighbour2;
        Square neighbour3 = new Square(4, 3, board);
        expected[2] = neighbour3;
        Square neighbour4 = new Square(4, 5, board);
        expected[3] = neighbour4;
        Point2D point = new Point2D(4, 4);
        Square[] actual = board.getNeighbourSquares(point.getX(), point.getY());

        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i].getCoordinateX(), actual[i].getCoordinateX());
            assertEquals(expected[i].getCoordinateY(), actual[i].getCoordinateY());

        }
    }

    @Test
    public void canPlaceShipVertical_InsideBoard() {
        Ship ship = new Ship(battleship, 4, false);
        boolean actual = board.canPlaceShip(ship, 4, 5);
        assertEquals(true, actual);
    }

    @Test
    public void canNotPlaceShipVertical_OutsideBoard() {
        Ship ship = new Ship(battleship, 4, false);
        boolean actual = board.canPlaceShip(ship, 5, 11);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_OutsideBoardPartially() {
        Ship ship = new Ship(battleship, 4, false);
        boolean actual = board.canPlaceShip(ship, 7, 1);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_AlreadyOccupied() {
        Ship placedShip = new Ship(destroyer, 2, true);
        board.placeShip(placedShip, 4, 1);
        Ship ship = new Ship(battleship, 4, false);
        boolean actual = board.canPlaceShip(ship, 4, 1);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_Neighbour() {
        Ship neighbour = new Ship(destroyer, 2, true);
        board.placeShip(neighbour, 4, 2);
        Ship ship = new Ship(battleship, 4, false);
        boolean actual = board.canPlaceShip(ship, 4, 1);
        assertEquals(false, actual);
    }

    @Test
    public void canPlaceShipHorizontal_InsideBoard() {
        Ship ship = new Ship(battleship, 4, true);
        boolean actual = board.canPlaceShip(ship, 4, 5);
        assertEquals(true, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_OutsideBoard() {
        Ship ship = new Ship(battleship, 4, true);
        boolean actual = board.canPlaceShip(ship, 11, 5);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_OutsideBoardPartially() {
        Ship ship = new Ship(battleship, 4, true);
        boolean actual = board.canPlaceShip(ship, 1, 7);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_AlreadyOccupied() {
        Ship placedShip = new Ship(destroyer, 2, false);
        board.placeShip(placedShip, 4, 1);
        Ship ship = new Ship(battleship, 4, true);
        boolean actual = board.canPlaceShip(ship, 4, 1);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_Neighbour() {
        Ship neighbour = new Ship(destroyer, 2, false);
        board.placeShip(neighbour, 4, 2);
        Ship ship = new Ship(battleship, 4, true);
        boolean actual = board.canPlaceShip(ship, 4, 1);
        assertEquals(false, actual);
    }

    @Test
    public void notPlaceShip_Neighbour() {
        Ship neighbour = new Ship(destroyer, 2, true);
        board.placeShip(neighbour, 1, 1);
        Ship ship = new Ship(battleship, 4, true);
        boolean actual = board.placeShip(ship, 1, 2);
        assertEquals(false, actual);
    }
}