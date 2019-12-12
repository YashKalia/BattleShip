
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OpponentPlayerTest {
    private transient OpponentPlayer opponentPlayer;
    private transient Board board;
    private transient HelloWorld helloWorld;

    @BeforeEach
    public void setUpEnvironment() {
        opponentPlayer = new OpponentPlayer();
        boolean opponent = true;
        EventHandler<? super MouseEvent> handler = null;
        board = new Board(opponent, handler);
        helloWorld = new HelloWorld();
    }

    @Test
    public void randomTest() {
        helloWorld.setOpponentTurn(true);
        opponentPlayer.enemyShot(board);
        int randomX = opponentPlayer.getxCoord();
        int randomY = opponentPlayer.getyCoord();
        assertTrue(randomX>=0 || randomX<=10);
        assertTrue(randomY>=0 || randomY<=10);
    }

    @Test
    public void squareAlreadyShot()  {
        Square square = new Square(5, 7, board);
        square.shoot();
        helloWorld.setOpponentTurn(true);
        opponentPlayer.enemyShot(board);
        opponentPlayer.setxCoord(5);
        opponentPlayer.setyCoord(7);

        int newrandomX = opponentPlayer.getxCoord();
        int newrandomY = opponentPlayer.getyCoord();
        System.out.println(opponentPlayer.getxCoord()+" and "+opponentPlayer.getyCoord());
    }

    @Test
    public void squareEmpty()  {
        helloWorld.setOpponentTurn(true);
        opponentPlayer.enemyShot(board);
        opponentPlayer.setxCoord(5);
        opponentPlayer.setyCoord(7);
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void squareShip()  {
        Square square = new Square(5, 7, board);
        Ship ship = new Ship(2, false);
        square.setShip(ship);
        opponentPlayer.enemyShot(board);
        helloWorld.setOpponentTurn(true);
        System.out.println(helloWorld.isOpponentTurn());
        opponentPlayer.setxCoord(5);
        opponentPlayer.setyCoord(7);
        System.out.println(square.shooted);

        assertTrue(helloWorld.isOpponentTurn());
    }

//    @Test
//    public void placeShips() {
//        Ship ship = new Ship(3, false);
//        List<Ship> ships = new ArrayList<>();
//        ships.add(ship);
//        ships.add(ship);
//        ships.add(ship);
//        ships.add(ship);
//        ships.add(ship);
//        opponentPlayer.placeShipsOpponent(board);
//        opponentPlayer.setShips(ships);
//        System.out.println("wahta" + opponentPlayer.allShipsPlaced);
//        assertEquals(opponentPlayer.allShipsPlaced, 4);
//        board.placeShip(ships.get(4), 1, 2);
//        assertEquals(opponentPlayer.allShipsPlaced, 3);
//        opponentPlayer.placeShipsOpponent(board);
//        assertEquals(opponentPlayer.allShipsPlaced, 2);
//        opponentPlayer.placeShipsOpponent(board);
//        assertEquals(opponentPlayer.allShipsPlaced, 1);
//        opponentPlayer.placeShipsOpponent(board);
//        assertEquals(opponentPlayer.allShipsPlaced, 0);
//        opponentPlayer.placeShipsOpponent(board);
//    }

}


