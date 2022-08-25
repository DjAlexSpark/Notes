package com.example.notes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HelloController implements Initializable {


    @FXML
    private BorderPane borderPane;

    @FXML
    private Button button;

    @FXML
    private ListView<Button> list;

    private void addNotesToList2() {
        for (String note: Param.notes) {
            list2.add(new Button(note));
        }
        for (Button b: list2) {
            b.setPrefWidth(borderPane.getWidth()-18);

            b.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Param.buffer = ((Button) actionEvent.getTarget()).getText();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("noteWindow.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());//,450,250
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);//не дает обратиться назад
                        stage.setTitle("Заметка");
                        stage.setScene(scene);
                        stage.showAndWait();
                        ((Button) actionEvent.getTarget()).setText(Param.buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        list.getItems().setAll(list2);
    }

    @FXML
    void onClickAddButton(ActionEvent event) {
        button.setStyle("-fx-background-color: #ff0000 ");
        Param.notes.add(Param.buffer);
        Param.buffer = Param.notes.get(i);
        list2.add(new Button("Стартуем"));
        list2.get(list2.size()-1).setPrefWidth(borderPane.getWidth()-18);
        list2.get(list2.size()-1).setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Param.buffer = ((Button) actionEvent.getTarget()).getText();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("noteWindow.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());//,450,250
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);//не дает обратиться назад
                    stage.setTitle("Заметка");
                    stage.setScene(scene);
                    stage.showAndWait();
                    ((Button) actionEvent.getTarget()).setText(Param.buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        list.getItems().setAll(list2);
    }



    public static ArrayList<Button> list2;
    int i=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button.setStyle("-fx-background-color: #0000ff ");
        list.setStyle("-fx-alignment: center");

        list2= new ArrayList<>();
        addNotesToList2();

        borderPane.widthProperty().addListener((observableValue, oldValue, newValue) -> {
            for (Button b : list2) {
                b.setPrefWidth(borderPane.getWidth()-18); }
        });

        //borderPane.setPrefWidth(350);

    }



    public static ArrayList<String> toStringList2(){
        ArrayList<String> arr= new ArrayList<>();
        for (int i = 0; i < list2.size()-1; i++) {
            arr.add(list2.get(i).getText());
        }
        return arr;
    }

    public void onZoomed(){

        for (Button button: list2) {
            button.setPrefWidth(list.getWidth()-18);
        }
    }
}
