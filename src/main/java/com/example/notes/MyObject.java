package com.example.notes;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class MyObject implements Serializable {

    String textField;
    String textArea ;
    ArrayList<File> listOfImages;

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

    public ArrayList<File> getListOfImages() {
        return listOfImages;
    }

    public void setListOfImages(ArrayList<File> listOfImages) {
        this.listOfImages = listOfImages;
    }

    public MyObject() {
        this.textField = "НЕПустое сообщение";
        this.textArea = "Пустое сообщение";
        this.listOfImages = new ArrayList<>();

    }

    public MyObject(String textField, String textArea, ArrayList<File> listOfImages) {
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

