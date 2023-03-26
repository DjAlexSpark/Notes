package com.example.notes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestA {
    public static void main(String[] args) {
        ArrayList<MyObject> listOfMyObjects = new ArrayList<>();

        Path directory = Paths.get("C:\\Users\\33\\IdeaProjects\\NotesWithAdds\\src\\main\\resources\\MyNotes");

        System.out.println("Start taking paths...");
        try {
            Files.walk(directory)
                    .filter(p -> p.toFile().isDirectory())
                    .forEach(path -> {

                        String textAreaFilename = path.resolve("textArea.txt").toString();
                        String textFieldFilename = path.resolve("textField.txt").toString();
                        File textAreaFile = new File(textAreaFilename);
                        File textFieldFile = new File(textFieldFilename);

                        ArrayList<File> listofImages = new ArrayList<>();
                        try {
                            List<Path> pathOfImages = Files.walk(path.resolve("Images"))
                                    .filter(p -> {
                                        try {
                                            BufferedImage image = ImageIO.read(p.toFile());
                                            return image != null;
                                        } catch (IOException e) {
                                            System.out.println(e.getMessage());
                                            return false;
                                        }
                                    })
                                    .collect(Collectors.toList());
                            for (Path p : pathOfImages) {
                                listofImages.add(p.toFile());
                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                        listOfMyObjects.add(new MyObject(textAreaFilename, textFieldFilename, listofImages));
                    });
        } catch (IOException e) {

        }

        System.out.println(listOfMyObjects.size());
        for (MyObject myObject : listOfMyObjects) {
            System.out.println(myObject.textField);
        }
    }
}
