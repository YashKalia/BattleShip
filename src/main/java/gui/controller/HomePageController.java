package gui.controller;

import entity.board.BoardCreator;
import gui.Main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
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
        Stage primaryStage = Main.getStage();
        primaryStage.setScene(scene);
    }

    /**Display the home screen.
     *
     * @param event When user click on back option.
     * @throws IOException if error occurs.
     */
    @SuppressWarnings("deprecation")
    public void show_main_screen(ActionEvent event) throws IOException {
        Stage primaryStage = Main.getStage();

        URL url = new File("src/main/java/gui/fxml/MainFXML.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
    }

    /**Display the leaderboard.
     *
     * @param event When user click on login option.
     * @throws IOException if error occurs.
     */
    @SuppressWarnings("deprecation")
    public void show_Leaderboard(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        URL url = new File("src/main/java/gui/fxml/Leaderboard.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**Display the tutorial.
     *
     * @param event When user click on login option.
     * @throws IOException if error occurs.
     */
    @SuppressWarnings("deprecation")
    public void show_Tutorial(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        URL url = new File("src/main/java/gui/fxml/Tutorial.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
