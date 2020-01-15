package gui.controller;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TutorialController {


    /**Display the home screen.
     *
     * @param event When user click on back option.
     * @throws IOException if error occurs.
     */
    @SuppressWarnings("deprecation")
    public void show_home_page(ActionEvent event) throws IOException {
        Stage primaryStage = Main.getStage();

        URL url = new File("src/main/java/gui/fxml/HomePage.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
    }
}
