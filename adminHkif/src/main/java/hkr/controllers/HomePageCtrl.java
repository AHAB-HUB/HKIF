package hkr.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageCtrl implements Initializable {

    @FXML
    private BorderPane borderPane;

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
