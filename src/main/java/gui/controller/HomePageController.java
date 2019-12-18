package gui.controller;

import gui.HelloWorld;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageController {

    /**Shows the game screen,finally.
     *
     * @param event if button is pressed.
     */
    public void startGame(ActionEvent event) {
        Scene scene = new Scene(HelloWorld.setUp());
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
