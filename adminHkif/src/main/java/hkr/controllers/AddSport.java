package hkr.controllers;

import hkr.database.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSport implements Initializable {

    @FXML private TextField addSportText, addDescriptionText;
    @FXML private ComboBox<String> sportAvailableBox, sportLocationBox;
    @FXML private Pane upperBar;
    private double x, y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sportAvailableBox.setItems(addAvailableList());
        sportLocationBox.setItems(addLocationList());
    }

    @FXML private void insertNewSport(){
       boolean added =  new DatabaseConnector().insertNewValuesIntoSport(addSportText.getText(), addDescriptionText.getText(),
                sportAvailableBox.getValue(), sportLocationBox.getValue());

       if (added){
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("New event");
           alert.setHeaderText("A new item has been added successfully.");
           alert.showAndWait();
       }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Failed to insert");
           alert.setHeaderText("Please fill all fields");
           alert.showAndWait();
       }
    }

    private ObservableList<String> addAvailableList(){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("AVAILABLE");
        list.add("NOT AVAILABLE");

        return list;
    }

    private ObservableList<String> addLocationList(){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Campus");
        list.add("City-Center");

        return list;
    }

    @FXML private void onUpperBarDragged(MouseEvent event) {
        Popup stage = (Popup) upperBar.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML private void onUpperBarPressed(MouseEvent event) {
        Popup stage = (Popup) upperBar.getScene().getWindow();

        x = event.getScreenX() - stage.getX();
        y = event.getScreenY() - stage.getY();
    }

    @FXML private void close_app(){
        Popup stage = (Popup) upperBar.getScene().getWindow();
        stage.hide();
    }
}