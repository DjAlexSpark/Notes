package com.example.notes;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.imageio.*;



public class Test {
    public static void main(String[] args) {
        ArrayList <MyObject> obj = fillArrayWithMyObjects(getDirectoriesIn(Path.of("C:\\Users\\33\\IdeaProjects\\NotesWithAdds\\src\\main\\resources\\MyNotes")));
        for (MyObject o:obj) {
            System.out.println(o.textArea+o.textField+o.listOfImages.toString());
        };
    }

    private static ArrayList<MyObject> fillArrayWithMyObjects(List<Path> directoriesIn) {
        ArrayList list = new ArrayList();
        for (Path path:directoriesIn) {
            list.add(getAnObjectFromPath(path));
        }
        return list;
        }



        public static MyObject getAnObjectFromPath(Path path){

        path = Path.of("C:\\Users\\33\\IdeaProjects\\NotesWithAdds\\src\\main\\resources\\MyNotes\\0");

        String textArea = "";
        String textField = "";
        ArrayList<String> imageList = new ArrayList<>();

        File textAreaFile = new File(path+"\\textArea.txt");
        File textFieldFile = new File(path+"\\textField.txt");
            BufferedReader reader = null;
        textArea = getStringFromFile(textAreaFile);
        textField = getStringFromFile(textFieldFile);
        imageList = getImagesFromPath(path);//todo


        return  new MyObject(textField,textArea,imageList);

/*
            ArrayList<MyObject> arr= new ArrayList<>();
            String textArea,textField;
            //todo получить картинки внутри папки
            for (Path path : directoriesIn) {


                //добавление к пути /Images
                File file = new File(path.toString());
                Path other = Path.of(path.toString().concat("\\Images"));
                System.out.println(other);//печатаем че получилось с Images

                if (Files.exists(other)){
                    //todo прогуляться внутри и вытащить только картинки в массив
                    System.out.println(true);

                    File fileqwe = new File(String.valueOf(other));
                    List files = new ArrayList();
                    for(File f: file.listFiles()){
                        if(f.isFile()){
                            System.out.println(f.getPath());
                            //new Image(f.getPath());
                            //todo здесь нужно привести "f"  к "Image" и пихнуть в массив с return

                        }
                    }

                }*/
        }

    private static ArrayList<String> getImagesFromPath(Path path) {
        ArrayList<String> list = new ArrayList<>();
        try {

            List<Path> result;
            result = (Files.walk(path)
                    .filter(p -> {
                        if (p.toFile().exists() & p.toFile().isFile()) {
                            BufferedImage input = null;
                            try {

                                input = ImageIO.read(
                                        new File(p.toUri()));
                                if(input!=null) {
                                    String s = input.getData().toString();
                                    System.out.println("картинка - "+s);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (input != null) {
                                return true;
                            }
                        }

                        return false;
                    }).collect(Collectors.toList()));
            for (Path p:result) {
                list.add(p.toAbsolutePath().toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static String getStringFromFile(File file){
            BufferedReader reader = null;
            String currentFile = "";
            if(file.exists()){
                try {
                    reader = new BufferedReader(new FileReader(file));
                    currentFile = reader.readLine();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        return currentFile;
        }

    public static List<Path> getDirectoriesIn(Path path) {
            List<Path> result = new ArrayList();
            try (Stream<Path> walk = Files.walk(path,1)) {
                result = walk.filter(Files::isDirectory)
                        .collect(Collectors.toList());

            }catch (Exception e){
                e.printStackTrace();
            }
            result.remove(0);
            return result;

    }
}
