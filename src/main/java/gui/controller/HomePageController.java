package gui.controller;

import entity.board.BoardCreator;
import entity.board.EnhancedBoard;
import entity.board.EnhancedBoardCreator;
import entity.board.StandardBoardCreator;
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

    /**Shows the game screen with the standardboard,finally.
     *
     * @param event if Standard button is pressed.
     */
    public void startStandardGame(ActionEvent event) {
        Parent root = StandardBoardCreator.createBord();
        Scene scene = new Scene(root);
        Stage primaryStage = Main.getStage();
        primaryStage.setScene(scene);
    }

    /**Shows the game screen with the enhancedboard,finally.
     *
     * @param event if Enhanced button is pressed.
     */
    public void startEnhancedGame(ActionEvent event) {
        Parent root = EnhancedBoardCreator.createBord();
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
