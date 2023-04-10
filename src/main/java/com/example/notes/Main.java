package com.example.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }
    ArrayList<MyObject> arrayList;
    @Override
    public void start(Stage primaryStage) throws Exception {
        arrayList = new ArrayList();
        arrayList=getMyObjectsFrom(Path.of("C:\\Users\\33\\IdeaProjects\\Notes\\src\\main\\resources\\MyNotes"));
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("1stPage.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        Controller controller = fxmlloader.getController();
        controller.setArrayList(arrayList);

        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Start");
        primaryStage.show();

    }
    public static ArrayList<MyObject> getMyObjectsFrom(Path path) {
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