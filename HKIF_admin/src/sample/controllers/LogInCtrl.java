package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.Connection;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInCtrl implements Initializable {

    @FXML
    private TextField txtUserName, txtPassword;
    @FXML
    private AnchorPane upperBar;

    private double x,y;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void logIn(){
        Connection database = new Connection();
        database.connect();
        if (database.login(txtUserName.getText(), txtPassword.getText())) {
            System.out.println("logged in");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Log in");
            alert.setHeaderText("Been successfully logged in.");
            alert.setContentText("..");
            alert.showAndWait();

            try {
                Stage stage;
                Parent root;
                stage = (Stage) upperBar.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../fxml/homePage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();

            }


        }else{
            System.out.println("Failed to  log in");
        }

        database.disconnect();
    }

    @FXML
    private void onUpperBarDragged(MouseEvent event) {
        Stage stage = (Stage)upperBar.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void onUpperBarPressed(MouseEvent event) {
        Stage stage = (Stage)upperBar.getScene().getWindow();

        x=event.getScreenX() - stage.getX();
        y=event.getScreenY() - stage.getY();
    }

    @FXML
    private void close_app(){
        System.exit(0);
    }

    @FXML
    private void minimize(){
        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.setIconified(true);
    }
}