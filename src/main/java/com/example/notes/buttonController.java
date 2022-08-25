package com.example.notes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class buttonController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private StackPane stackPane;

    @FXML
    private TextArea textArea;



    @FXML
    private Button deleteButton;

    @FXML
    void deleteOnAction(ActionEvent event) {
        System.out.println("deleteButton");
        stage = (Stage) stackPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveOnClickButton(ActionEvent event) {
        Param.buffer = String.valueOf(textArea.getText());
        stage = (Stage) stackPane.getScene().getWindow();
        stage.close();
        final var Button = (Button) event.getTarget();
        Button.setText(Param.buffer);
        System.out.println(((Button) event.getTarget()).getText());
        saveInArrayOfButtons();
    }

    private void saveInArrayOfButtons() {
        Param.notes.clear();
        Param.notes.addAll(HelloController.toStringList2());
    }

    @FXML
    void cancelOnClickButton(ActionEvent event) {
        stage = (Stage) stackPane.getScene().getWindow();
        stage.close();
    }

    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setText(Param.buffer);



    }
}
