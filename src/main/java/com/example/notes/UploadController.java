package com.example.notes;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.notes.Remote.Server;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UploadController {

    @FXML
    private GridPane gridPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField IPDestinationField;

    @FXML
    private TextField PortDestinationField;

    @FXML
    private Label YourIPLabel;

    @FXML
    private Label YourPortLabel;

    @FXML
    private Label YourServerStatusLabel;

    @FXML
    private Button launchButton;

    @FXML
    private Button uploadButton;
    Server server = new Server();
    @FXML
    void launchButtonOnAction(ActionEvent event) {
//        btn.setDisable(true);
//        new Thread(()->{
//            final String numberOfViews =  getNumberOfViewers();
//            Platform.runLater(()->{
//                lbl.setText(numberOfViews);
//                btn.setDisable(false);
//            });
//        }).start();
//        lbl.setText(getNumberOfViewers());

        /*todo start server from ur computer, getPort()=>YourPortLaber
        todo getIP()=>YourIPLabel, getStatus()=>yourServerStatusLabel;
         */
        if (YourServerStatusLabel.getText().equals("Включен")|| server.isAlive()) {
            uploadButton.setDisable(false);
            IPDestinationField.setDisable(false);
            PortDestinationField.setDisable(false);
            launchButton.setDisable(true);
            new Thread(()->{
                Platform.runLater(()->{
                    server.interrupt();
                    System.out.println("Выключаю сервер");
                    YourServerStatusLabel.setText("Выключен");
                    launchButton.setText("Включить");
                    YourServerStatusLabel.setStyle("");//todo-fx-font-color:green
                    launchButton.setDisable(false);
                });
            }).start();

        }else{
            uploadButton.setDisable(true);
            launchButton.setDisable(true);
            IPDestinationField.setDisable(true);
            PortDestinationField.setDisable(true);
            new Thread(()->{
                Platform.runLater(()->{
                    server.interrupt();
                    System.out.println("Включаю сервер");
                    YourServerStatusLabel.setText("Включен");
                    launchButton.setText("Выключить");
                    YourServerStatusLabel.setStyle("");//todo-fx-font-color:red
                    launchButton.setDisable(false);
                });
            }).start();

        }

    }

    @FXML
    void uploadButtonOnAction(ActionEvent event) {
        /*
        todo start client выдать новое окошко с сравнением
         */
    }
    Stage child;
    @FXML
    void initialize() {

    }

}