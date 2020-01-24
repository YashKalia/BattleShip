package entity.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import entity.Game;
import entity.ships.Destroyer;
import entity.ships.Ship;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private transient Board board;
    private transient String battleship = "BattleShip";
    private transient String destroyer = "Destroyer";
    private transient Map<String, Point2D> listFront = new HashMap<>();
    private transient List<Ship> listOfShips = new ArrayList<>();
    private transient Game game;


    /**
     * Setting up the environment of testing.
     */
    @BeforeEach
    public void setUpEnvironment() {
        listFront.put(battleship, new Point2D(2.0, 4.0));
        listOfShips.add(new Destroyer(destroyer, 2, true));
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new Board(opponent, handler) {
            @Override
            public boolean inRange(int x, int y, Board board) {
                return false;
            }

            @Override
            public Board reshape(Board board) {
                return null;
            }
        };
    }

    @Test
    public void getFrontOfShips() {
        board.setFrontShip(listFront);
        Map<String, Point2D> actual = board.getFrontShip();
        assertEquals(listFront, actual);
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
    public void getGame() {
        board.setGame(game);
        Game actual = board.getGame();
        assertEquals(game, actual);
    }

    @Test
    public void getShipList() {
        board.setShipList(listOfShips);
        List<Ship> actual = board.getShipList();
        assertEquals(listOfShips, actual);

    }
}
