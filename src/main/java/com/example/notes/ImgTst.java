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



public class ImgTst {
    public static void main(String[] args) {
        ArrayList<MyObject>listOfMyObjects = new ArrayList<>();

        Path directories = Path.of("C:\\Users\\33\\IdeaProjects\\NotesWithAdds\\src\\main\\resources\\MyNotes");

        try {

//            System.out.println((Files.walk(directories,1)).filter(path -> !path.equals(directories)).toList());
            for (Path path : ((Files.walk(directories, 1)).filter(path -> !path.equals(directories)).toList())) {
//                System.out.println(path);
                Files.walk(path).filter(p -> !p.equals(path)).forEach(System.out::println);

//                try  {
//                    List list = (Files.list(path).filter(p->p.endsWith("textField")|p.endsWith("textArea"))).toList();
//
//
//
//
//                listOfMyObjects.add(new MyObject());
//            }catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
