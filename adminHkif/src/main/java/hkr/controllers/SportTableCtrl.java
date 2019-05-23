package hkr.controllers;

import hkr.data.Sport;
import hkr.database.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SportTableCtrl implements Initializable {

    @FXML
    private TableView<Sport> sportTable;
    @FXML
    private TableColumn<Sport, String> col_sport_name, col_sport_description, col_sport_available, col_location_name;
    @FXML
    private TableColumn<Sport, Button> col_edit;
    private ObservableList<Sport> sportData;
    private DatabaseConnector databaseConnector;
    @FXML
    private BorderPane borderPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseConnector = new DatabaseConnector();

        initTable();

        getSportInformation();
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        col_sport_name.setCellValueFactory(new PropertyValueFactory<>("sportName"));
        col_sport_description.setCellValueFactory(new PropertyValueFactory<>("sportDescription"));
        col_sport_available.setCellValueFactory(new PropertyValueFactory<>("sportAvailable"));
        col_location_name.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        col_edit.setCellValueFactory(new PropertyValueFactory<>("updateBtn"));

        editableCols();
    }

    private void editableCols() {
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

    private void getSportInformation() {
        sportData = FXCollections.observableArrayList();
        String query = "Select sport.sport_name, sport.sport_description, sport.sport_available,\n" +
                "location.location_name\n" +
                "From sport, location\n" +
                "WHERE sport.location_location_id = location.location_id";
        try {
            ResultSet resultSet = databaseConnector.getConnection().createStatement().executeQuery(query);

            while (resultSet.next()) {
                sportData.add(new Sport(resultSet.getString("sport_name"),
                        resultSet.getString("sport_description"),
                        resultSet.getString("sport_available"),
                        resultSet.getString("location_name"), new Button("Update")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sportTable.setItems(null);
        sportTable.setItems(sportData);
    }

    private ObservableList<String> availableItems() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("AVAILABLE");
        list.add("NOT AVAILABLE");

        return list;
    }

    private ObservableList<String> locationItems() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Campus");
        list.add("City-Center");

        return list;
    }

    @FXML
    private void openAddSport() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../add_sport.fxml"));
            Stage stage = (Stage) sportTable.getScene().getWindow();
            Popup popup = new Popup();
            popup.getContent().add(root);
            // Parent rot = FXMLLoader.load(getClass().getResource("../sport_table.fxml"));

            popup.show(stage);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private boolean deleteRow() throws IOException {

        try {
            ObservableList<Sport> selectedRow;

            selectedRow = sportTable.getSelectionModel().getSelectedItems();
            System.out.println(selectedRow.get(0));

            new DatabaseConnector().deleteSportRow(selectedRow.get(0).getSportName());

        } catch (IndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to delete");
            alert.setHeaderText("Please select an item to be deleted.");
            alert.showAndWait();
            return false;
        }

        Parent root = FXMLLoader.load(getClass().getResource("../sport_table.fxml"));
        borderPane.setCenter(root);
        return false;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}
