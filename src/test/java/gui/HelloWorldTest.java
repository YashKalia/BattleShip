package gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import entity.Board;

import entity.StandardBoard;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HelloWorldTest {

    private transient HelloWorld helloWorld;

    @BeforeEach
    public void setUpEnvironment() {
        helloWorld = new HelloWorld();
    }

    @Test
    public void getInProgress() {
        boolean actual = helloWorld.isInProgress();
        assertEquals(false, actual);
    }

    @Test
    public void setInProgress() {
        helloWorld.setInProgress(true);
        boolean actual = helloWorld.isInProgress();
        assertEquals(true, actual);
    }

    @Test
    public void opponentBoard() {
        EventHandler<? super MouseEvent> handler = null;
        Board opponent = new StandardBoard(false, handler);
        helloWorld.setOpponentBoard(opponent);
        Board actual = helloWorld.getOpponentBoard();
        assertEquals(opponent, actual);
    }

    @Test
    public void playerBoard() {
        EventHandler<? super MouseEvent> handler = null;
        Board player = new StandardBoard(true, handler);
        helloWorld.setPlayerBoard(player);
        Board actual = helloWorld.getPlayerBoard();
        assertEquals(player, actual);
    }



    @Test
    public void setAllShipsPlaced() {
        helloWorld.setAllShipsPlaced(6);
        int actual = helloWorld.getAllShipsPlaced();
        assertEquals(6, actual);
    }

    @Test
    public void getAllShipsPlaced() {
        int actual = helloWorld.getAllShipsPlaced();
        assertEquals(6, actual);
    }

    @Test
    public void getOpponentsTurn() {
        boolean actual = helloWorld.isOpponentTurn();
        assertEquals(false, actual);
    }

    @Test
    public void setOpponentsTurn() {
        helloWorld.setOpponentTurn(true);
        boolean actual = helloWorld.isOpponentTurn();
        assertEquals(true, actual);
    }
}