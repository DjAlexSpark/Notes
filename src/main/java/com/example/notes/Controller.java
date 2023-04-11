package com.example.notes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.notes.Main.getMyObjectsFrom;


public class Controller {

    @FXML
    private MenuItem aboutButton;

    @FXML
    private MenuItem addImageButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem deleteButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private MenuItem sendMessageButton;

    @FXML
    private VBox vboxList;

    @FXML
    private VBox vboxTop;


    @FXML
    void onActionAddImage(ActionEvent event){
    }
    @FXML
    void onActionSaveAndExit(ActionEvent event){
    }
    @FXML
    void closeNote(ActionEvent event){
    }
    @FXML
    void deleteNote(ActionEvent event){
    }
    @FXML
        void aboutNote(ActionEvent event){
    }

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
    ArrayList<MyObject> arrayList = getMyObjectsFrom(Path.of("C:\\Users\\33\\IdeaProjects\\Notes\\src\\main\\resources\\MyNotes"));

    @FXML
    void initialize() {
        vboxList.prefWidthProperty().bind(scrollPane.widthProperty());
        vboxList.prefHeightProperty().bind(scrollPane.heightProperty());
        vboxList.setAlignment(Pos.TOP_CENTER);
        //ArrayList<Button> listOfButtons = new ArrayList<>();
        Map<MyObject,Button> mapOfButtons = new HashMap<>();
        for (int i = 0; i < arrayList.size(); i++) {
            Button b = new Button(arrayList.get(i).getTextField());
            b.setPrefWidth(200);
            b.setPrefHeight(100);
            String s = "";
            b.setUserData(s);
            b.setOnAction(actionEvent -> {
                System.out.println("нажал ");
//                for (MyObject a : arrayList) {
//                    System.out.println(a.textField);
//                }
                System.out.println(b.getUserData());
            });
            vboxList.getChildren().add(b);
        }


        //vboxList.getChildren().addAll(listOfButtons);



        arrayList.forEach(array -> System.out.println("элемент.getTextField()= {" + array.getTextField() + "}"));

        vboxList.setSpacing(10.2);
        scrollPane.setContent(vboxList);



    }



    public void setArrayList(ArrayList<MyObject> arrayList) {
        this.arrayList = arrayList;
        System.out.println("setArrayList");
    }

    public ArrayList getArrayList() {
        return this.arrayList;
    }
}




