import entity.User;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoringTest {
    private transient Board board;
    private transient String battleship;
    private transient String destroyer;
    private transient String username;
    private transient String password;
    private transient Scoring score;

    @BeforeEach
    public void setUpEnvironment() {
        boolean opponent = false;
        EventHandler<? super MouseEvent> handler = null;
        board = new Board(opponent, handler);
        battleship = "BattleShip";
        destroyer = "Destroyer";
        username = "pravesha";
        password = "password";
        score = new Scoring();

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
    void scoreSystem() {
    }
}