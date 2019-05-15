package hkr.controllers;

import hkr.database.DatabaseConnector;
import hkr.data.Sport;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SportTableCtrl implements Initializable {

    @FXML
    private Button addSportBtn;

    @FXML
    private Button deleteSportBtn;

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
    private TableColumn<Sport, Button> col_edit;

    private ObservableList<Sport> sportData;
    private DatabaseConnector databaseConnector;

    @FXML
    private BorderPane borderPane;


    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseConnector = new DatabaseConnector();

        initTable();

        getSportInformation();

        addSportBtn.setOnAction(e -> openAddSport());
        deleteSportBtn.setOnAction(e -> deleteRow());
    }


    private void initTable(){
        initCols();
    }



    private void initCols(){
        col_sport_name.setCellValueFactory(new PropertyValueFactory<>("sportName"));
        col_sport_description.setCellValueFactory(new PropertyValueFactory<>("sportDescription"));
        col_sport_available.setCellValueFactory(new PropertyValueFactory<>("sportAvailable"));
        col_location_name.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        col_edit.setCellValueFactory(new PropertyValueFactory<>("updateBtn"));

        editableCols();
    }

    private void editableCols(){
        col_sport_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_sport_name.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setSportName(event.getNewValue());
        });

        col_sport_description.setCellFactory(TextFieldTableCell.forTableColumn());
        col_sport_description.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setSportDescription(event.getNewValue());
        });

        col_sport_available.setCellFactory(ComboBoxTableCell.forTableColumn(availableItems()));
        col_sport_available.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setSportAvailable(event.getNewValue());
        });



        col_location_name.setCellFactory(ComboBoxTableCell.forTableColumn(locationItems()));
        col_location_name.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setLocationName(event.getNewValue());
        });

        sportTable.setEditable(true);
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
                        resultSet.getString("location_name"), new Button("Update")));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        sportTable.setItems(null);
        sportTable.setItems(sportData);
    }

    private ObservableList<String> availableItems(){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("AVAILABLE");
        list.add("NOT AVAILABLE");

        return list;
    }

    private ObservableList<String> locationItems(){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Campus");
        list.add("City-Center");

        return list;
    }

    private void openAddSport(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../add_sport.fxml"));
            borderPane.setCenter(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteRow(){
        ObservableList<Sport> selectedRow;

        selectedRow = sportTable.getSelectionModel().getSelectedItems();

        new DatabaseConnector().deleteSportRow(selectedRow.get(0).getSportName());

    }

}
