package com.example.notes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TextField textField;

    @FXML
    private VBox vboxList;

    @FXML
    private VBox vboxTop;

    @FXML
    void aboutNote(ActionEvent event) {

    }

    @FXML
    void closeNote(ActionEvent event) {

    }

    @FXML
    void deleteNote(ActionEvent event) {

    }

    @FXML
    void onActionAddImage(ActionEvent event) {

    }

    @FXML
    void onActionSaveAndExit(ActionEvent event) {

    }
    ArrayList<MyObject> array = getMyObjectsFrom(Path.of("C:\\Users\\33\\IdeaProjects\\Notes\\src\\main\\resources\\MyNotes"));


    @FXML
    void initialize() {
        vboxList.prefWidthProperty().bind(scrollPane.widthProperty());
        vboxList.prefHeightProperty().bind(scrollPane.heightProperty());
        vboxList.setAlignment(Pos.TOP_CENTER);

        ArrayList<Button> listOfButtons = new ArrayList<>();
        for (MyObject m : array) {
            Button b = new Button(m.getTextField());
            b.setOnAction(actionEvent -> {
                for (MyObject a : array) {
                    System.out.println(a.textField);
                }
                System.out.println("button = " + b.getText());
            });

            listOfButtons.add(b);


        }

        vboxList.getChildren().addAll(listOfButtons);



        array.forEach(array -> System.out.println("элемент.getTextField()= {" + array.getTextField() + "}"));

        vboxList.setSpacing(10.2);
        scrollPane.setContent(vboxList);



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
                    //чтение файла для java 8
                    /*try {

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
                            textFieldSB.append(textFieldLine); //.append("\n");
                        }

                        textAreaContents = textAreaSB.toString();
                        textFieldContents = textFieldSB.toString();

                        textAreaBR.close(); // не забудьте закрыть поток
                        textFieldBR.close(); // не забудьте закрыть поток
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }*/
                    textAreaContents=Files.readString(p.resolve("textArea.txt"));
                    textFieldContents=Files.readString(p.resolve("textField.txt"));
                    //todo не АААААААААААААА!
                    System.out.println(textAreaContents+textFieldContents);
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



}