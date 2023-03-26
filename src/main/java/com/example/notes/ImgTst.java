package com.example.notes;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.*;



public class ImgTst {
    public static void main(String[] args) {
        ArrayList<MyObject>listOfMyObjects = new ArrayList<>();

        Path directories = Path.of("C:\\Users\\33\\IdeaProjects\\NotesWithAdds\\src\\main\\resources\\MyNotes");

        ArrayList<File> listofImages=new ArrayList<>();
        String textField="";
        String textArea="";
        System.out.println("start taking paths... ");
        for (Path path:directories) {//walking on 1st level directory
            textArea = path.resolve("textArea.txt").toString();
            textField = path.resolve("textField.txt").toString();
            try {
                List<Path> pathOfImages = Files.walk(path.resolve("\\Images")).filter(p->//add "\\Images" to the path and walking in every folder in Images and getting images
                {
                    System.out.println("pathOfImages");
                    try {
                        BufferedImage image = ImageIO.read(new File(p.toUri()));
                        System.out.println("got image");
                        return true;
                    } catch (IOException e) {
                        System.out.println("Не изображение");
                        return false;
                    }
                }).toList();
                for (Path p :pathOfImages) {
                    listofImages.add(new File(p.toUri()));
                }

            } catch (IOException e) {
                System.out.println("не пугайся");
                e.printStackTrace();

            }
            listOfMyObjects.add(new MyObject(textField,textField,listofImages));
        }
        System.out.println(listOfMyObjects.size());
        for (MyObject myObject:listOfMyObjects) {
            System.out.println(myObject.textField);
        }

        /*Foreach path
        textField = textField
        textArea = textArea
            getPathofImages from path

            foreach path of image
            listofImages.add(new File(path of image))
           }
           new MyObject(textfiedl,textare,arrayofimages)
         }
         */








//       try {
//                var s = Files.walk(directories);
//                s.forEach(System.out::println);
//            System.out.println((Files.walk(directories,1)).filter(path -> !path.equals(directories)).toList());
//            for (Path path : ((Files.walk(directories, 1)).filter(path -> !path.equals(directories)).toList())) {
////                System.out.println(path);
//                Files.walk(path).filter(p -> !p.equals(path)).forEach(System.out::println);

//                try  {
//                    List list = (Files.list(path).filter(p->p.endsWith("textField")|p.endsWith("textArea"))).toList();

//                listOfMyObjects.add(new MyObject());
//            }catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }

    }

    }


