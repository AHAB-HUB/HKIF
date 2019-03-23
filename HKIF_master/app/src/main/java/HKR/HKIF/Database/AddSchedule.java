package HKR.HKIF.Database;

public class AddSchedule {

    private String date;
    private String location;
    private String sport;
    private String start;
    private String end;
    private boolean session_updated;


    public AddSchedule() {

    }

    public AddSchedule( String start, String end, boolean session_updated) {

        this.start = start;
        this.end = end;
        this.session_updated = session_updated;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isSession_updated() {
        return session_updated;
    }

    public void setSession_updated(boolean session_updated) {
        this.session_updated = session_updated;
    }
}
