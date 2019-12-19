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


public class LoginController {

    private static User user;

    @FXML
     transient TextField txtPassword;

    @FXML
     transient TextField txtUsername;

    @FXML
    transient Label response;

    /**
     * Tries to log in a user.
     * If the response of the client is the boolean true, the username and password are correct.
     * Then, the local variable user is update with the credentials,
     * And a new screen is brought up.
     * Otherwise, an error message is displayed.
     *
     * @param event on click
     * @throws Exception HUGE OFF
     */
    @SuppressWarnings("deprecation")
    public void login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //txtUsername.getText(), txtPassword.getText()
        user = new User(txtUsername.getText(),txtPassword.getText());
        String r = Connect.authenticate(user);
        if (r.equals("User does not exist.")) {
            response.setText("User does not exist.");
        } else if (r.equals("Authentication successful.")) {
            response.setText("Authentication successful.");
            Stage primaryStage = new Stage();
            URL url = new File("src/main/java/gui/fxml/HomePage.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            //Stage firstStage = (Stage) txtUsername.getScene().getWindow();
            //firstStage.close();


        } else {
            response.setText("Incorrect password,authentication unsuccessful.");
        }

    }
}
