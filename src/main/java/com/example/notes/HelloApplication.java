package com.example.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void init() throws Exception {
        System.out.println("Начинаем проверку на наличие файла");
        Param.checkNotesFile();
        System.out.println("Закончили проверку файла");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Начинаем записывать файл");
        HelloController.list2ToNotes();
        Param.saveInFile(Param.notes);
        System.out.println("Закончили приложение");
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
        stage.show();

    }

    public static void main(String[] args) {
       launch();
    }
    //todo int i 64000 to 64500 { try {new serverSocket(port)}
}