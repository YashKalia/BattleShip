package gui.controller;

import database.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class LeaderboardController {

    @FXML
    public javafx.scene.control.TableView tableView;

    public TableColumn username;
    public TableColumn score;

    /**
     * Get leaders and populate the leaderboard.
     */
    private void getLeaders() throws SQLException, ClassNotFoundException{
        Connect.getLeaderboard();
//        username.setCellValueFactory(new PropertyValueFactory("name"));
//        score.setCellValueFactory(new PropertyValueFactory("score"));
//        tableView.getColumns().setAll(username, score);
    }


}
