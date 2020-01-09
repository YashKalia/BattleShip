package entity;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BoardClient {
    private static EventHandler<? super MouseEvent> handler;

    public static void main(String[] args) {
        Board standardBoard = new StandardBoard(false, handler);
        standardBoard.createBoard();
    }
}
