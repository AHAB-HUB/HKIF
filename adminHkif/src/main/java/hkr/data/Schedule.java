package hkr.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Schedule {

    private StringProperty sportName, scheduleDay, scheduleDate, sessionStart, sessionEnd;

    public Schedule(String sportName, String scheduleDay, String scheduleDate, String sessionStart, String sessionEnd){
        this.sportName = new SimpleStringProperty(sportName);
        this.scheduleDay = new SimpleStringProperty(scheduleDay);
        this.scheduleDate = new SimpleStringProperty(scheduleDate);
        this.sessionStart = new SimpleStringProperty(sessionStart);
        this.sessionEnd = new SimpleStringProperty(sessionEnd);
    }

    public Schedule(){

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

    public String getScheduleDay() {
        return scheduleDay.get();
    }

    public StringProperty scheduleDayProperty() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay.set(scheduleDay);
    }

    public String getScheduleDate() {
        return scheduleDate.get();
    }

    public StringProperty scheduleDateProperty() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate.set(scheduleDate);
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
