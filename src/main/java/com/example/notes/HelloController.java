package com.example.notes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menu1;

    @FXML
    private Menu menu2;

    @FXML
    private MenuItem instructionButton;

    @FXML
    private MenuItem uploadButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button button;

    public static void list2ToNotes() {
        Param.notes.clear();
        for (Button b:list2) {
            Param.notes.add(b.getText());
        }
    }




    private void addNotesToList2() {
        for (String note: Param.notes) {
            list2.add(new Button(note));
        }
        for (int i = 0; i <list2.size(); i++) {
            list2.get(i).setPrefWidth(borderPane.getWidth());
            list2.get(i).setOnAction(actionEvent -> {
                try {
                    Param.buffer = ((Button) actionEvent.getTarget()).getText();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("noteWindow.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());//,450,250
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);//не дает обратиться назад
                    stage.setTitle("Заметка");
                    stage.setScene(scene);
                    stage.setOnHidden(event ->{
                        String s = String.valueOf(((((Stage)(event.getTarget())).getUserData())));
                        System.out.println(s);
                        if(s=="d"){
                            vboxForButtons.getChildren().setAll(list2);
                        }
                    });
                    stage.showAndWait();
                    ((Button) actionEvent.getTarget()).setText(Param.buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        }
        vboxForButtons.getChildren().setAll(list2);

    }

    @FXML
    void onClickAddButton(ActionEvent event) {

        list2.add(new Button("Стартуем"));
        list2.get(list2.size()-1).setPrefWidth(vboxForButtons.getWidth());

        list2.get(list2.size()-1).setOnAction(actionEvent -> {
            try {
                Param.buffer = ((Button) actionEvent.getTarget()).getText();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("noteWindow.fxml"));
                Scene scene = new Scene(fxmlLoader.load());//,450,250
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);//не дает обратиться назад
                stage.setTitle("Заметка");
                stage.setScene(scene);
                stage.setOnHidden(event1 ->{
                    String s = String.valueOf(((((Stage)(event1.getTarget())).getUserData())));
                    System.out.println(s);
                    if(s=="d"){

                        vboxForButtons.getChildren().setAll(list2);
                    }
                });
                stage.showAndWait();
                ((Button) actionEvent.getTarget()).setText(Param.buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        vboxForButtons.getChildren().clear();
        vboxForButtons.getChildren().addAll(list2);

    }

    @FXML
    private ScrollPane scrollPanewithVbox;

    @FXML
    private VBox vboxForButtons;

    public static ArrayList<Button> list2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vboxForButtons.setFillWidth(true);
        scrollPanewithVbox.setFitToWidth(true);
        uploadButton.setOnAction(event-> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("uploadWindow.fxml"));
                Scene scene = new Scene(fxmlLoader.load());//,450,250
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);//не дает обратиться назад
                stage.setTitle("Синхронизация с сервером");
                stage.setScene(scene);
                stage.showAndWait();

            }catch(IOException e){}
        });
        instructionButton.setOnAction(event -> {
            System.out.println("сделай окно с инструкцией");

        });

        list2= new ArrayList<Button>();
        addNotesToList2();

        borderPane.widthProperty().addListener((observableValue, oldValue, newValue) -> {
            for (Button b : list2) {
                b.setPrefWidth(vboxForButtons.getWidth()-2);
            }
        });

    }

}