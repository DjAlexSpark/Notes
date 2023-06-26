package com.example.notes;


import com.example.notes.Remote.Client;
import com.example.notes.Remote.Server;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UploadController {
    boolean isNotOnline = true;
    private Server server;
    private Client client;
    private ArrayList<MyObject> arrayList;
    private Stage stage;
    private int portNumber = 5000;

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


    boolean isWorking;

    @FXML
    void launchButtonOnAction(ActionEvent event) {

        if (server != null && !server.isAlive()) {
            server.start();
        }
        System.out.println("pressed");
        //стартуем сервер и меняем название кнопки на отключить
//        while(isNotOnline){
//
//                Thread thread = new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            serverSocket.accept();
//
//                            System.out.println("server accept");
//                        } catch (IOException e) {
//                            System.err.println("server IOException");
//                            throw new RuntimeException(e);
//                        }
//                    }
//                };
//
//
//        }


//        if(isNotOnline) {
//
//            //стартуем сервер
//            System.out.println("ветка true");
//
//            //server.setArrayList(arrayList);
//            imageOfStatus.setImage(greenLight);
//            launchButton.setText("Остановить");
//            IPDestinationField.setDisable(true);
//            PortDestinationField.setDisable(true);
//            uploadButton.setDisable(true);
//            isNotOnline=false;
//        }else {
//
////            гасим сервер
////            server.close();
//            System.out.println("ветка else");
//            launchButton.setText("Запуск");
//            imageOfStatus.setImage(redLight);
//            IPDestinationField.setDisable(false);
//            PortDestinationField.setDisable(false);
//            uploadButton.setDisable(false);
//            isNotOnline =true;
//        }

    }

    @FXML
    void uploadButtonOnAction(ActionEvent event) {
        //todo start client


    }

    @FXML
    void initialize() {
        YourPortLabel.setText(String.valueOf(getPortNumber()));
        YourIPLabel.setText("");
        if (server != null) {
            if (server.isAlive()) {
                YourServerStatusLabel.setText("Запущен");
            }
        }


        //todo гавно нерабочее
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    if (server.isAlive()) {
                        updateMessage("Server is running");
                    } else {
                        updateMessage("Server is not running");
                    }
                    Thread.sleep(1000); // Check every second
                }
            }
        };
        YourServerStatusLabel.textProperty().bind(task.messageProperty()); // Bind the label to the task progress

        Thread thread = new Thread(task);
        thread.setDaemon(true); // Make the thread a daemon thread to avoid blocking JVM shutdown
        thread.start();


//рабочее
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            if (server.isAlive()) {
                YourServerStatusLabel.setText("Server is running");
            } else {
                YourServerStatusLabel.setText("Server is not running");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        timeline.play();

    }

    public void startServer() {
        server = new Server(
                this.getArrayList(),
                this.getPortNumber());
        server.setDaemon(true);
        server.start();
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public void setStage(Stage stage) {
        this.stage = stage;

    }


}