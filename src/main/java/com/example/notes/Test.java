package com.example.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("notePreviewNoNeeded.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Start");
        primaryStage.show();

    }
}
