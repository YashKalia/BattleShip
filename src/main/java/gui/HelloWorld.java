package gui;

import entity.Game;
import entity.board.BoardCreator;
import entity.board.EnhancedBoardCreator;
import entity.board.StandardBoardCreator;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class HelloWorld extends Application {

    protected static Game game;



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = EnhancedBoardCreator.createBord();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
