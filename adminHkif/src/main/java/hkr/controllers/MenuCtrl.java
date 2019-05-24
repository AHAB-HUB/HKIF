package hkr.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuCtrl implements Initializable {

    @FXML
    private Label notificationLbl, onlinelbl, profile;
    @FXML
    private Pane upperBar;
    @FXML
    private BorderPane borderPane;
    private double x, y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCenter("home_page");
    }

    @FXML
    private void home() {
        setCenter("home_page");
    }

    @FXML
    private void schedule() {
        setCenter("update_schedule");
    }

    @FXML
    private void addEvent() {
        setCenter("add_event");
    }

    @FXML
    private void sports() {
        setCenter("sport_table");
    }

    @FXML
    private void members() {
        setCenter("person_table");
    }

    @FXML
    private void logOut() throws IOException {
        Stage stage = (Stage) upperBar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onUpperBarDragged(MouseEvent event) {
        Stage stage = (Stage) upperBar.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void onUpperBarPressed(MouseEvent event) {
        Stage stage = (Stage) upperBar.getScene().getWindow();

        x = event.getScreenX() - stage.getX();
        y = event.getScreenY() - stage.getY();
    }

    @FXML
    private void close_app() {
        System.exit(0);
    }

    @FXML
    private void minimize() {
        Stage stage = (Stage) upperBar.getScene().getWindow();
        stage.setIconified(true);
    }

    private void setCenter(String UI) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../" + UI + ".fxml"));
            Parent root = loader.load();
            borderPane.setCenter(root);
            borderPane.setRight(null);

            if (UI.equals("sport_table"))
                ((SportTableCtrl) loader.getController()).setBorderPane(borderPane);
            if (UI.equals("person_table"))
                ((MemberTableCtrl) loader.getController()).setBorderPane(borderPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
