package hkr.controllers;

import hkr.database.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSport implements Initializable {

    @FXML
    private TextField addSportText;

    @FXML
    private TextField addDescriptionText;

    @FXML
    private ComboBox<String> sportAvailableBox;

    @FXML
    private ComboBox<String> sportLocationBox;

    @FXML
    private Button addBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sportAvailableBox.setItems(addAvailableList());
        sportLocationBox.setItems(addLocationList());
        addBtn.setOnAction(e -> insertNewSport());
    }

    private void insertNewSport(){
        new DatabaseConnector().insertNewValuesIntoSport(addSportText.getText(), addDescriptionText.getText(),
                sportAvailableBox.getValue(), sportLocationBox.getValue());
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
}
