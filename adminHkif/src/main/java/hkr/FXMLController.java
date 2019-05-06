package hkr;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    @FXML
    private Button showBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private TextField showText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBtn.setOnAction(event -> showText.setText("Hello There"));
    }
}