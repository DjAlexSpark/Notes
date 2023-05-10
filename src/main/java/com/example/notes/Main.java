package com.example.notes;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main extends Application {
    ArrayList<MyObject> arrayList;
    Path path = Path.of("C:\\Users\\33\\IdeaProjects\\Notes\\src\\main\\resources\\MyNotes");
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("1stPage.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                Controller controller = new Controller();
                controller.setArrayList(arrayList);
                return controller;
            }
        });

        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();
        arrayList=getMyObjectsFrom(path);
        //todo find Notes, if no - create objectives

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        writeMyObjectsTo(arrayList,Path.of("C:\\Users\\33\\IdeaProjects\\Notes\\src\\main\\resources\\TryMyNotes"));
        //todo save array

    }

    public static ArrayList<MyObject> getMyObjectsFrom(Path path) {
        //begin

            //path = path.of(\\MyNotes)
            ArrayList<MyObject> list = new ArrayList<>();

        try {
            List<Path> folders = Files.list(path).toList();
            File textField;
            File textArea;

            List<File> files = new ArrayList<File>();
            for (Path p : folders) {

                //вместо имени файла, нужно содержимое
                textField = new File(p.resolve("textField.txt").toUri());
                textArea = new File(p.resolve("textArea.txt").toUri());


                System.out.println(textArea.getAbsolutePath() + " " +
                        textField.getAbsolutePath());
                HashMap<String, Image> images = new HashMap<>();
                Path imgPath = p.resolve(Paths.get("Images"));
                System.out.println(imgPath);
                try {
                    List<Path> paths = Files.walk(imgPath)
                            .filter(Files::isRegularFile)
                            .filter(p1 -> p1.toString().endsWith(".jpg") || p1.toString().endsWith(".png") || p1.toString().endsWith(".gif"))
                            .collect(Collectors.toList());


                    for (Path path1 : paths) {
                        files.add(path1.toFile());
                        System.out.println("изображение: " + path1);
                        images.put(path1.getFileName().toString(), new Image(path1.toString()));

                    }

                    String textFieldContents = null;
                    String textAreaContents = null;

                    textAreaContents=Files.readString(p.resolve("textArea.txt"));
                    textFieldContents=Files.readString(p.resolve("textField.txt"));

                    System.out.println(textAreaContents+textFieldContents);
                    list.add(new MyObject(textAreaContents, textFieldContents, images));

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            return list;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean writeMyObjectsTo(ArrayList<MyObject> arrayList, Path path){
        boolean isSaved = false;
        try {
            //Files.deleteIfExists(path);
            for (int i = 0; i < arrayList.size(); i++) {
                Path currentDirectory = path.resolve(String.valueOf(i));
                if (!Files.exists(currentDirectory)) {
                    Files.createDirectory(currentDirectory);
                }
                Files.write(currentDirectory.resolve("textField.txt"), arrayList.get(i).getTextField().getBytes(StandardCharsets.UTF_8));
                Files.write(currentDirectory.resolve("textArea.txt"), arrayList.get(i).getTextArea().getBytes(StandardCharsets.UTF_8));
                try {
                    HashMap<String, Image> imageMap = arrayList.get(i).getListOfImages();
                    Path imagesPath = currentDirectory.resolve("Images");
                    if (!Files.exists(imagesPath)) {
                        Files.createDirectory(imagesPath);
                    }

                    //todo перебить с листа на HashMap
                    for (Map.Entry<String, Image> entry : imageMap.entrySet()) {
                        String name = entry.getKey();
                        Image o = entry.getValue();
                        //todo получать расширение файла из name
                        String extension = name.substring(name.lastIndexOf(".") + 1);
                        File outputFile = new File(o.getUrl());
                        try (FileOutputStream out = new FileOutputStream(String.valueOf(imagesPath.resolve(outputFile.getName())))) {
                            BufferedImage bufferedImage =
                                    SwingFXUtils.fromFXImage(o, null);
                            //todo просмотреть этот участок кода перед тем как Запускать!!!!!
                            ImageIO.write(bufferedImage, extension, out);
                            System.out.println("записал изображения");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            } catch (IOException e) {
            e.printStackTrace();
        }

        return isSaved;
    }
}