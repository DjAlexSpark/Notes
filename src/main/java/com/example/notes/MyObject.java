package com.example.notes;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.HashMap;

public class MyObject implements Serializable {

    private String textField;
    private String textArea;
    transient  HashMap<String, Image> listOfImages;

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public MyObject(String textField, String textArea, HashMap<String, Image> listOfImages) {
        this.textField = textField;
        this.textArea = textArea;
        this.listOfImages = listOfImages;
    }

    public MyObject() {
        this.textField = "НЕПустое сообщение";
        this.textArea = "Пустое сообщение";
        this.listOfImages = new HashMap<>();

    }

    public MyObject(String textField, String textArea) {
        this.textField = textField;
        this.textArea = textArea;
        this.listOfImages = new HashMap<>();
    }

    public HashMap<String, Image> getListOfImages() {
        return listOfImages;
    }

    public void setListOfImages(HashMap<String, Image> listOfImages) {
        this.listOfImages = listOfImages;
    }

}

