package hkr.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Schedule {

    private StringProperty sportName, scheduleDay, scheduleDate, sessionStart, sessionEnd;

    public Schedule(String sportName, String scheduleDay, String scheduleDate, String sessionStart, String sessionEnd) {
        this.sportName = new SimpleStringProperty(sportName);
        this.scheduleDay = new SimpleStringProperty(scheduleDay);
        this.scheduleDate = new SimpleStringProperty(scheduleDate);
        this.sessionStart = new SimpleStringProperty(sessionStart);
        this.sessionEnd = new SimpleStringProperty(sessionEnd);
    }

    public Schedule() {

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

    public String getScheduleDay() {
        return scheduleDay.get();
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay.set(scheduleDay);
    }

    public StringProperty scheduleDayProperty() {
        return scheduleDay;
    }

    public String getScheduleDate() {
        return scheduleDate.get();
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate.set(scheduleDate);
    }

    public StringProperty scheduleDateProperty() {
        return scheduleDate;
    }

    public String getSessionStart() {
        return sessionStart.get();
    }

    public void setSessionStart(String sessionStart) {
        this.sessionStart.set(sessionStart);
    }

    public StringProperty sessionStartProperty() {
        return sessionStart;
    }

    public String getSessionEnd() {
        return sessionEnd.get();
    }

    public void setSessionEnd(String sessionEnd) {
        this.sessionEnd.set(sessionEnd);
    }

    public StringProperty sessionEndProperty() {
        return sessionEnd;
    }
}
