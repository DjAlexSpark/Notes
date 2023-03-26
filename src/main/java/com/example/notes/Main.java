package com.example.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Scene scene = new Scene(fxmlloader.load());

        Controller controller = fxmlloader.getController();
        controller.fillArray();
        controller.setArrayList(controller.getMyObjectsFrom(controller.getPath()));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Start");
        primaryStage.show();
        controller.writeArray();
    }




}
