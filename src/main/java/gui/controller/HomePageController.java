package gui.controller;

import entity.BoardCreator;
import entity.Game;
import gui.HelloWorld;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageController {

    protected static BoardCreator boardCreator;

    /**Shows the game screen,finally.
     *
     * @param event if button is pressed.
     */
    public void startGame(ActionEvent event) {
        Parent root = boardCreator.createBord();
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
