
package hkr.controllers;


import hkr.database.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddEvent implements Initializable {


    @FXML
    private TextField eventNameText;

    @FXML
    private TextField eventDescriptionText;

    @FXML
    private TextField eventLocationText;

    @FXML
    private DatePicker eventDate;

    @FXML
    private TextField eventStartText;

    @FXML
    private TextField eventEndText;

    @FXML
    private ComboBox<String> sportComboBox;

    @FXML
    private Button createBtn;

    @FXML
    private Button addMoreSportsBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sportComboBox.setItems(new DatabaseConnector().getAllSportNames());

        createBtn.setOnAction(e -> {
            try {
                creatNewEvent();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        addMoreSportsBtn.setOnAction(e -> addMoreSportsToEvent());
    }

    private void creatNewEvent() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Time startTime = new Time(dateFormat.parse(eventStartText.getText()).getTime());
        Time endTime = new Time(dateFormat.parse(eventEndText.getText()).getTime());

        new DatabaseConnector().insertValueIntoEvent(eventNameText.getText(), eventDescriptionText.getText(),
                eventLocationText.getText(),  Date.valueOf(eventDate.getValue()), startTime, endTime);
        new DatabaseConnector().insertValueIntoSportHasEvent(new DatabaseConnector().getEventId(eventNameText.getText()),
                new DatabaseConnector().getSportId(sportComboBox.getValue()));



    }

    private void addMoreSportsToEvent() {
        ChoiceDialog<String> eventChoice = new ChoiceDialog<>("Event", new DatabaseConnector().getAlleventNames());
        eventChoice.setTitle("ADD EVENT");
        eventChoice.setHeaderText("Choose an event");
        eventChoice.setContentText("Event Name");
        Optional<String> eventResult = eventChoice.showAndWait();

        if (eventResult.isPresent()) {
            ChoiceDialog<String> sportChoice = new ChoiceDialog<>("Sport", new DatabaseConnector().getAllSportNames());
            sportChoice.setTitle("ADD SPORT");
            sportChoice.setHeaderText("Choose a sport");
            sportChoice.setContentText("Sport Name");
            Optional<String> sp = sportChoice.showAndWait();

            if (sp.isPresent()) {
                new DatabaseConnector().insertValueIntoSportHasEvent(new DatabaseConnector().getEventId(eventChoice.getSelectedItem()),
                        new DatabaseConnector().getSportId(sportChoice.getSelectedItem()));
            }
        }
    }

}