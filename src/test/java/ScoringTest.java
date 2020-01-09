import entity.User;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.geometry.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class ScoringTest {
    private transient Board board;
    private transient String battleship;
    private transient String destroyer;
    private transient String username;
    private transient String password;
    private transient Scoring score;
    private transient Square square;
    private transient Ship testShipB;
    private transient Ship testShipC;
    private transient Ship testShipCr;
    private transient Ship testShipS;
    private transient Ship testShipD;
    private transient Ship testIncorrectShip;

    @BeforeEach
    public void setUpEnvironment() {
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new Board(opponent, handler);
        square = new Square(1,2,board);
        battleship = "BattleShip";
        destroyer = "Destroyer";
        username = "pravesha";
        password = "password";
        score = new Scoring();
        testShipB = new Ship(battleship, 4, true);
        testShipC = new Ship("Carrier", 5, true);
        testShipCr = new Ship("Cruiser", 3, true);
        testShipS = new Ship("Submarine", 2, true);
        testShipD = new Ship("Destroyer", 3, true);
        testIncorrectShip = new Ship("Pravesha", 2, true);

    }

    @Test
    void getShipname() {
        assertEquals(score.getShipname(),null);
    }

    @Test
    void setShipname() {
        score.setShipname(battleship);
        assertEquals(score.getShipname(),battleship);
    }

    @Test
    void getScore() {
        Scoring score = new Scoring();
        assertEquals(score.getScore(), 0);

    }

    @Test
    void setScore() {
        score.setScore(1000);
        assertEquals(score.getScore(), 1000);
    }

    @Test
    void scoreSystemSuccessfulBattleship() {
        square.setShip(testShipB);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipB);
        assertEquals(result, 4000);
    }

    @Test
    void scoreSystemSuccessfulCarrier() {
        square.setShip(testShipC);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipC);
        assertEquals(result, 5000);
    }

    @Test
    void scoreSystemSuccessfulCruiser() {
        square.setShip(testShipCr);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipCr);
        assertEquals(result, 3000);
    }

    @Test
    void scoreSystemSuccessfulSubmarine() {
        square.setShip(testShipS);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipS);
        assertEquals(result, 3500);
    }

    @Test
    void scoreSystemSuccessfulDestroyer() {
        square.setShip(testShipD);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipD);
        assertEquals(result, 2000);
    }

}