package com.example.notes;



import com.example.notes.Remote.Server;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;


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


    @FXML
    void launchButtonOnAction(ActionEvent event) {

    }

    @FXML
    void uploadButtonOnAction(ActionEvent event) {
        //todo start client


    }
    Server server = new Server();
    @FXML
    void initialize() {

        launchButton.setDisable(true);

        StringProperty property = new SimpleStringProperty((serverStatus()));
        YourServerStatusLabel.textProperty().bind(property);
        YourIPLabel.setText(server.getIP()+"\n"+server.getHostAddress());
        YourPortLabel.setText(String.valueOf(server.getPort()));
    }
    private String serverStatus(){
        if (server.isAlive()){
            return"Включен";
        }else {
            return"Выключен";
        }
    }
}