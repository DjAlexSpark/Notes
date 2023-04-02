package com.example.notes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem aboutButton;

    @FXML
    private MenuItem addImageButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem closeButton;

    @FXML
    private MenuItem deleteButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem saveAndExitButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField textField;

    @FXML
    private VBox vboxList;

    @FXML
    private VBox vboxTop;

    @FXML
    void aboutNote(ActionEvent event) {

    }

    @FXML
    void closeNote(ActionEvent event) {

    }

    @FXML
    void deleteNote(ActionEvent event) {

    }

    @FXML
    void onActionAddImage(ActionEvent event) {

    }

    @FXML
    void onActionSaveAndExit(ActionEvent event) {

    }

    @FXML
    void initialize() {
        Button button = new Button("qweasdzxc");
        Button button2 = new Button("qweasdzxc");
        Button button3 = new Button("qweasdzxc");
        Button button4 = new Button("qweasdzxc");
        Button button5 = new Button("qweasdzxc");
        Button button6 = new Button("qweasdzxc");
        Button button7 = new Button("qweasdzxc");
        Button button8 = new Button("qweasdzxc");
        vboxList.getChildren().addAll(button, button2, button3, button4, button5, button6, button7, button8);
        for (Node b : vboxList.getChildren()) {

        }
        vboxList.setAlignment(Pos.CENTER);
        vboxList.setSpacing(10.2);
        scrollPane.setContent(vboxList);


    }

}
