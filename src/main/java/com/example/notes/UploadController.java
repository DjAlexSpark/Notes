package com.example.notes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UploadController {

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

    @FXML
    void launchButtonOnAction(ActionEvent event) {
        /*todo start server from ur computer, getPort()=>YourPortLaber
        todo getIP()=>YourIPLabel, getStatus()=>yourServerStatusLabel;
         */
        if (YourServerStatusLabel.getText().equals("Включен")) {
            YourServerStatusLabel.setText("Выключен");
        }else{
            YourServerStatusLabel.setText("Включен");
        }

    }

    @FXML
    void uploadButtonOnAction(ActionEvent event) {
        /*
        todo start client выдать новое окошко с сравнением
         */
    }

    @FXML
    void initialize() {



    }

}