package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import entity.ships.Destroyer;
import entity.ships.Mini;
import gui.HelloWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class OpponentPlayerTest {
    private transient OpponentPlayer opponentPlayer;
    private transient Board board;
    private transient Game helloWorld;
    private transient Random random;
    private transient ArrayList<Square> alreadyShot;
    private transient Ship destroyer;
    private transient Ship mini;
    private transient Board mockedBoard;

    /**
     * Setting up the enviroment for the tests.
     */
    @BeforeEach
    public void setUpEnvironment() {
        opponentPlayer = new OpponentPlayer();
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new StandardBoard(opponent, handler);
        helloWorld = new Game();
        alreadyShot = new ArrayList<>();
        Square square = new Square(6, 7, board);
        alreadyShot.add(square);
        helloWorld.opponentTurn = true;
        opponentPlayer.setShotSquares(alreadyShot);
        random = Mockito.mock(Random.class);
        mockedBoard = Mockito.mock(Board.class);
        destroyer = new Destroyer(2, true);
        mini = new Mini(1, true);

    }

    @Test
    public void shottedSquares() {
        assertEquals(opponentPlayer.getShotSquares(), alreadyShot);
    }

    @Test
    public void right() {
        Square right = new Square(3, 5, board);
        opponentPlayer.setRight(right);
        assertEquals(right, opponentPlayer.getRight());
    }

    @Test
    public void left() {
        Square left = new Square(8, 5, board);
        opponentPlayer.setLeft(left);
        assertEquals(left, opponentPlayer.getLeft());
    }

    @Test
    public void down() {
        Square down = new Square(1, 3, board);
        opponentPlayer.setDown(down);
        assertEquals(down, opponentPlayer.getDown());
    }

    @Test
    public void up() {
        Square up = new Square(6, 2, board);
        opponentPlayer.setUp(up);
        assertEquals(up, opponentPlayer.getUp());
    }

    @Test
    public void opponentEnemyFullSquareList() {
        when(random.nextInt(4)).thenReturn(0);
        opponentPlayer.enemyShot(board, random);
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
    public void opponentShootRightEmpty() {
        when(random.nextInt(4)).thenReturn(0);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
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
        when(random.nextInt(4)).thenReturn(1);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
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
        when(random.nextInt(4)).thenReturn(2);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
        Square down = opponentPlayer.getDown();
        // Verifying the lower square is indeed shot
        assertTrue(down.isShooted());
        // Verifying the square is indeed the lower one
        assertEquals(down.getCoordinateX(), 6);
        assertEquals(down.getCoordinateY(), 8);
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootUpEmpty() {
        when(random.nextInt(4)).thenReturn(3);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
        Square up = opponentPlayer.getUp();
        // Verifying the upper square is indeed shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the upper one
        assertEquals(up.getCoordinateX(), 6);
        assertEquals(up.getCoordinateY(), 6);
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRight_PartOfShip() {
        board.placeShip(destroyer, 7, 7, board);
        when(random.nextInt(4)).thenReturn(0);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
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
        board.placeShip(mini, 7, 7, board);
        when(random.nextInt(4)).thenReturn(0);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
        Square right = opponentPlayer.getRight();
        // Verifying the right square is shot
        assertTrue(right.isShooted());
        // Verifying this right square contains a ship
        assertEquals(right.getShip(), mini);
        // Verifying the square is indeed the right one
        assertEquals(right.getCoordinateX(), 7);
        assertEquals(right.getCoordinateY(), 7);
        // No ship located on the the second right:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
        // Verifying since whole ship was found, square list is empty
        assertTrue(alreadyShot.isEmpty());
    }

    @Test
    public void opponentShootLeft_PartOfShip() {
        board.placeShip(destroyer, 5, 7, board);
        when(random.nextInt(4)).thenReturn(1);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
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
        board.placeShip(mini, 5, 7, board);
        when(random.nextInt(4)).thenReturn(1);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
        Square left = opponentPlayer.getLeft();
        // Verifying the left square is shot
        assertTrue(left.isShooted());
        // Verifying this left square contains a ship
        assertEquals(left.getShip(), mini);
        // Verifying the square is indeed the second left one
        assertEquals(left.getCoordinateX(), 5);
        assertEquals(left.getCoordinateY(), 7);
        // No ship located on the the second left:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
        // Verifying since whole ship was found, square list is empty
        assertTrue(alreadyShot.isEmpty());
    }

    @Test
    public void opponentShootDown_PartOfShip() {
        board.placeShip(destroyer, 6, 8, board);
        when(random.nextInt(4)).thenReturn(2);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
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
        board.placeShip(mini, 6, 8, board);
        when(random.nextInt(4)).thenReturn(2);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
        Square down = opponentPlayer.getDown();
        // Verifying the down square is shot
        assertTrue(down.isShooted());
        // Verifying this down square contains a ship
        assertEquals(down.getShip(), mini);
        // Verifying the square is indeed the second down one
        assertEquals(down.getCoordinateX(), 6);
        assertEquals(down.getCoordinateY(), 8);
        // No ship located on the the second down:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
        // Verifying since whole ship was found, square list is empty
        assertTrue(alreadyShot.isEmpty());
    }

    @Test
    public void opponentShootUp_PartOfShip() {
        board.placeShip(destroyer, 6, 6, board);
        when(random.nextInt(4)).thenReturn(3);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
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
        board.placeShip(mini, 6, 6, board);
        when(random.nextInt(4)).thenReturn(3);
        opponentPlayer.enemyShotCoordinates(board, 6, 7, random);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shot
        assertTrue(up.isShooted());
        // Verifying this up square contains a ship
        assertEquals(up.getShip(), mini);
        // Verifying the square is indeed the second up one
        assertEquals(up.getCoordinateX(), 6);
        assertEquals(up.getCoordinateY(), 6);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
        // Verifying since whole ship was found, square list is empty
        assertTrue(alreadyShot.isEmpty());
    }

    @Test
    public void opponentShootUpBoarder_0() {
        when(random.nextInt(3)).thenReturn(0);
        opponentPlayer.shootUp(board, 4, 0, random);
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

        when(random.nextInt(3)).thenReturn(1);
        opponentPlayer.shootUp(board, 4, 0, random);
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
        when(random.nextInt(3)).thenReturn(2);
        opponentPlayer.shootUp(board, 4, 0, random);
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
        when(random.nextInt(3)).thenReturn(0);
        opponentPlayer.shootUp(board, 6, 5, random);
        Square right = opponentPlayer.getRight();
        // Verifying the up square is shot
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
        when(random.nextInt(3)).thenReturn(1);
        opponentPlayer.shootUp(board, 6, 5, random);
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
        when(random.nextInt(3)).thenReturn(2);
        opponentPlayer.shootUp(board, 6, 5, random);
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

    @Test
    public void opponentShootLeftBoarder_0() {
        when(random.nextInt(3)).thenReturn(0);
        opponentPlayer.shootLeft(board, 0, 7, random);
        Square right = opponentPlayer.getRight();
        // Verifying the right square is shot
        assertTrue(right.isShooted());
        // Verifying the square is indeed the second right one
        assertEquals(right.getCoordinateX(), 1);
        assertEquals(right.getCoordinateY(), 7);
        // No ship located on the the second right:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootLeftBoarder_1() {

        when(random.nextInt(3)).thenReturn(1);
        opponentPlayer.shootLeft(board, 0, 7, random);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(up.getCoordinateX(), 0);
        assertEquals(up.getCoordinateY(), 6);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootLeftBoarder_2() {
        when(random.nextInt(3)).thenReturn(2);
        opponentPlayer.shootLeft(board, 0, 7, random);
        Square down = opponentPlayer.getDown();
        // Verifying the down square is shots
        assertTrue(down.isShooted());
        // Verifying the square is indeed the second down one
        assertEquals(down.getCoordinateX(), 0);
        assertEquals(down.getCoordinateY(), 8);
        // No ship located on the the second down:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootLeftAlreadyShot_0() {
        Square square = board.getSquare(7, 4);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(0);
        opponentPlayer.shootLeft(board, 8, 4, random);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shots
        assertTrue(up.isShooted());
        // Verifying the square is indeed the second up one
        assertEquals(up.getCoordinateX(), 8);
        assertEquals(up.getCoordinateY(), 3);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootLeftAlreadyShot_1() {
        Square square = board.getSquare(7, 4);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(1);
        opponentPlayer.shootLeft(board, 8, 4, random);
        Square down = opponentPlayer.getDown();
        // Verifying the down square is shots
        assertTrue(down.isShooted());
        // Verifying the square is indeed the second down one
        assertEquals(down.getCoordinateX(), 8);
        assertEquals(down.getCoordinateY(), 5);
        // No ship located on the the second down
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootLeftAlreadyShot_2() {
        Square square = board.getSquare(7, 4);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(2);
        opponentPlayer.shootLeft(board, 8, 4, random);
        Square right = opponentPlayer.getRight();
        // Verifying the right square is shot
        assertTrue(right.isShooted());
        // Verifying the square is indeed the second right one
        assertEquals(right.getCoordinateX(), 9);
        assertEquals(right.getCoordinateY(), 4);
        // No ship located on the the second right:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRightBoarder_0() {
        when(random.nextInt(3)).thenReturn(0);
        opponentPlayer.shootRight(board, 9, 5, random);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the up one
        assertEquals(up.getCoordinateX(), 9);
        assertEquals(up.getCoordinateY(), 4);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRightBoarder_1() {

        when(random.nextInt(3)).thenReturn(1);
        opponentPlayer.shootRight(board, 9, 5, random);
        Square left = opponentPlayer.getLeft();
        // Verifying the left square is shot
        assertTrue(left.isShooted());
        // Verifying the square is indeed the left one
        assertEquals(left.getCoordinateX(), 8);
        assertEquals(left.getCoordinateY(), 5);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRightBoarder_2() {
        when(random.nextInt(3)).thenReturn(2);
        opponentPlayer.shootRight(board, 9, 5, random);
        Square down = opponentPlayer.getDown();
        // Verifying the down square is shots
        assertTrue(down.isShooted());
        // Verifying the square is indeed the down one
        assertEquals(down.getCoordinateX(), 9);
        assertEquals(down.getCoordinateY(), 6);
        // No ship located on the the second down:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRightAlreadyShot_0() {
        Square square = board.getSquare(8, 4);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(0);
        opponentPlayer.shootRight(board, 7, 4, random);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the up one
        assertEquals(up.getCoordinateX(), 7);
        assertEquals(up.getCoordinateY(), 3);
        // No ship located on the up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRightAlreadyShot_1() {
        Square square = board.getSquare(8, 4);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(1);
        opponentPlayer.shootRight(board, 7, 4, random);
        Square left = opponentPlayer.getLeft();
        // Verifying the left square is shot
        assertTrue(left.isShooted());
        // Verifying the square is indeed the left one
        assertEquals(left.getCoordinateX(), 6);
        assertEquals(left.getCoordinateY(), 4);
        // No ship located on the left
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootRightAlreadyShot_2() {
        Square square = board.getSquare(8, 4);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(2);
        opponentPlayer.shootRight(board, 7, 4, random);
        Square down = opponentPlayer.getDown();
        // Verifying the down square is shot
        assertTrue(down.isShooted());
        // Verifying the square is indeed the down one
        assertEquals(down.getCoordinateX(), 7);
        assertEquals(down.getCoordinateY(), 5);
        // No ship located on the down
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootDownBoarder_0() {
        when(random.nextInt(3)).thenReturn(0);
        opponentPlayer.shootDown(board, 5, 9, random);
        Square right = opponentPlayer.getRight();
        // Verifying the right square is shot
        assertTrue(right.isShooted());
        // Verifying the square is indeed the right one
        assertEquals(right.getCoordinateX(), 6);
        assertEquals(right.getCoordinateY(), 9);
        // No ship located on the the second right
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootDownBoarder_1() {

        when(random.nextInt(3)).thenReturn(1);
        opponentPlayer.shootDown(board, 5, 9, random);
        Square left = opponentPlayer.getLeft();
        // Verifying the left square is shot
        assertTrue(left.isShooted());
        // Verifying the square is indeed the left one
        assertEquals(left.getCoordinateX(), 4);
        assertEquals(left.getCoordinateY(), 9);
        // No ship located on the the second left:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootDownBoarder_2() {
        when(random.nextInt(3)).thenReturn(2);
        opponentPlayer.shootDown(board, 5, 9, random);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the up one
        assertEquals(up.getCoordinateX(), 5);
        assertEquals(up.getCoordinateY(), 8);
        // No ship located on the the second up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootDownAlreadyShot_0() {
        Square square = board.getSquare(7, 5);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(0);
        opponentPlayer.shootDown(board, 7, 4, random);
        Square right = opponentPlayer.getRight();
        // Verifying the right square is shot
        assertTrue(right.isShooted());
        // Verifying the square is indeed the right one
        assertEquals(right.getCoordinateX(), 8);
        assertEquals(right.getCoordinateY(), 4);
        // No ship located on the the second right:
        // Verifying it is not the opponent's turn anymore.
    }

    @Test
    public void opponentShootDownAlreadyShot_1() {
        Square square = board.getSquare(7, 5);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(1);
        opponentPlayer.shootDown(board, 7, 4, random);
        Square left = opponentPlayer.getLeft();
        // Verifying the left square is shot
        assertTrue(left.isShooted());
        // Verifying the square is indeed the left one
        assertEquals(left.getCoordinateX(), 6);
        assertEquals(left.getCoordinateY(), 4);
        // No ship located on the left
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void opponentShootDownAlreadyShot_2() {
        Square square = board.getSquare(7, 5);
        square.setShooted(true);
        when(random.nextInt(3)).thenReturn(2);
        opponentPlayer.shootDown(board, 7, 4, random);
        Square up = opponentPlayer.getUp();
        // Verifying the up square is shot
        assertTrue(up.isShooted());
        // Verifying the square is indeed the up one
        assertEquals(up.getCoordinateX(), 7);
        assertEquals(up.getCoordinateY(), 3);
        // No ship located on the up:
        // Verifying it is not the opponent's turn anymore.
        assertFalse(helloWorld.isOpponentTurn());
    }

    @Test
    public void placingShips() {
        List<Ship> myShips = new ArrayList<>();
        myShips.add(destroyer);
        board.setShipList(myShips);
        when(random.nextInt(10)).thenReturn(3);
        opponentPlayer.placeShipsOpponent(board, random);
        // Verifying whether the ship is indeed placed
        // Assertion missing
    }


}




