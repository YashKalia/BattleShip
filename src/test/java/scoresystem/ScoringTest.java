package scoresystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import entity.Scoring;
import entity.Square;
import entity.board.Board;
import entity.board.StandardBoard;
import entity.ships.BattleShip;
import entity.ships.Carrier;
import entity.ships.Cruiser;
import entity.ships.Destroyer;
import entity.ships.Ship;
import entity.ships.Submarine;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    private transient String carrier;
    private transient String cruiser;

    @BeforeEach
    public void setUpEnvironment() {
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new StandardBoard(opponent, handler);
        square = new Square(1,5,board);
        battleship = "BattleShip";
        destroyer = "Destroyer";
        carrier = "Carrier";
        cruiser = "Cruiser";
        username = "pravesha";
        password = "password";
        score = new Scoring();
        testShipB = new BattleShip(battleship, 4, true);
        testShipC = new Carrier(carrier, 5, true);
        testShipCr = new Cruiser(cruiser, 3, true);
        testShipS = new Submarine("Submarine", 2, true);
        testShipD = new Destroyer(destroyer, 2, true);
        testIncorrectShip = new Destroyer("Pravesha", 2, true);

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
        assertEquals(Scoring.getScore(), 500);

    }

    @Test
    void setScore() {
        score.setScore(1000);
        assertEquals(score.getScore(), 1000);
    }

    @Test
    void scoreSystemSuccessfulBattleship() {
        Scoring.setScore(0);
        square.setShip(testShipB);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipB);
        assertEquals(result, 4000);
    }

    @Test
    void scoreSystemSuccessfulCarrier() {
        Scoring.setScore(0);
        square.setShip(testShipC);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipC);
        assertEquals(result, 5000);
    }

    @Test
    void scoreSystemSuccessfulCruiser() {
        Scoring.setScore(0);
        square.setShip(testShipCr);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipCr);
        assertEquals(result, 3000);
    }

    @Test
    void scoreSystemSuccessfulSubmarine() {
        Scoring.setScore(0);
        square.setShip(testShipS);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipS);
        assertEquals(result, 3500);
    }

    @Test
    void scoreSystemSuccessfulDestroyer() {
        Scoring.setScore(0);
        square.setShip(testShipD);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        int result = score.scoreSystem(coordinate, board, testShipD);
        assertEquals(result, 2000);
    }

    @Test
    void scoreSystemSuccessfulBattleShipMiss() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipB);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(battleship, coordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipB);
        assertEquals(result, 2000);
    }

    @Test
    void scoreSystemSuccessfulBattleShipMissWeak1() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipB);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        Point2D testCoordinate = new Point2D(square.getCoordinateX(), 2);

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(battleship, testCoordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipB);
        assertEquals(result, 1500);
    }

    @Test
    void scoreSystemSuccessfulBattleShipMissWeak2() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipB);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        Point2D testCoordinate = new Point2D(square.getCoordinateX(), 3);

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(battleship, testCoordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipB);
        assertEquals(result, 500);
    }

    @Test
    void scoreSystemSuccessfulCarrierMissWeak1() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipC);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(carrier, coordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipC);
        assertEquals(result, 1500);
    }

    @Test
    void scoreSystemSuccessfulCarrierMiss() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipC);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        Point2D testCoordinate = new Point2D(square.getCoordinateX(), 0);

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(carrier, testCoordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipC);
        assertEquals(result, 2500);
    }

    @Test
    void scoreSystemSuccessfulCarrierMissCoordinate2() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipC);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        Point2D testCoordinate = new Point2D(square.getCoordinateX(), 2);

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(carrier, testCoordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipC);
        assertEquals(result, 2000);
    }

    @Test
    void scoreSystemSuccessfulSubmarineMissWeak1() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipS);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put("Submarine", coordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipS);
        assertEquals(result, 1500);
    }

    @Test
    void scoreSystemSuccessfulSubmarineMissWeak2() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipS);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        Point2D testCoordinate = new Point2D(square.getCoordinateX(), 1);

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put("Submarine", testCoordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipS);
        assertEquals(result, 1750);
    }


    @Test
    void scoreSystemSuccessfulDestroyerMiss() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipD);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put("Destroyer", coordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipD);
        assertEquals(result, 750);
    }

    @Test
    void scoreSystemSuccessfulDestroyerMissWeak1() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipD);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        Point2D testCoordinate = new Point2D(square.getCoordinateX(), 4);

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put("Destroyer", testCoordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipD);
        assertEquals(result, 1000);
    }

    @Test
    void scoreSystemSuccessfulCruiserMiss() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipCr);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(cruiser, coordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipCr);
        assertEquals(result, 500);
    }

    @Test
    void scoreSystemSuccessfulCruiserMissWeak1() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipCr);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        Point2D testCoordinate = new Point2D(square.getCoordinateX(), 4);

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(cruiser, testCoordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipCr);
        assertEquals(result, 1500);
    }

    @Test
    void scoreSystemSuccessfulCruiserMissWeak2() {
        Scoring.setScore(0);
        board.setMisses(2);
        square.setShip(testShipCr);
        Point2D coordinate = new Point2D(square.getCoordinateX(), square.getCoordinateY());
        Point2D testCoordinate = new Point2D(square.getCoordinateX(), 3);

        Map<String,Point2D> frontShip = new HashMap<>();
        frontShip.put(cruiser, testCoordinate);
        board.setFrontShip(frontShip);

        int result = score.scoreSystem(coordinate, board, testShipCr);
        assertEquals(result, 1000);
    }

}