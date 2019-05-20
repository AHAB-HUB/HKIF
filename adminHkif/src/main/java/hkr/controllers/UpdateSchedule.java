package hkr.controllers;

import hkr.data.Schedule;
import hkr.database.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class UpdateSchedule implements Initializable {

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private ComboBox<String> dateComboBox;

    @FXML
    private TableView<Schedule> scheduleTable;

    @FXML
    private TableColumn<Schedule, String> col_sport_name;

    @FXML
    private TableColumn<Schedule, String> col_day;

    @FXML
    private TableColumn<Schedule, String> cola_date;

    @FXML
    private TableColumn<Schedule, String> col_session_start;

    @FXML
    private TableColumn<Schedule, String> col_session_end;

    @FXML
    private Button updateBtn;

    @FXML
    private Button getBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initCols();
        dayComboBox.setItems(fillDayComboBox());
        dateComboBox.setItems(new DatabaseConnector().getDatesOfDay(dayComboBox.getValue()));

        getBtn.setOnAction(event -> getInformation());


    }

    private void initCols(){
        col_sport_name.setCellValueFactory(new PropertyValueFactory<>("sportName"));
        col_day.setCellValueFactory(new PropertyValueFactory<>("day"));
        cola_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_session_start.setCellValueFactory(new PropertyValueFactory<>("sessionStart"));
        col_session_end.setCellValueFactory(new PropertyValueFactory<>("sessionEnd"));
    }

    private void getInformation(){
        scheduleTable.setItems(new DatabaseConnector().getScheduleInformation("Monday", "2019-05-20"));
    }

    private ObservableList<String> fillDayComboBox(){
        ObservableList<String> days = FXCollections.observableArrayList();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Sunday");

        return days;
    }
}
