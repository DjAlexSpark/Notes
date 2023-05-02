package com.example.notes;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class MyObject implements Serializable {

    String textField;
    String textArea;
    ArrayList<Image> listOfImages;

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

    public MyObject(String textField, String textArea, ArrayList<Image> listOfImages) {
        this.textField = textField;
        this.textArea = textArea;
        this.listOfImages = listOfImages;
    }

    public ArrayList<Image> getListOfImages() {
        return listOfImages;
    }

    public MyObject() {
        this.textField = "НЕПустое сообщение";
        this.textArea = "Пустое сообщение";
        this.listOfImages = new ArrayList<>();

    }

    public void setListOfImages(ArrayList<Image> listOfImages) {
        this.listOfImages = listOfImages;
    }

    public MyObject(String textField, String textArea) {
        this.textField = textField;
        this.textArea = textArea;
        this.listOfImages = new ArrayList<>();
    }

}

