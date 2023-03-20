package com.example.notes;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MyObject implements Serializable {

    String textField;
    String textArea ;
    ArrayList<String> listOfImages;

    public MyObject() {
        this.textField = "НЕПустое сообщение";
        this.textArea = "Пустое сообщение";
        this.listOfImages = new ArrayList<>();

    }

    public MyObject(String textField, String textArea, ArrayList<String> listOfImages) {
        this.textField = textField;
        this.textArea = textArea;
        this.listOfImages = listOfImages;
    }
    public MyObject(String textField, String textArea) {
        this.textField = textField;
        this.textArea = textArea;
        this.listOfImages = new ArrayList<>();
    }

}
