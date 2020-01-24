package gui.controller;

import database.Connect;
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



public class MainController {


    /**Display the login screen.
     *
     * @param event When user click on login option.
     * @throws IOException if error occurs.
     */
    @SuppressWarnings("deprecation")
    public void show_Login_Screen(ActionEvent event) throws IOException {
        Stage primaryStage = Main.getStage();

        URL url = new File("src/main/java/gui/fxml/Login.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
    }


    /**Display the register screen.
     *
     * @param event When user click on login option.
     * @throws IOException if error occurs.
     */
    @SuppressWarnings("deprecation")
    public void show_Register_Screen(ActionEvent event) throws IOException {
        Stage primaryStage = Main.getStage();

        URL url = new File("src/main/java/gui/fxml/Register.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
    }

    /**Display the register screen.
     *
     * @param event When user click on login option.
     * @throws IOException if error occurs.
     */
    @SuppressWarnings("deprecation")
    public void close_Window(ActionEvent event) {
        Stage primaryStage = Main.getStage();
        primaryStage.close();
    }

}
