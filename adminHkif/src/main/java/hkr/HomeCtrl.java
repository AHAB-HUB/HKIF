package hkr;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeCtrl implements Initializable {

    @FXML
    Label notificationlbl, onlinelbl, profile;

    @FXML
    Pane profileStage;

    @FXML
    private AnchorPane upperBar;

    private double x,y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        Stage stage = (Stage) upperBar.getScene().getWindow();
        stage.setIconified(true);
    }
}
