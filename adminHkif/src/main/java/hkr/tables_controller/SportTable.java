package hkr.tables_controller;

import hkr.DatabaseConnector;
import hkr.tables.Sport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SportTable implements Initializable {


    @FXML
    private TableView<Sport> sportTable;

    @FXML
    private TableColumn<Sport, String> col_sport_name;

    @FXML
    private TableColumn<Sport, String> col_sport_description;

    @FXML
    private TableColumn<Sport, String> col_sport_available;

    @FXML
    private TableColumn<Sport, String> col_location_name;

    @FXML
    private Button loadBtn;

    @FXML
    private Button backBtn;

    private ObservableList<Sport> sportData;
    private DatabaseConnector databaseConnector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseConnector = new DatabaseConnector();

        loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSportInformation();
            }
        });

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    goBack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getSportInformation(){
        sportData = FXCollections.observableArrayList();
        String query = "Select sport.sport_name, sport.sport_description, sport.sport_available,\n" +
                "location.location_name\n" +
                "From sport, location\n" +
                "WHERE sport.location_location_id = location.location_id";
        try {
            ResultSet resultSet = databaseConnector.getConnection().createStatement().executeQuery(query);

            while (resultSet.next()){
                sportData.add(new Sport(resultSet.getString("sport_name"),
                        resultSet.getString("sport_description"),
                        resultSet.getString("sport_available"),
                        resultSet.getString("location_name")));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        col_sport_name.setCellValueFactory(new PropertyValueFactory<>("sportName"));
        col_sport_description.setCellValueFactory(new PropertyValueFactory<>("sportDescription"));
        col_sport_available.setCellValueFactory(new PropertyValueFactory<>("sportAvailable"));
        col_location_name.setCellValueFactory(new PropertyValueFactory<>("locationName"));

        sportTable.setItems(null);
        sportTable.setItems(sportData);
    }

    private void goBack() throws Exception{
        Stage stage;
        Parent root;
        stage = (Stage) backBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
