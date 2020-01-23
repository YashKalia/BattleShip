package gui;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static Stage getStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setStage(primaryStage);
        primaryStage.setTitle("Naval Battle");

        File file = new File("src/main/java/gui/fxml/MainFXML.fxml");
        URL url = file.toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        //scene.getStylesheets().add("src/main/java/gui/style.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


}