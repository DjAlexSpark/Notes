package com.example.notes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
//resources and location needed implements Initializable
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem aboutButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button button4;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private Button buttonPlus;


    @FXML
    private MenuItem quitButton;

    @FXML
    private MenuItem sendMessageButton;

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
        //todo save my notes;


        saveMyNotesNow();
    }


    @FXML
    private ListView<String> listView;
    ArrayList<MyObject> arrayList;
    ObservableList<String> observableList;
    Path path = Path.of("src/main/resources/MyNotes");
    @FXML
    void initialize() {
        arrayList = new ArrayList<>();//getAllNotes // MyObject содержит 'public String textField,textArea')

        observableList = FXCollections.observableArrayList();
        listView.setItems(observableList);
        addMyObject(new MyObject());

        listView.setOnMouseClicked(eventHandler->{
        if (listView.getSelectionModel().getSelectedIndex()>=0) {
            openSelectedItem(arrayList.get(listView.getSelectionModel().getSelectedIndex()));
            System.out.println(arrayList.size() + "" + listView.getItems().size());
        }
        });

        //получаем текст выбранного элемента(не работает если текст одинаковый)
        MultipleSelectionModel<String> langsSelectionModel = listView.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
                System.out.println("Selected: " + newValue);
            }
        });
        System.out.println(arrayList.size()+""+listView.getItems().size());

    }

    private ArrayList getAllNotes(Path path) {
        //todo найти все заметки и вытащить их в массив
        //ищем папку с записями
        //добавляем каждую папку с записями в массив переводя
        ArrayList array = new ArrayList();


//        for (File file : files) {
//            if (file.isDirectory()) {
//                System.out.println("Directory: " + file.getAbsolutePath());
//                showFiles(file.listFiles()); // Calls same method again.
//            } else {
//                System.out.println("File: " + file.getAbsolutePath());
//            }

        return new ArrayList<>();
    }

    private void openSelectedItem(MyObject myObject) {
        //todo создать окно с этими textfield и textarea
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

        }catch(IOException e){}

    }



    public void saveMyNotesNow(){

    }
    public List<Path> getDirectoriesIn(Path path) {
        List<Path> result = new ArrayList();
        try (Stream<Path> walk = Files.walk(path,1)) {
            result = walk.filter(Files::isDirectory)
                    .collect(Collectors.toList());
        }catch (Exception e){

        }
        result.remove(0);
        return result;
    }
}




