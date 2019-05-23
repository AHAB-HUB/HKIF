package hkr.controllers;

import hkr.data.Schedule;
import hkr.database.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.Optional;
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
        getBtn.setOnAction(event -> getInformation());

        updateBtn.setOnAction(event -> updateScheduleTable());


    }

    private void initCols() {
        col_sport_name.setCellValueFactory(new PropertyValueFactory<>("sportName"));
        col_day.setCellValueFactory(new PropertyValueFactory<>("scheduleDay"));
        cola_date.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        col_session_start.setCellValueFactory(new PropertyValueFactory<>("sessionStart"));
        col_session_end.setCellValueFactory(new PropertyValueFactory<>("sessionEnd"));
    }

    private void getInformation() {

        scheduleTable.setItems(null);
        scheduleTable.setItems(new DatabaseConnector().
                getScheduleInformation(dayComboBox.getValue(), dateComboBox.getValue()));

    }

    private ObservableList<String> fillDayComboBox() {
        ObservableList<String> days = FXCollections.observableArrayList();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Sunday");

        return days;
    }

    @FXML
    private void fillDate() {
        dateComboBox.setValue("Date");
        dateComboBox.setItems(new DatabaseConnector().getDatesOfDay(dayComboBox.getValue()));
    }

    private void updateScheduleTable() {
        Optional<String> result;
        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Update Schedule");
            dialog.setContentText("Enter the number of days ");
            result = dialog.showAndWait();

            if (result.isPresent()) {// if ok button is pressed

                if (StringUtils.isNumeric(result.get())) {
                    new DatabaseConnector().callUpdateScheduleProc(Integer.valueOf(result.get()));
                    new DatabaseConnector().callUpdateScheduleHasSportPro(Integer.valueOf(result.get()));
                    break;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong input");
                    alert.setResizable(false);
                    alert.setContentText("Please use only numbers!");
                    alert.showAndWait();
                }
            } else {//if cancel button is pressed
                break;
            }
        } while (true);


    }
}
