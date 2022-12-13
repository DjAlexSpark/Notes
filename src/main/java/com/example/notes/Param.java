package com.example.notes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Param {
    private static Path path = Path.of("src/main/resources/com/example/notes/Notes.txt");
    public static int i;
    public static String buffer = "new note";
    public static ArrayList<String> notes = new ArrayList();

    public static void saveInFile(ArrayList<String> arrayList){
        try {
            Files.write(path,notes);
            System.out.println("Savin notes in file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public static void checkNotesFile(){

        if (!Files.exists(path)){
            try {
                Files.createFile(path);
                System.out.println("file created");
            } catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        }else {
            System.out.println("file notes exists");
        }

        List<String> list = null;
        try {
            list = Files.readAllLines(path);
            if (list.isEmpty()) {
//                for (String str : list)
//                    System.out.println(str);
//            }else{
                System.out.println("List is empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!list.isEmpty()){
            Param.notes.addAll(list);
        }
//        for (String s : Param.notes)
//            System.out.println(s);
    }

}