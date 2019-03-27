package HKR.HKIF.Database;

public class SportTable {

    private String sport_id;
    private String sport_name;
    private String sport_description;
    private boolean sport_updated;

    public SportTable() {
    }

    public SportTable(String sport_id, String sport_name, String sport_description, boolean sport_updated) {
        this.sport_id = sport_id;
        this.sport_name = sport_name;
        this.sport_description = sport_description;
        this.sport_updated = sport_updated;
    }

    public String getSport_id() {
        return sport_id;
    }

    public void setSport_id(String sport_id) {
        this.sport_id = sport_id;
    }

    public String getSport_name() {
        return sport_name;
    }

    public void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }

    public String getSport_description() {
        return sport_description;
    }

    public void setSport_description(String sport_description) {
        this.sport_description = sport_description;
    }

    public boolean isSport_updated() {
        return sport_updated;
    }

    public void setSport_updated(boolean sport_updated) {
        this.sport_updated = sport_updated;
    }


    @Override
    public String toString() {
        return "SportTable{" +
                "sport_id='" + sport_id + '\'' +
                ", sport_name='" + sport_name + '\'' +
                ", sport_description='" + sport_description + '\'' +
                ", sport_updated=" + sport_updated +
                '}';
    }
}
