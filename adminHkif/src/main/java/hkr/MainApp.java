package hkr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage.setTitle("HKIF");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.getScene().setRoot(root);
        stage.initStyle(StageStyle.UNDECORATED); //To have the application without the regular title bar that we have from windows
        //stage.getIcons().add(new Image("icon.png")); //TODO fix this issue where it conflict with gradle 5.
        stage.show();
    }

}

