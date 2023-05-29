package com.example.notes;



import com.example.notes.Remote.Server;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UploadController {
    boolean isNotOnline= true;
    private Server server;
    private ArrayList<MyObject> arrayList;

    public ArrayList<MyObject> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<MyObject> arrayList) {
        this.arrayList = arrayList;
    }

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
    private ImageView imageOfStatus;

    Image redLight =new Image(getClass().getResource("status_image/Red.png").toString());
    Image greenLight =new Image(getClass().getResource("status_image/Green.png").toString());
    @FXML
    void launchButtonOnAction(ActionEvent event) {
        System.out.println("pressed");
    //стартуем сервер и меняем название кнопки на отключить
        if(isNotOnline) {
            //server = new Server(arrayList, server.getPort());
            imageOfStatus.setImage(greenLight);
            launchButton.setText("Остановить");
            IPDestinationField.setDisable(true);
            PortDestinationField.setDisable(true);
            uploadButton.setDisable(true);
            isNotOnline=false;
        }else {
            launchButton.setText("Запуск");
            imageOfStatus.setImage(redLight);
            IPDestinationField.setDisable(false);
            PortDestinationField.setDisable(false);
            uploadButton.setDisable(false);
            isNotOnline =true;
        }

    }

    @FXML
    void uploadButtonOnAction(ActionEvent event) {
        //todo start client


    }

    @FXML
    void initialize() {
//todo переписать контроллер на новый сервер

//        launchButton.setDisable(true);
//
//        StringProperty property = new SimpleStringProperty((serverStatus()));
//        YourServerStatusLabel.textProperty().bind(property);
//        YourIPLabel.setText(server.getIP()+"\n"+server.getHostAddress());
//        YourPortLabel.setText(String.valueOf(server.getPort()));
    }
//    private String serverStatus(){
//    imageOfStatus.setImage()//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
//        if (server.isAlive()){
//            return"Включен";
//        }else {
//            return"Выключен";
//        }
//    }
}