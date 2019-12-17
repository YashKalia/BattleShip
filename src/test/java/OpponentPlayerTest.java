import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OpponentPlayerTest {
    private transient OpponentPlayer opponentPlayer;
    private transient Board board;
    private transient HelloWorld helloWorld;
    private  transient Random random;

    @BeforeEach
    public void setUpEnvironment() {
        opponentPlayer = new OpponentPlayer();
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new Board(opponent, handler);
        helloWorld = new HelloWorld();
    }

    @Test
    public void Random() {
        Random random = new Random();
        opponentPlayer.setRandom(random);
        assertEquals(opponentPlayer.getRandom(), random);
    }

    @Test
    public void shottedSquares()  {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        alreadyShot.add(square);
        opponentPlayer.setShotSquares(alreadyShot);
        assertEquals(opponentPlayer.getShotSquares(), alreadyShot);
    }

    @Test
    public void Right()  {
        Square right = new Square(3, 5, board);
        opponentPlayer.setRight(right);
        assertEquals(right, opponentPlayer.getRight());
    }

    @Test
    public void Left()  {
        Square left = new Square(8, 5, board);
        opponentPlayer.setLeft(left);
        assertEquals(left, opponentPlayer.getLeft());
    }

    @Test
    public void Down()  {
        Square down = new Square(1, 3, board);
        opponentPlayer.setDown(down);
        assertEquals(down, opponentPlayer.getDown());
    }

    @Test
    public void Up()  {
        Square up = new Square(6, 2, board);
        opponentPlayer.setUp(up);
        assertEquals(up, opponentPlayer.getUp());
    }

    @Test
    public void opponentShootRightEmpty() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random0 = Mockito.mock(Random.class);
        when(random0.nextInt(4)).thenReturn(0);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random0);
        assertEquals(helloWorld.isOpponentTurn(), false);
        Square right = opponentPlayer.getRight();
        // Verifying the right square is indeed shot
        assertTrue(right.isShooted());
        // Verifying the square is indeed the right one
        assertEquals(right.getCoordinateX(), 7);
        assertEquals(right.getCoordinateY(), 7);
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootLeftEmpty() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random1 = Mockito.mock(Random.class);
        when(random1.nextInt(4)).thenReturn(1);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random1);
        Square left = opponentPlayer.getLeft();
        // Verifying the left square is indeed shot
        assertTrue(left.isShooted());
        // Verifying the square is indeed the right one
        assertEquals(left.getCoordinateX(), 5);
        assertEquals(left.getCoordinateY(), 7);
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootDownEmpty() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random2 = Mockito.mock(Random.class);
        when(random2.nextInt(4)).thenReturn(2);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random2);
        Square down = opponentPlayer.getDown();
        // Verifying the left square is indeed shot
        assertTrue(down.isShooted());
        // Verifying the square is indeed the right one
        assertEquals(down.getCoordinateX(), 6);
        assertEquals(down.getCoordinateY(), 8);
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootUpEmpty() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random3 = Mockito.mock(Random.class);
        when(random3.nextInt(4)).thenReturn(3);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random3);
        Square up = opponentPlayer.getUp();
        // Verifying the left square is indeed shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the right one
        assertEquals(up.getCoordinateX(), 6);
        assertEquals(up.getCoordinateY(), 6);
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRight_PartOfShip() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        Ship ship = new Ship("Destroyer", 2, true);
        board.placeShip(ship, 7, 7);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random0 = Mockito.mock(Random.class);
        when(random0.nextInt(4)).thenReturn(0);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random0);
        Square right = opponentPlayer.getRight();
        // Verifying the right square is shot
        assertTrue(right.isShooted());
        // Verifying the square is indeed the second right one
        assertEquals(right.getCoordinateX(), 8);
        assertEquals(right.getCoordinateY(), 7);
        // No ship located on the the second right:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRight_WholeShip() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        Ship ship = new Ship("Mini", 1, true);
        board.placeShip(ship, 7, 7);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random0 = Mockito.mock(Random.class);
        when(random0.nextInt(4)).thenReturn(0);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random0);
        Square right = opponentPlayer.getRight();
        // Verifying the right square is shot
        assertTrue(right.isShooted());
        // Verifying the square is indeed the second right one
        assertEquals(right.getCoordinateX(), 8);
        assertEquals(right.getCoordinateY(), 7);
        // No ship located on the the second right:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
        // Verifying since whole ship was found, square list is empty
        assertTrue(alreadyShot.isEmpty());
    }

    @Test
    public void opponentShootLeft_PartOfShip() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        Ship ship = new Ship("Destroyer", 2, true);
        board.placeShip(ship, 5, 7);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random1 = Mockito.mock(Random.class);
        when(random1.nextInt(4)).thenReturn(1);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random1);
        Square left = opponentPlayer.getLeft();
        // Verifying the left square is shot
        assertTrue(left.isShooted());
        // Verifying the square is indeed the second left one
        assertEquals(left.getCoordinateX(), 4);
        assertEquals(left.getCoordinateY(), 7);
        // No ship located on the the second left:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootLeft_WholeShip() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        Ship ship = new Ship("Mini", 1, true);
        board.placeShip(ship, 5, 7);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random1 = Mockito.mock(Random.class);
        when(random1.nextInt(4)).thenReturn(1);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random1);
        Square left = opponentPlayer.getLeft();
        // Verifying the left square is shot
        assertTrue(left.isShooted());
        // Verifying the square is indeed the second left one
        assertEquals(left.getCoordinateX(), 4);
        assertEquals(left.getCoordinateY(), 7);
        // No ship located on the the second left:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
        // Verifying since whole ship was found, square list is empty
        assertTrue(alreadyShot.isEmpty());
    }

    @Test
    public void opponentShootDown_PartOfShip() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        Ship ship = new Ship("Destroyer", 2, false);
        board.placeShip(ship, 6, 8);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random2 = Mockito.mock(Random.class);
        when(random2.nextInt(4)).thenReturn(2);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random2);
        Square down = opponentPlayer.getDown();
        // Verifying the down square is shot
        assertTrue(down.isShooted());
        // Verifying the square is indeed the second down one
        assertEquals(down.getCoordinateX(), 6);
        assertEquals(down.getCoordinateY(), 9);
        // No ship located on the the down left:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootDown_WholeShip() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        Ship ship = new Ship("Mini", 1, false);
        board.placeShip(ship, 6, 8);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random2 = Mockito.mock(Random.class);
        when(random2.nextInt(4)).thenReturn(2);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random2);
        Square down = opponentPlayer.getDown();
        // Verifying the down square is shot
        assertTrue(down.isShooted());
        // Verifying the square is indeed the second down one
        assertEquals(down.getCoordinateX(), 6);
        assertEquals(down.getCoordinateY(), 9);
        // No ship located on the the second down:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
        // Verifying since whole ship was found, square list is empty
        assertTrue(alreadyShot.isEmpty());
    }

    @Test
    public void opponentShootUp_PartOfShip() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        Ship ship = new Ship("Destroyer", 2, false);
        board.placeShip(ship, 6, 6);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random3 = Mockito.mock(Random.class);
        when(random3.nextInt(4)).thenReturn(3);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random3);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(up.getCoordinateX(), 6);
        assertEquals(up.getCoordinateY(), 5);
        // No ship located on the the up left:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootUp_WholeShip() {
        ArrayList<Square> alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        Ship ship = new Ship("Mini", 1, false);
        board.placeShip(ship, 6, 6);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        Random random3 = Mockito.mock(Random.class);
        when(random3.nextInt(4)).thenReturn(3);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random3);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(up.getCoordinateX(), 6);
        assertEquals(up.getCoordinateY(), 5);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
        // Verifying since whole ship was found, square list is empty
        assertTrue(alreadyShot.isEmpty());
    }

    @Test
    public void opponentShootUpBoarder_0() {
        Random random0 = Mockito.mock(Random.class);
        when(random0.nextInt(3)).thenReturn(0);
        opponentPlayer.shootUp(board, 4, 0, random0);
        Square right = opponentPlayer.getRight();
        // Verifying the up square is shots
        assertTrue(right.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(right.getCoordinateX(), 5);
        assertEquals(right.getCoordinateY(), 0);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootUpBoarder_1() {

        Random random1 = Mockito.mock(Random.class);
        when(random1.nextInt(3)).thenReturn(1);
        opponentPlayer.shootUp(board, 4, 0, random1);
        Square left = opponentPlayer.getLeft();
        // Verifying the up square is shots
        assertTrue(left.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(left.getCoordinateX(), 3);
        assertEquals(left.getCoordinateY(), 0);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootUpBoarder_2() {
        Random random2 = Mockito.mock(Random.class);
        when(random2.nextInt(3)).thenReturn(2);
        opponentPlayer.shootUp(board, 4, 0, random2);
        Square down = opponentPlayer.getDown();
        // Verifying the up square is shots
        assertTrue(down.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(down.getCoordinateX(), 4);
        assertEquals(down.getCoordinateY(), 1);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootUpAlreadyShot_0() {
        Square square = board.getSquare(6, 4);
        square.setShooted(true);
        Random random0 = Mockito.mock(Random.class);
        when(random0.nextInt(3)).thenReturn(0);
        opponentPlayer.shootUp(board, 6, 5, random0);
        Square right = opponentPlayer.getRight();
        // Verifying the up square is shots
        assertTrue(right.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(right.getCoordinateX(), 7);
        assertEquals(right.getCoordinateY(), 5);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootUpAlreadyShot_1() {
        Square square = board.getSquare(6, 4);
        square.setShooted(true);
        Random random1 = Mockito.mock(Random.class);
        when(random1.nextInt(3)).thenReturn(1);
        opponentPlayer.shootUp(board, 6, 5, random1);
        Square left = opponentPlayer.getLeft();
        // Verifying the up square is shots
        assertTrue(left.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(left.getCoordinateX(), 5);
        assertEquals(left.getCoordinateY(), 5);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootUpAlreadyShot_2() {
        Square square = board.getSquare(6, 4);
        square.setShooted(true);
        Random random2 = Mockito.mock(Random.class);
        when(random2.nextInt(3)).thenReturn(2);
        opponentPlayer.shootUp(board, 6, 5, random2);
        Square down = opponentPlayer.getDown();
        // Verifying the up square is shots
        assertTrue(down.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(down.getCoordinateX(), 6);
        assertEquals(down.getCoordinateY(), 6);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }



}


