package hkr.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXMLController implements Initializable {


    @FXML
    private Button personList_btn, sportList_btn;

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sportList_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    moveToSportList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        personList_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    moveTpPersonList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void moveToSportList() throws Exception{
        Stage stage;
        Parent root;
        stage = (Stage) sportList_btn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("sport_table.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void moveTpPersonList() throws Exception{
        Stage stage;
        Parent root;
        stage = (Stage) personList_btn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("person_table.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;

    }
}