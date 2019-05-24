package HKR.HKIF.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

import HKR.HKIF.R;
import HKR.HKIF.data.ScheduleItem;
import HKR.HKIF.localDatabase.AttendanceHistory;
import HKR.HKIF.localDatabase.HkifLocalDatabase;
import HKR.HKIF.utilities.CalendarNewEvent;


public class ScheduleAdapter extends ArrayAdapter<ScheduleItem> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private Context context;
    private boolean logInStatus;

    public ScheduleAdapter(Context context, List<ScheduleItem> objects) {
        super(context, 0, objects);
        this.context = context;
        logInStatus = FirebaseAuth.getInstance().getUid() != null;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        final ScheduleItem item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        final ViewHolder viewHolder;

        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());

            if (item.getCanceled().equals("false")) {
                cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            } else {
                cell = (FoldingCell) vi.inflate(R.layout.cell_unfoldable, parent, false);
            }
            // binding view parts to view holder
            viewHolder.going_numb = cell.findViewById(R.id.going);
            viewHolder.from = cell.findViewById(R.id.from);
            viewHolder.to = cell.findViewById(R.id.to);
            viewHolder.temperature = cell.findViewById(R.id.temperature);
            viewHolder.sport_name = cell.findViewById(R.id.sport_name);
            viewHolder.sport_name_1 = cell.findViewById(R.id.sport_name_1);
            viewHolder.time = cell.findViewById(R.id.time);
            viewHolder.day = cell.findViewById(R.id.date);
            viewHolder.head_image = cell.findViewById(R.id.head_image);
            viewHolder.temperature_1 = cell.findViewById(R.id.temperature_1);
            viewHolder.going_1 = cell.findViewById(R.id.going_1);
            viewHolder.leader_name = cell.findViewById(R.id.leader_name);
            viewHolder.to_1 = cell.findViewById(R.id.to_1);
            viewHolder.from_1 = cell.findViewById(R.id.from_1);
            viewHolder.location_date = cell.findViewById(R.id.location_date);
            viewHolder.location = cell.findViewById(R.id.location);
            viewHolder.contentCalenderBtn = cell.findViewById(R.id.content_calender_btn);
            viewHolder.going_button = cell.findViewById(R.id.content_going_btn);
            viewHolder.going_button.setEnabled(false);
            viewHolder.contentCalenderBtn.setEnabled(false);

            if (logInStatus && item.getCanceled().equals("false")) {
                isGoing(item, viewHolder);
                viewHolder.going_button.setEnabled(true);
                viewHolder.contentCalenderBtn.setEnabled(true);
            }
            viewHolder.contentCalenderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                            Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {

                        new CalendarNewEvent(context, item.getSport_name(), item.getLocation(), item.getFrom(), item.getTo(), item.getLocation_date());

                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_CALENDAR}, 1);
                    }
                }
            });

            viewHolder.going_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (viewHolder.going_button.getText().equals("Going")) {
                        setGoing(item, viewHolder, "false");

                    } else {
                        setGoing(item, viewHolder, "true");
                    }
                }
            });
            cell.setTag(viewHolder);
        } else {
            if (item.getCanceled().equals("true")) {
                // for existing cell set valid valid state(without animation)
                if (unfoldedIndexes.contains(position)) {
                    cell.unfold(true);
                } else {
                    cell.fold(true);
                }
            }
            viewHolder = (ViewHolder) cell.getTag();
        }
        if (null == item) {
            return cell;
        }

        // bind data from selected element to view through view holder
        viewHolder.going_numb.setText(item.getGoing());
        viewHolder.time.setText(item.getLocation_date());
        viewHolder.day.setText(item.getDay());
        viewHolder.temperature.setText(item.getTemperature());
        viewHolder.from.setText(item.getFrom());
        viewHolder.to.setText(String.valueOf(item.getTo()));
        viewHolder.sport_name.setText(item.getSport_name());
        viewHolder.going_1.setText(item.getGoing());
        viewHolder.temperature_1.setText(item.getTemperature());
        viewHolder.from_1.setText(item.getFrom());
        viewHolder.to_1.setText(String.valueOf(item.getTo()));
        viewHolder.sport_name_1.setText(item.getSport_name());
        viewHolder.location.setText(item.getLocation());
        viewHolder.location_date.setText(item.getLocation_date());
        viewHolder.leader_name.setText(item.getLeader_name());

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    private void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    private void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    private void isGoing(ScheduleItem item, final ViewHolder v) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("attendance")
                .child(FirebaseAuth.getInstance().getUid())//user id
                .child(item.getId());  //event id

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue().equals("true")) {
                    v.going_button.setText("Going");
                    v.going_button.setBackgroundColor(Color.GREEN);
                    v.going_button.setTextColor(Color.WHITE);
                } else {
                    v.going_button.setText("Not going");
                    v.going_button.setBackgroundColor(Color.RED);
                    v.going_button.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setGoing(ScheduleItem item, ViewHolder v, final String value) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("attendance")
                .child(FirebaseAuth.getInstance().getUid())//user id
                .child(item.getId());  //event id

        data.setValue(value).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        if (value.equals("true")) {
            v.going_button.setText("Going");
            v.going_button.setBackgroundColor(Color.GREEN);
            v.going_button.setTextColor(Color.WHITE);
            fillAttendance(item);
        } else {
            v.going_button.setText("Not going");
            v.going_button.setBackgroundColor(Color.RED);
            v.going_button.setTextColor(Color.WHITE);
            deleteAttendance(item);
        }
    }

    private void fillAttendance(ScheduleItem item) {

        AttendanceHistory attendanceHistory = new AttendanceHistory();
        attendanceHistory.setUser_id(FirebaseAuth.getInstance().getUid());
        attendanceHistory.setSport_name(item.getSport_name());
        attendanceHistory.setSession_date(item.getLocation_date());
        attendanceHistory.setLocation(item.getLocation());
        HkifLocalDatabase hkifLocalDatabase = new HkifLocalDatabase(getContext());
        hkifLocalDatabase.addAttendance(attendanceHistory);
    }

    private void deleteAttendance(ScheduleItem item) {
        HkifLocalDatabase hkifLocalDatabase = new HkifLocalDatabase(getContext());
        hkifLocalDatabase.deleteUserAttendanceHistory(item.getSport_name());
    }

    // View lookup cache
    private static class ViewHolder { //TODO REMOVE STATICS
        TextView going_numb;
        TextView day;
        TextView time;
        TextView from;
        TextView to;
        TextView temperature;
        TextView sport_name;
        TextView sport_name_1;
        TextView contentCalenderBtn;
        TextView location;
        TextView location_date;
        TextView from_1;
        TextView to_1;
        TextView leader_name;
        TextView going_1;
        TextView temperature_1;
        TextView going_button;
        ImageView head_image;
    }
}