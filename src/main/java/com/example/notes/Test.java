package com.example.notes;

import javafx.scene.image.Image;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Test {
    public static void main(String[] args) {
        ArrayList <MyObject> obj = fillArrayWithMyObjects(getDirectoriesIn(Path.of("C:\\Users\\33\\IdeaProjects\\NotesWithAdds\\src\\main\\resources\\MyNotes")));


    }

    private static ArrayList<MyObject> fillArrayWithMyObjects(List<Path> directoriesIn) {
        ArrayList list = new ArrayList();
        for (Path path:directoriesIn) {
            list.add(getAnObjectFromPath(path));
        }
        return null;
        }



        public static MyObject getAnObjectFromPath(Path path){

        path = Path.of("C:\\Users\\33\\IdeaProjects\\NotesWithAdds\\src\\main\\resources\\MyNotes\\0");

        String textArea = "";
        String textField = "";
        ArrayList<Image>imageList = new ArrayList<>();

        File textAreaFile = new File(String.valueOf(path));
            BufferedReader reader = null;
        if(textAreaFile.exists()){
            try {
                //todo кароч не читает файл из пути
                reader = new BufferedReader(new FileReader(textAreaFile));;
                String currentLine = reader.readLine();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }





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

    public static List<Path> getDirectoriesIn(Path path) {
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
