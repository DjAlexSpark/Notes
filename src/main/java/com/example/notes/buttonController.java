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
        //HelloController.list2.remove(event.getTarget());
        HelloController.list2.removeIf(n -> (n.getText().equals(text)));

        System.out.println("deleteButton");
        stage = (Stage) stackPane.getScene().getWindow();
        stage.close();

        setTextEmpty();// setTextEmpty + updateNotesFromList2
    }

    @FXML
    void saveOnClickButton(ActionEvent event) {
        Param.buffer = String.valueOf(textArea.getText());
        stage = (Stage) stackPane.getScene().getWindow();
        stage.close();
        final var Button = (Button) event.getTarget();
        Button.setText(Param.buffer);
        System.out.println(((Button) event.getTarget()).getText());

        setTextEmpty();


    }



    @FXML
    void cancelOnClickButton(ActionEvent event) {
        stage = (Stage) stackPane.getScene().getWindow();
        stage.close();
        setTextEmpty();
    }

    Stage stage;
    String text="";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.text=Param.buffer;
        textArea.setText(text);
    }

    public void setTextEmpty() {
        this.text = "";
    }
}
