package hkr.tables_controller;

import hkr.DatabaseConnector;
import hkr.tables.Person;
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

public class PersonTable implements Initializable {

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> col_first_name;

    @FXML
    private TableColumn<Person, String> col_last_name;

    @FXML
    private TableColumn<Person, String> col_email;

    @FXML
    private TableColumn<Person, String> col_phone_number;

    @FXML
    private TableColumn<Person, String> col_position;

    @FXML
    private TableColumn<Person, String> col_has_paid;

    @FXML
    private Button loadBtn;

    @FXML
    private Button backBtn;

    private ObservableList<Person> personData;
    private DatabaseConnector databaseConnector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        databaseConnector = new DatabaseConnector();

        loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getPersonInformation();
            }
        });

        backBtn.setOnAction(event -> {
            try {
                goBack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void getPersonInformation(){
        personData = FXCollections.observableArrayList();
        String query = "SELECT first_name, last_name, email, phone_number, position, has_paid FROM person " +
                "WHERE person.first_name != 'admin' ";
        try {
            ResultSet resultSet = databaseConnector.getConnection().createStatement().executeQuery(query);

            while (resultSet.next()){
                personData.add(new Person(resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"),
                        resultSet.getString("phone_number"), resultSet.getString("position"),
                        resultSet.getString("has_paid")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone_number.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_has_paid.setCellValueFactory(new PropertyValueFactory<>("hasPaid"));

        personTable.setItems(null);
        personTable.setItems(personData);
    }

    private void goBack() throws Exception{
        Stage stage;
        Parent root;
        stage = (Stage) backBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
