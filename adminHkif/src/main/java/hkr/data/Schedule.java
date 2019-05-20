package hkr.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Schedule {

    private StringProperty sportName, day, date, sessionStart, sessionEnd;

    public Schedule(String sportName, String day, String date, String sessionStart, String sessionEnd){
        this.sportName = new SimpleStringProperty(sportName);
        this.day = new SimpleStringProperty(day);
        this.date = new SimpleStringProperty(date);
        this.sessionStart = new SimpleStringProperty(sessionStart);
        this.sessionEnd = new SimpleStringProperty(sessionEnd);
    }

    public String getSportName() {
        return sportName.get();
    }

    public StringProperty sportNameProperty() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName.set(sportName);
    }

    public String getDay() {
        return day.get();
    }

    public StringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getSessionStart() {
        return sessionStart.get();
    }

    public StringProperty sessionStartProperty() {
        return sessionStart;
    }

    public void setSessionStart(String sessionStart) {
        this.sessionStart.set(sessionStart);
    }

    public String getSessionEnd() {
        return sessionEnd.get();
    }

    public StringProperty sessionEndProperty() {
        return sessionEnd;
    }

    public void setSessionEnd(String sessionEnd) {
        this.sessionEnd.set(sessionEnd);
    }
}
