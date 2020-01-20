package gui.controller;

import database.Connect;
import gui.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


public class LeaderboardController extends Application {

    @FXML
    private transient ObservableList<ObservableList> data;
    private transient TableView tableview;

    public transient TableColumn username;
    public transient TableColumn score;

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
    private void getLeaders() throws SQLException, ClassNotFoundException {
        //ObservableList listTopFive = FXCollections.observableArrayList(Connect.getTopFive());
        //tableView.setItems(listTopFive);
        data = FXCollections.observableArrayList();

        for (int i = 0; i < Connect.getTopFive().getMetaData().getColumnCount(); i++) {
            int j = 1;
            TableColumn table = new TableColumn(Connect.getTopFive().getMetaData()
                    .getColumnName(i + 1));
            table.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,
                    String>, ObservableValue<String>>() {
                public ObservableValue<String> call(
                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            tableview.getColumns().addAll(table);
            System.out.println("Column [" + i + "]");
        }

        while (Connect.getTopFive().next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= Connect.getTopFive().getMetaData().getColumnCount(); i++) {
                row.add(Connect.getTopFive().getString(i));
            }
            System.out.println("Row[1] added " + row);
            data.add(row);
        }
        tableview.setItems(data);

        username.setCellValueFactory(new PropertyValueFactory("name"));
        score.setCellValueFactory(new PropertyValueFactory("score"));
        tableview.getColumns().setAll(score);
    }

    @Override
    public void start(Stage stage) throws Exception {
        tableview = new TableView();
        getLeaders();

        Scene scene = new Scene(tableview);

        stage.setScene(scene);
        stage.show();
    }


}
