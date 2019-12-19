package gui.controller;

import database.Connect;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class RegisterController {


    @FXML
    transient TextField fieldusername;

    @FXML
    transient TextField fieldpassword;

    @FXML
    transient Label result;


    /**register.
     *
     * @param event if mouse clicked.
     * @throws SQLException if exception occurs.
     * @throws ClassNotFoundException if exception occurs.
     * @throws IOException if exception occurs.
     */
    @SuppressWarnings("deprecation")
    public void registerUser(ActionEvent event) throws SQLException,
            ClassNotFoundException, IOException {

        String response = Connect.registerUser(
                new User(fieldusername.getText(), fieldpassword.getText()));
        if (response.equals("Registration successful.")) {
            result.setText("Registration successful.");
            Stage primaryStage = new Stage();
            URL url = new File("src/main/java/gui/fxml/HomePage.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else if (response.equals("Registration unsuccessful.")) {
            result.setText("Registration unsuccessful.");
        } else {
            result.setText("User already exists,please log in.");
        }
    }


}
