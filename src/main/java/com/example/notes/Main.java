package com.example.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main extends Application {
    Path path = Path.of("C:\\Users\\33\\IdeaProjects\\Notes\\src\\main\\resources\\MyNotes");
    public static void main(String[] args) {
        launch(args);
    }
//todo НЕ ЗАПУСКАТЬ ПОКА НЕ УБЕДИШЬСЯ ЧТО СОЗДАЕТШЬ ЛИСТ С ОБЪЕКТАМИ ДО ПРОГРАММЫ!
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        Controller controller =  fxmlloader.getController();
        controller.setArrayList(initializeMyObjects(path));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Start");
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();

    }

    private ArrayList<MyObject> initializeMyObjects(Path path) {
        getImages(path);

        return new ArrayList<MyObject>();
    }

    private ArrayList<Path> getImages(Path path) {
        ArrayList<Path> listOfPathsToImages = new ArrayList<>();
        boolean b;
        try {
            for (Path p:(Files.walk(Path.of("C:\\Users\\33\\IdeaProjects\\Notes\\src\\main\\resources\\MyNotes\\0\\Images"))).filter(p -> p.toFile().isFile()).collect(Collectors.toList())) {

                b = false;
                try {
                    b = (ImageIO.read(p.toFile()) != null);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listOfPathsToImages.add(p.toAbsolutePath());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }//todo
        return listOfPathsToImages;
    }
}
