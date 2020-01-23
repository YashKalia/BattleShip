package gui.controller;

import database.Connect;
import gui.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class LeaderboardController {
    @FXML
    public transient Label label1;
    @FXML
    private transient Label label2;
    @FXML
    private transient Label label3;
    @FXML
    private transient Label label4;
    @FXML
    private transient Label label5;

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

    /**
     * Get leaders and populate the leaderboard.
     */
    public void getLeaders() throws SQLException, ClassNotFoundException {
        String zero = Connect.getTopFive().get(0).toString();
        System.out.println(zero);
        label1.setText(zero);
        label2.setText(Connect.getTopFive().get(1).toString());
        label3.setText(Connect.getTopFive().get(2).toString());
        label4.setText(Connect.getTopFive().get(3).toString());
        label5.setText(Connect.getTopFive().get(4).toString());
    }

    public void show_leaderboard(ActionEvent event)throws SQLException, ClassNotFoundException {
        getLeaders();
    }

}
