package com.example.notes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NoteController {


    @FXML
    private MenuItem aboutButton;

    @FXML
    private MenuItem addImageButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button buttonFolder;

    @FXML
    private MenuItem closeButton;

    @FXML
    private MenuItem deleteButton;

    @FXML
    private HBox hboxForButtons;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem saveAndExitButton;

    @FXML
    private Button saveButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField;

    @FXML
    private VBox vboxTop;

    @FXML
    void onActionAddImage(ActionEvent event) {
        //todo call directory and file _choser
    }
    @FXML
    void aboutNote(ActionEvent event) {
        //todo easter egg with about instruction
    }

    @FXML
    void closeNote(ActionEvent event) {
        this.textArea.setText(onClosedTextArea.getText());
        this.textField.setText(onClosedTextField.getText());
        borderPane.getScene().getWindow().hide();
    }

    @FXML
    void deleteNote(ActionEvent event) {
        System.out.println("deleted");

    }

    public TextArea getTextArea() {
        return textArea;
    }

    public TextField getTextField() {
        return textField;
    }

    @FXML
    void onActionSaveAndExit(ActionEvent event) {
        borderPane.getScene().getWindow().hide();
    }
    private TextArea onClosedTextArea = new TextArea("");
    private TextField onClosedTextField = new TextField("");

     private MyObject myObject;
    private Stage stage;


    @FXML
    void onActionOpenFolder(ActionEvent event) {
    //todo on action open contained folder
    }

    public void setMyObject(MyObject myObject) {
        this.myObject = myObject;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void initialize() {
        System.out.println("initialize");
        borderPane.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != vboxTop
                        && dragEvent.getDragboard().hasFiles()) {
                    /* allow for both copying and moving, whatever user chooses */
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                dragEvent.consume();
            }
        });
        borderPane.setOnDragDropped(new EventHandler<DragEvent>() {

                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasFiles()) {
                        //todo add Image in ImageBoard
                        textField.setText(db.getFiles().toString());
                        success = true;
                    }
                    /* let the source know whether the string was successfully
                     * transferred and used */
                    event.setDropCompleted(success);
                    event.consume();
            }
        });
    }

    public void setStrings(MyObject myObject){
        this.textArea.setText(myObject.textArea);
        this.textField.setText(myObject.textField);
        this.onClosedTextArea.setText(myObject.textArea);
        this.onClosedTextField.setText(myObject.textField);
        //myObject.listOfImages
        ArrayList arr = new ArrayList();
        for (Image i:myObject.listOfImages){
            arr.add(new ImageView(i));
            //todo чет не то
        }
        //todo сначала закончи с импортом заметок в MyObjects потом тут добавляй картинки

//        Image img = new Image(getClass().getResourceAsStream("img.PNG"));
//        Image img2 = new Image(getClass().getResourceAsStream("img2.PNG"));
//        Image img3 = new Image(getClass().getResourceAsStream("img3.PNG"));
//        ImageView imgView = new ImageView(img);
//        ImageView imgView2 = new ImageView(img2);
//        ImageView imgView3 = new ImageView(img3);
//        HBox hboxImages = new HBox();
//        hboxImages.getChildren().add(imgView);
//        hboxImages.getChildren().add(imgView2);
//        GridPane gridPane = new GridPane();
//        gridPane.add(imgView,1,1);
//        gridPane.add(imgView2,0,1);
//        gridPane.add(imgView3,2,1);



//        imgView.setPreserveRatio(true);
//        imgView2.setPreserveRatio(true);
//        imgView3.setPreserveRatio(true);
//        imgView.setFitHeight(100);
//        imgView2.setFitHeight(100);
//        imgView3.setFitHeight(100);

        //bP.setBottom(button);

        //scrollPane.setContent(hboxImages);
//        scrollPane.setContent(gridPane);
//        hboxImages.setFillHeight(true);
        //
    }



}
