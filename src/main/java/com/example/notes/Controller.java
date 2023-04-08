package com.example.notes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class Controller {

    @FXML
    private MenuItem aboutButton;

    @FXML
    private MenuItem addImageButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem closeButton;

    @FXML
    private MenuItem deleteButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem saveAndExitButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxList;

    @FXML
    private VBox vboxTop;

    @FXML
    void sendMessageMethod(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uploadWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());//,450,250
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);//не дает обратиться назад
            stage.setTitle("Синхронизация с сервером");
            stage.setScene(scene);
            stage.showAndWait();

        }catch(IOException e){}
    }
    @FXML
    void addNewNote(ActionEvent event) {
        System.out.println("добавить в listview новый элемент" );
        addMyObject(new MyObject());

        System.out.println("qwerty");
    }

    private void addMyObject(MyObject myObject) {
        arrayList.add(myObject);
        listView.getItems().add(myObject.textField);
    }

    @FXML
    void button4OnClick(ActionEvent event) {

    }


    @FXML
    private ListView<String> listView;
    ArrayList<MyObject> arrayList;

    public ArrayList<MyObject> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<MyObject> arrayList) {
        this.arrayList = arrayList;
    }

    ObservableList<String> observableList = FXCollections.observableArrayList();
    @FXML
    void initialize() {
        vboxList.prefWidthProperty().bind(scrollPane.widthProperty());
        vboxList.prefHeightProperty().bind(scrollPane.heightProperty());
        vboxList.setAlignment(Pos.TOP_CENTER);

        ArrayList<Button> listOfButtons = new ArrayList<>();
        for (MyObject m : arrayList) {
            Button b = new Button(m.getTextField());
            b.setOnAction(actionEvent -> {
                openSelectedItem(m);
            });
            listOfButtons.add(b);
        }

        vboxList.getChildren().addAll(listOfButtons);

        arrayList.forEach(array -> System.out.println("элемент.getTextField()= {" + array.getTextField() + "}"));

        vboxList.setSpacing(10.2);
        scrollPane.setContent(vboxList);
    }



    private void openSelectedItem(MyObject myObject) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Note.fxml"));

            Scene scene = new Scene(fxmlLoader.load());//,450,250
            Stage stage = new Stage(StageStyle.DECORATED);

            NoteController controller = fxmlLoader.getController();
            System.out.println("Before controller.setStrings()");

            controller.setStrings(myObject);
            controller.setStage(stage);
            controller.setMyObject(myObject);

            stage.initModality(Modality.APPLICATION_MODAL);//не дает обратиться назад
            stage.setTitle("Заметка");
            stage.setScene(scene);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    myObject.textArea = controller.getTextArea().getText();
                    myObject.textField = controller.getTextField().getText();
                }
            });
            stage.showAndWait();
            myObject.textArea = controller.getTextArea().getText();
            myObject.textField = controller.getTextField().getText();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }












}




