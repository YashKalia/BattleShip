package entity.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import entity.Square;
import entity.ships.BattleShip;
import entity.ships.Destroyer;
import entity.ships.Ship;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
class StandardBoardTest {

    private transient Board boardStandard;
    private transient String battleship = "BattleShip";
    private transient String destroyer = "Destroyer";


    @BeforeEach
    public void setUpEnvironment() {
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        boardStandard = new StandardBoard(opponent, handler);
    }

    @Test
    public void pointInsideBoard() {
        Point2D point = new Point2D(4, 4);
        boolean actual = boardStandard.inRange((int)point.getX(), (int)point.getY(),
                boardStandard);
        assertEquals(true, actual);
    }

    @Test
    public void pointNotInsideBoard() {
        Point2D point = new Point2D(11, 4);
        boolean actual = boardStandard.inRange((int)point.getX(), (int)point.getY(),
                boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void isValidPoint() {
        Point2D point = new Point2D(6, 9);
        boolean actual = boardStandard.isValidPoint(point, boardStandard);
        assertEquals(true, actual);
    }

    @Test
    public void isNotValidPoint() {
        Point2D point = new Point2D(11, 4);
        boolean actual = boardStandard.isValidPoint(point, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void getNeighbours() {
        Square[] expected = new Square[4];
        Square neighbour1 = new Square(3, 4, boardStandard);
        expected[0] = neighbour1;
        Square neighbour2 = new Square(5, 4, boardStandard);
        expected[1] = neighbour2;
        Square neighbour3 = new Square(4, 3, boardStandard);
        expected[2] = neighbour3;
        Square neighbour4 = new Square(4, 5, boardStandard);
        expected[3] = neighbour4;
        Point2D point = new Point2D(4, 4);
        Square[] actual = boardStandard.getNeighbourSquares((int)point.getX(), (int)point.getY(),
                boardStandard);

        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i].getCoordinateX(), actual[i].getCoordinateX());
            assertEquals(expected[i].getCoordinateY(), actual[i].getCoordinateY());

        }
    }

    @Test
    public void canPlaceShipVertical_InsideBoard() {
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardStandard.canPlaceShip(ship, 4, 5, boardStandard);
        assertEquals(true, actual);
    }

    @Test
    public void canNotPlaceShipVertical_OutsideBoard() {
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardStandard.canPlaceShip(ship, 5, 11, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_OutsideBoardPartially() {
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardStandard.canPlaceShip(ship, 7, 1, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_AlreadyOccupied() {
        Ship placedShip = new Destroyer(destroyer, 2, true);
        boardStandard.placeShip(placedShip, 4, 1, boardStandard);
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardStandard.canPlaceShip(ship, 4, 1, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipVertical_Neighbour() {
        Ship neighbour = new Destroyer(destroyer, 2, true);
        boardStandard.placeShip(neighbour, 4, 2, boardStandard);
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardStandard.canPlaceShip(ship, 4, 1, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void canPlaceShipHorizontal_InsideBoard() {
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardStandard.canPlaceShip(ship, 4, 5, boardStandard);
        assertEquals(true, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_OutsideBoard() {
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardStandard.canPlaceShip(ship, 11, 5, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_OutsideBoardPartially() {
        Ship ship = new BattleShip(battleship, 4, false);
        boolean actual = boardStandard.canPlaceShip(ship, 1, 7, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_AlreadyOccupied() {
        Ship placedShip = new Destroyer(destroyer, 2, false);
        boardStandard.placeShip(placedShip, 4, 1, boardStandard);
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardStandard.canPlaceShip(ship, 4, 1, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void canNotPlaceShipHorizontal_Neighbour() {
        Ship neighbour = new Destroyer(destroyer, 2, false);
        boardStandard.placeShip(neighbour, 4, 2, boardStandard);
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardStandard.canPlaceShip(ship, 4, 1, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void notPlaceShip_Neighbour() {
        Ship neighbour = new Destroyer(destroyer, 2, true);
        boardStandard.placeShip(neighbour, 1, 1, boardStandard);
        Ship ship = new BattleShip(battleship, 4, true);
        boolean actual = boardStandard.placeShip(ship, 1, 2, boardStandard);
        assertEquals(false, actual);
    }

    @Test
    public void reshapeTest() {
        boardStandard.reshape(boardStandard);
        assertTrue(boardStandard.inRange(3, 0, boardStandard));
        assertTrue(boardStandard.inRange(6, 1, boardStandard));
        assertTrue(boardStandard.inRange(4, 0, boardStandard));
        assertTrue(boardStandard.inRange(5, 1, boardStandard));
    }

    /**
     * Mutation testing the method >inRange<.
     * Making use of a ParameterizedTest to test 8 different test cases.
     * @param x
     * The x coordinate that is being tested
     * @param y
     * The y coordinate that is being tested
     * @param answer
     * The expected boolean value
     */
    @ParameterizedTest
    @CsvSource({"0,5,true", "-1,4,false", "10,3,false", "9,2,true",
            "2,0,true", "3,-1,false", "4,10,false", "5,9,true"})

    void testPointsInRange(int x, int y, boolean answer) {
        assertEquals(boardStandard.inRange(x, y, boardStandard), answer);
    }

    /**
     * Mutation testing the method >isValid<.
     * Making use of a ParameterizedTest to test 8 different test cases.
     * @param x
     * The x coordinate that is being tested
     * @param y
     * The y coordinate that is being tested
     * @param answer
     * The expected boolean value
     */
    @ParameterizedTest
    @CsvSource({"0,5,true", "-1,4,false", "10,3,false", "9,2,true",
            "2,0,true", "3,-1,false", "4,10,false", "5,9,true"})
    void testPointsValid(int x, int y, boolean answer) {
        assertEquals(boardStandard.isValidPoint(new Point2D((double) x,(double) y),
                boardStandard), answer);
    }

}


