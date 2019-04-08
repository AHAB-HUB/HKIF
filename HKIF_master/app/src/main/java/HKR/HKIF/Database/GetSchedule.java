package HKR.HKIF.Database;

public class GetSchedule {

    private String canceled, day, from, going, id, leader_name, location, location_date, sport_name, to;

    public GetSchedule() {
    }

    public GetSchedule(String canceled, String day, String from, String going, String id,
                       String leader_name, String location, String location_date,
                       String sport_name, String to) {


        this.canceled = canceled;
        this.day = day;
        this.from = from;
        this.going = going;
        this.id = id;
        this.leader_name = leader_name;
        this.location = location;
        this.location_date = location_date;
        this.sport_name = sport_name;
        this.to = to;

    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getGoing() {
        return going;
    }

    public void setGoing(String going) {
        this.going = going;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_date() {
        return location_date;
    }

    public void setLocation_date(String location_date) {
        this.location_date = location_date;
    }

    public String getSport_name() {
        return sport_name;
    }

    public void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
