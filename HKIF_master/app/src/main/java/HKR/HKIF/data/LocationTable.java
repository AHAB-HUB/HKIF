package HKR.HKIF.data;

public class LocationTable {

    private String location_id;
    private String location_name;
    private String location_address;

    public LocationTable() {
    }

    public LocationTable(String location_id, String location_name, String location_address) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.location_address = location_address;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }
}
