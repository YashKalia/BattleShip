package entity.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import entity.Square;
import entity.ships.BattleShip;
import entity.ships.Destroyer;
import entity.ships.Ship;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
class EnhancedBoardTest {

    private transient Board boardEnhanced;
    private transient String battleship = "BattleShip";
    private transient String destroyer = "Destroyer";


    @BeforeEach
    public void setUpEnvironment() {
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        boardEnhanced = new EnhancedBoard(opponent, handler);
    }


    @Test
    public void pointInsideBoard() {
        Point2D point = new Point2D(4, 4);
        boolean actual = boardEnhanced.inRange((int) point.getX(), (int) point.getY(),
                boardEnhanced);
        assertEquals(true, actual);
    }

    @Test
    public void pointNotInsideBoard() {
        Point2D point = new Point2D(11, 4);
        boolean actual = boardEnhanced.inRange((int) point.getX(), (int) point.getY(),
                boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void isValidPoint() {
        Point2D point = new Point2D(6, 9);
        boolean actual = boardEnhanced.isValidPoint(point, boardEnhanced);
        assertEquals(true, actual);
    }

    @Test
    public void isNotValidPoint() {
        Point2D point = new Point2D(11, 4);
        boolean actual = boardEnhanced.isValidPoint(point, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void getNeighbours() {
        Square[] expected = new Square[4];
        Square neighbour1 = new Square(3, 4, boardEnhanced);
        expected[0] = neighbour1;
        Square neighbour2 = new Square(5, 4, boardEnhanced);
        expected[1] = neighbour2;
        Square neighbour3 = new Square(4, 3, boardEnhanced);
        expected[2] = neighbour3;
        Square neighbour4 = new Square(4, 5, boardEnhanced);
        expected[3] = neighbour4;
        Point2D point = new Point2D(4, 4);
        Square[] actual = boardEnhanced.getNeighbourSquares((int) point.getX(),
                (int) point.getY(), boardEnhanced);

        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i].getCoordinateX(), actual[i].getCoordinateX());
            assertEquals(expected[i].getCoordinateY(), actual[i].getCoordinateY());

        }
    }

    @Test
    public void canPlaceShipVertical_InsideBoard() {
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardEnhanced.canPlaceShip(ship, 4, 5, boardEnhanced);
        assertEquals(true, actual);
    }

    @Test
    public void canNotPlaceShipVertical_OutsideBoard() {
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardEnhanced.canPlaceShip(ship, 5, 11, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_OutsideBoardPartially() {
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardEnhanced.canPlaceShip(ship, 7, 1, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_AlreadyOccupied() {
        Ship placedShip = new Destroyer(destroyer, 2, true);
        boardEnhanced.placeShip(placedShip, 4, 1, boardEnhanced);
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardEnhanced.canPlaceShip(ship, 4, 1, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_Neighbour() {
        Ship neighbour = new Destroyer(destroyer, 2, true);
        boardEnhanced.placeShip(neighbour, 4, 2, boardEnhanced);
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardEnhanced.canPlaceShip(ship, 4, 1, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void canPlaceShipHorizontal_InsideBoard() {
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardEnhanced.canPlaceShip(ship, 4, 5, boardEnhanced);
        assertEquals(true, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_OutsideBoard() {
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardEnhanced.canPlaceShip(ship, 11, 5, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_OutsideBoardPartially() {
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardEnhanced.canPlaceShip(ship, 1, 7, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_AlreadyOccupied() {
        Ship placedShip = new Destroyer(destroyer, 2, false);
        boardEnhanced.placeShip(placedShip, 4, 1, boardEnhanced);
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardEnhanced.canPlaceShip(ship, 4, 1, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_Neighbour() {
        Ship neighbour = new Destroyer(destroyer, 2, false);
        boardEnhanced.placeShip(neighbour, 4, 2, boardEnhanced);
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardEnhanced.canPlaceShip(ship, 4, 1, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void notPlaceShip_Neighbour() {
        Ship neighbour = new Destroyer(destroyer, 2, true);
        boardEnhanced.placeShip(neighbour, 1, 1, boardEnhanced);
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardEnhanced.placeShip(ship, 1, 2, boardEnhanced);
        assertEquals(false, actual);
    }

    @Test
    public void reshapeTest() {
        boardEnhanced.reshape(boardEnhanced);
        assertFalse(boardEnhanced.inRange(3, 0, boardEnhanced));
        assertFalse(boardEnhanced.inRange(3, 1, boardEnhanced));
        assertFalse(boardEnhanced.inRange(4, 0, boardEnhanced));
        assertFalse(boardEnhanced.inRange(4, 1, boardEnhanced));
        assertFalse(boardEnhanced.inRange(4, 2, boardEnhanced));
        assertFalse(boardEnhanced.inRange(5, 0, boardEnhanced));
        assertFalse(boardEnhanced.inRange(5, 1, boardEnhanced));
        assertFalse(boardEnhanced.inRange(5, 2, boardEnhanced));
        assertFalse(boardEnhanced.inRange(6, 0, boardEnhanced));
        assertFalse(boardEnhanced.inRange(6, 1, boardEnhanced));
    }

    /**
     * Mutation testing the method >inRange<.
     * Making use of a ParameterizedTest to test 8 different test cases.
     *
     * @param x      The x coordinate that is being tested
     * @param y      The y coordinate that is being tested
     * @param answer The expected boolean value
     */
    @ParameterizedTest
    @CsvSource({"0,5,true", "-1,4,false", "10,3,false", "9,2,true",
            "2,0,true", "3,-1,false", "4,10,false", "5,9,true"})
    void testPointsInRange(int x, int y, boolean answer) {
        assertEquals(boardEnhanced.inRange(x, y, boardEnhanced), answer);
    }


    /**
     * Mutation & Boundary testing the method >isValid<.
     * Making use of a ParameterizedTest to test 8 different test cases.
     *
     * @param x      The x coordinate that is being tested
     * @param y      The y coordinate that is being tested
     * @param answer The expected boolean value
     */
    @ParameterizedTest
    @CsvSource({"0,5,true", "-1,4,false", "10,3,false", "9,2,true",
            "2,0,true", "3,-1,false", "4,10,false", "5,9,true"})
    void testPointsValid(int x, int y, boolean answer) {
        assertEquals(boardEnhanced.isValidPoint(new Point2D((double) x,
                (double) y), boardEnhanced), answer);
    }
}



