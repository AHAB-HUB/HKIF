package hkr.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hkr.database.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSport implements Initializable {

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXTextField addSportText;

    @FXML
    private JFXTextField addDescriptionText;

    @FXML
    private JFXComboBox<String> sportAvailableBox;

    @FXML
    private JFXComboBox<String> sportLocationBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sportAvailableBox.setItems(addAvailableList());
        sportLocationBox.setItems(addLocationList());
        addBtn.setOnAction(e -> insertNewSport());
    }

    private void insertNewSport(){
        new DatabaseConnector().insertNewValuesIntoSport(addSportText.getText(), addDescriptionText.getText(),
                sportAvailableBox.);
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
