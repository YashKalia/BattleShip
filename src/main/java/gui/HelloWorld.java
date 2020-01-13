package gui;

import entity.BoardCreator;
import entity.Game;
import entity.StandardBoardCreator;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class HelloWorld extends Application {

    protected static Game game;
    protected static BoardCreator boardCreator = new StandardBoardCreator();



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = boardCreator.createBord();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
