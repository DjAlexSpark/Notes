package com.example.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void init() throws Exception {

    }

    @Override
    public void stop() throws Exception {

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());//, 320, 240
        stage.setTitle("Заметки");
        //stage.setResizable(false);
        scene.getStylesheets() .add(getClass() .getResource(
                "style_hello_view.css") .toExternalForm());
        stage.setScene(scene);
        stage.setOnShown((event) -> {
            HelloController.onStart();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}