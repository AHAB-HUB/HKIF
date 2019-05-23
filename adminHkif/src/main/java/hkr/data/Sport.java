package hkr.data;

import hkr.database.DatabaseConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class Sport {
    private StringProperty sportName, sportDescription, sportAvailable, locationName;
    private Button updateBtn;

    public Sport(String sportName, String sportDescription, String sportAvailable, String locationName, Button updateBtn) {
        this.sportName = new SimpleStringProperty(sportName);
        this.sportDescription = new SimpleStringProperty(sportDescription);
        this.sportAvailable = new SimpleStringProperty(sportAvailable);
        this.locationName = new SimpleStringProperty(locationName);
        this.updateBtn = updateBtn;

        this.updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new DatabaseConnector().updateSport(getSportDescription(), getSportAvailable(), getLocationName(), getSportName());
            }
        });
    }

    public Button getUpdateBtn() {
        return updateBtn;
    }

    public void setUpdateBtn(Button updateBtn) {
        this.updateBtn = updateBtn;
    }

    public String getSportName() {
        return sportName.get();
    }

    public void setSportName(String sportName) {
        this.sportName.set(sportName);
    }

    public StringProperty sportNameProperty() {
        return sportName;
    }

    public String getSportDescription() {
        return sportDescription.get();
    }

    public void setSportDescription(String sportDescription) {
        this.sportDescription.set(sportDescription);
    }

    public StringProperty sportDescriptionProperty() {
        return sportDescription;
    }

    public String getSportAvailable() {
        return sportAvailable.get();
    }

    public void setSportAvailable(String sportAvailable) {
        this.sportAvailable.set(sportAvailable);
    }

    public StringProperty sportAvailableProperty() {
        return sportAvailable;
    }

    public String getLocationName() {
        return locationName.get();
    }

    public void setLocationName(String locationName) {
        this.locationName.set(locationName);
    }

    public StringProperty locationNameProperty() {
        return locationName;
    }
}
