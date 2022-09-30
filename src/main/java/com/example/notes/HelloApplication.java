package com.example.notes;

import com.example.notes.Remote.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


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
        stage.setOnShown((event) -> {
            HelloController.onStart();
        });
        stage.show();


    }
        static Server server = new Server();
    public static void main(String[] args) {
        System.out.println("Начинаем проверку на наличие файла");
        Param.checkNotesFile();
        System.out.println("Закончили проверку файла");
        server.setDaemon(true);
        server.start();
        launch();
    }
    //todo int i 64000 to 64500 { try {new serverSocket(port)}
}