package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
        primaryStage.setTitle("HKIF");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.getScene().setRoot(root);
        primaryStage.initStyle(StageStyle.UNDECORATED); //To have the application without the regular title bar that we have from windows
        primaryStage.getIcons().add(new Image(getClass().getResource("asset/icon.png").toURI().toString()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
