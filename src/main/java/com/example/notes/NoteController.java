package com.example.notes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;

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
    private HBox hboxforButtons;

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
    private HBox hboxforContent;



    @FXML
    void onActionAddImage(ActionEvent event) {
        //todo call directory and file _choser
    }
    @FXML
    void aboutNote(ActionEvent event) {
        //todo easter egg with about instruction
    }

    private MyObject myObject;

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
    private final TextArea onClosedTextArea = new TextArea("");
    private final TextField onClosedTextField = new TextField("");

    @FXML
    void closeNote(ActionEvent event) {
        this.textArea.setText(onClosedTextArea.getText());
        this.textField.setText(onClosedTextField.getText());
        borderPane.getScene().getWindow().hide();

    }
    private Stage stage;


    @FXML
    void onActionOpenFolder(ActionEvent event) {
    //todo on action open contained folder
    }

    public void setMyObject(MyObject myObject) {
        this.myObject = myObject;
        this.textArea.setText(myObject.getTextArea());
        this.textField.setText(myObject.getTextField());
        this.onClosedTextArea.setText(myObject.getTextArea());
        this.onClosedTextField.setText(myObject.getTextField());
        try {
            if (!myObject.listOfImages.isEmpty()) {
                for (Map.Entry<String, Image> entry : myObject.listOfImages.entrySet()) {
                    String key = entry.getKey();
                    Image value = entry.getValue();
                    ImageView imageView = new ImageView(value);
                    imageView.setOnMouseClicked(actionEvent -> {
                        try {
                            VBox root = new VBox();
                            root.setPrefSize(600, 400);
                            root.setAlignment(Pos.CENTER);
                            imageView.setPreserveRatio(true);
                            imageView.setSmooth(false);
                            imageView.fitHeightProperty().bind(root.heightProperty());
                            imageView.fitWidthProperty().bind(root.widthProperty());
                            root.getChildren().addAll(imageView);

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setTitle("Image");
                            stage.setScene(scene);
                            stage.showAndWait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    imageView.setFitHeight(125);

                    imageView.setPreserveRatio(true);
                    hboxforContent.getChildren().add(imageView);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    String imagePath = ("C:\\Users\\33\\IdeaProjects\\Notes\\src\\main\\resources\\MyNotes\\0\\Images\\ed3f4e3ecc8044a7d31b1e421a281433.jpg");
    @FXML
    void initialize() {
        hboxforContent.setSpacing(20.0);
        //hboxforContent.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        System.out.println("initializeNote");


        try {
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
                        // Load the file into an image
                        File file = db.getFiles().get(0);
                        Image image = new Image(file.toURI().toString());

                        myObject.getListOfImages().put(file.getName(), image);
                        // Add the image to an ImageView and the HBox
                        ImageView imageView = new ImageView(image);
                        imageView.setOnMouseClicked(actionEvent->{
                            try{
                                VBox root = new VBox();
                                root.setPrefSize(600,400);
                                root.setAlignment(Pos.CENTER);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(false);
                                imageView.fitHeightProperty().bind(root.heightProperty());
                                imageView.fitWidthProperty().bind(root.widthProperty());
                                root.getChildren().addAll(imageView);

                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setTitle("Image");
                                stage.setScene(scene);
                                stage.showAndWait();
                            }catch(Exception e){e.printStackTrace();}
                        });
                        imageView.setFitHeight(hboxforContent.getHeight());
                        imageView.setPreserveRatio(true);
                        hboxforContent.getChildren().add(imageView);

                        //todo здесь импорт изображений из arr(HashMap<String, Image>) в imageView
                        success = true;
                    }

            /* let the source know whether the string was successfully
            transferred and used */
                    event.setDropCompleted(success);
                    event.consume();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setStrings(MyObject myObject){



    }


    public MyObject getMyObject() {
        return myObject;
    }
}
