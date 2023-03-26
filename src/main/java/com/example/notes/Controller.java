package com.example.notes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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



    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    Path path = Path.of("src/main/resources/MyNotes");
    ObservableList<String> observableList;
    @FXML
    void initialize() {



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
        }

    }


    public ArrayList<MyObject> getMyObjectsFrom(Path path) {
        //begin
        try {
            //path = path.of(\\MyNotes)
            ArrayList<MyObject> list = new ArrayList<>();

            List<Path> folders = Files.list(path).toList();
            File textField;
            File textArea;
            List<File> files;
            for (Path p : folders) {

                //вместо имени файла, нужно содержимое
                textField = new File(p.resolve("textField.txt").toUri());
                textArea = new File(p.resolve("textArea.txt").toUri());


                System.out.println(textArea.getAbsolutePath() + " " +
                        textField.getAbsolutePath());
                ArrayList<File> images = new ArrayList<>();
                Path imgPath = p.resolve(Paths.get("Images"));
                System.out.println(imgPath);
                try {
                    List<Path> paths = Files.walk(imgPath)
                            .filter(Files::isRegularFile)
                            .filter(p1 -> p1.toString().endsWith(".jpg") || p1.toString().endsWith(".png"))
                            .collect(Collectors.toList());

                    files = new ArrayList<File>();
                    for (Path path1 : paths) {
                        files.add(path1.toFile());
                        System.out.println(path1);
                    }

                    String textFieldContents = null;
                    String textAreaContents = null;
                    try {

                        BufferedReader textAreaBR = new BufferedReader(new InputStreamReader(new FileInputStream(textArea)));
                        BufferedReader textFieldBR = new BufferedReader(new InputStreamReader(new FileInputStream(textField)));

                        StringBuilder textAreaSB = new StringBuilder();
                        StringBuilder textFieldSB = new StringBuilder();
                        String textAreaLine;
                        String textFieldLine;

                        while ((textAreaLine = textAreaBR.readLine()) != null) {
                            textAreaSB.append(textAreaLine).append("\n");
                        }
                        while ((textFieldLine = textFieldBR.readLine()) != null) {
                            textFieldSB.append(textFieldLine).append("\n");
                        }

                        textAreaContents = textAreaSB.toString();
                        textFieldContents = textFieldSB.toString();


                        textAreaBR.close(); // не забудьте закрыть поток
                        textFieldBR.close(); // не забудьте закрыть поток
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    //todo не АААААААААААААА!
                    list.add(new MyObject(textAreaContents, textFieldContents, images));


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            //для каждого пути найти файлы и создать MyObject
            //создать text и приравнять

            return list;
            //end
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void fillArray() {
        setArrayList(getMyObjectsFrom(getPath()));
        beforeStart();
    }

    public void fillArray(Path path) {
        setArrayList(getMyObjectsFrom(path));
        beforeStart();
    }

    public void writeArray() {
        System.out.println("wrote");
    }

    public void beforeStart() {
        for (MyObject object : arrayList) {
            observableList.add(object.textField);
        }

        listView.setItems(observableList);

        listView.setOnMouseClicked(eventHandler -> {
            if (listView.getSelectionModel().getSelectedIndex() >= 0) {
                openSelectedItem(arrayList.get(listView.getSelectionModel().getSelectedIndex()));
                System.out.println(arrayList.size() + "" + listView.getItems().size());
            }
        });

        //получаем текст выбранного элемента(не работает если текст одинаковый)
        MultipleSelectionModel<String> langsSelectionModel = listView.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {
                System.out.println("Selected: " + newValue);
            }
        });

    }
}




