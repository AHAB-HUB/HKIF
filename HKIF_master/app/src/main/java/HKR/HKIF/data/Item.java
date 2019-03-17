package HKR.HKIF.data;

import android.view.View;

import java.util.ArrayList;

/**
 * Simple POJO model for example
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Item {

    private String going;
    private String from;
    private String to;
    private String temperature;
    private String sport_name;
    private String day;
    private String time;
    private String location;
    private String location_date;
    private String leader_name;
    private String head_image;

    private View.OnClickListener requestBtnClickListener;

    public Item() {
    }

    public Item(String going, String from, String to, String temperature, String sport_name, String day, String time, String location, String location_date, String leader_name, String head_image) {
        this.going = going;
        this.from= from;
        this.to= to;
        this.temperature= temperature;
        this.sport_name= sport_name;
        this.day = day;
        this.time = time;
        this.location = location;
        this.location_date = location_date;
        this.leader_name = leader_name;
        this.head_image = head_image;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;


        if (location != null ? !location.equals(item.location) : item.location != null)
            return false;
        if (location_date != null ? !location_date.equals(item.location_date) : item.location_date != null)
            return false;
        if (leader_name != null ? !leader_name.equals(item.leader_name) : item.leader_name != null)
            return false;
        if (head_image != null ? !head_image.equals(item.head_image) : item.head_image != null)
            return false;

        if (!going.equals(item.going)) return false;
        if (from != null ? !from.equals(item.from) : item.from != null) return false;
        if (to != null ? !to.equals(item.to) : item.to != null)
            return false;
        if (temperature != null ? !temperature.equals(item.temperature) : item.temperature != null)
            return false;
        if (sport_name != null ? !sport_name.equals(item.sport_name) : item.sport_name != null)
            return false;
        if (day != null ? !day.equals(item.day) : item.day != null)
            return false;

        return !(time != null ? !time.equals(item.time) : item.time != null);
    }

    @Override
    public int hashCode() {
        int result = going != null ? going.hashCode() : 0;
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (sport_name != null ? sport_name.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (location_date != null ? location_date.hashCode() : 0);
        result = 31 * result + (leader_name != null ? leader_name.hashCode() : 0);
        result = 31 * result + (head_image != null ? head_image.hashCode() : 0);


        return result;
    }

    /**
     * @return List of elements prepared for tests
     */

    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("16","16:00","17:00","34 C","Badminton","Monday"  ,"16:00", "Kristianstad, Sweden, 291 39", "01/may/2019", "Ahmad"  , ""));
        items.add(new Item("16","16:00","17:00","54 C","Football" ,"Monday"  ,"16:00", "Kristianstad, Sweden, 291 39", "01/may/2019", "Nawar"  , ""));
        items.add(new Item("16","16:00","18:00","33 C","Swimming" ,"Friday"  ,"16:00", "Kristianstad, Sweden, 291 39", "01/may/2019", "Kobrum" , ""));
        items.add(new Item("16","16:00","19:00","32 C","Badminton","Monday"  ,"16:00", "Kristianstad, Sweden, 291 39", "01/may/2019", "Ibra"   , ""));
        items.add(new Item("16","16:00","10:00","31 C","5ra"      ,"Saturday","16:00", "Kristianstad, Sweden, 291 39", "01/may/2019", "Stephan", ""));
        items.add(new Item("16","16:00","12:00","44 C","Hiking"   ,"Sunday"  ,"16:00", "Kristianstad, Sweden, 291 39", "01/may/2019", "5ra"    , ""));
        return items;

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public String getGoing() {
        return going;
    }

    public void setGoing(String going) {
        this.going = going;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSport_name() {
        return sport_name;
    }

    public void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
