package hkr.controllers;

import hkr.database.DatabaseConnector;
import hkr.data.Person;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private TableColumn<Person, Button> col_edit;

    private ObservableList<Person> personData;
    private DatabaseConnector databaseConnector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        databaseConnector = new DatabaseConnector();

        initTable();
        getPersonInformation();
    }

    private void initTable(){
        initCols();
    }

    private void initCols(){

        col_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone_number.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_has_paid.setCellValueFactory(new PropertyValueFactory<>("hasPaid"));
        col_edit.setCellValueFactory(new PropertyValueFactory<>("updateBtn"));

        editableCols();
    }

    private void editableCols(){
        col_first_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_first_name.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(event.getNewValue());
        });

        col_last_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_last_name.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(event.getNewValue());
        });

        col_email.setCellFactory(TextFieldTableCell.forTableColumn());
        col_email.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setEmail(event.getNewValue());
        });

        col_phone_number.setCellFactory(TextFieldTableCell.forTableColumn());
        col_phone_number.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setPhoneNumber(event.getNewValue());
        });

        col_position.setCellFactory(TextFieldTableCell.forTableColumn());
        col_position.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setPosition(event.getNewValue());
        });

        col_has_paid.setCellFactory(TextFieldTableCell.forTableColumn());
        col_has_paid.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHasPaid(event.getNewValue());
        });

        personTable.setEditable(true);
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
                        resultSet.getString("has_paid"), new Button("Update")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        personTable.setItems(null);
        personTable.setItems(personData);
    }



}
