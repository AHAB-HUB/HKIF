package HKR.HKIF.data;

import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import HKR.HKIF.fragments.ScheduleFragment;
import androidx.annotation.NonNull;


@SuppressWarnings({"unused", "WeakerAccess"})
public class ScheduleItem {

    private String going;
    private String from;
    private String to;
    private String temperature;
    private String sport_name;
    private String day;
    private String location;
    private String location_date;
    private String leader_name;
    private String head_image;
    private String id;

    private View.OnClickListener requestBtnClickListener;


    public ScheduleItem() {
    }

    public ScheduleItem( String going, String from, String to, String id, String sport_name, String day, String location, String location_date, String leader_name) {

        this.going = going;
        this.from= from;
        this.to= to;
        this.sport_name= sport_name;
        this.day = day;
        this.location = location;
        this.location_date = location_date;
        this.leader_name = leader_name;
        this.id = id;


    }

    public ScheduleItem(String going, String from, String to, String id,String temperature, String sport_name, String day, String location, String location_date, String leader_name, String head_image) {

        this.going = going;
        this.from= from;
        this.to= to;
        this.temperature= temperature;
        this.sport_name= sport_name;
        this.day = day;
        this.location = location;
        this.location_date = location_date;
        this.leader_name = leader_name;
        this.head_image = head_image;
        this.id = id;

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

        ScheduleItem item = (ScheduleItem) o;


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


        return !(day != null ? !day.equals(item.day) : item.day != null);
    }

    @Override
    public int hashCode() {
        int result = going != null ? going.hashCode() : 0;
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (sport_name != null ? sport_name.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (location_date != null ? location_date.hashCode() : 0);
        result = 31 * result + (leader_name != null ? leader_name.hashCode() : 0);
        result = 31 * result + (head_image != null ? head_image.hashCode() : 0);

        return result;
    }


    public String getDay() {
        return day;
    }

    public String getLocation() {
        return location;
    }

    public String getLocation_date() {
        return location_date;
    }

    public String getLeader_name() {
        return leader_name;
    }

    public String getHead_image() {
        return head_image;
    }

    public String getGoing() {
        return going;
    }


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSport_name() {
        return sport_name;
    }

    public String getId() {
        return id;
    }

}
