package HKR.HKIF.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import HKR.HKIF.R;
import HKR.HKIF.localDatabase.AttendanceHistory;


public class AttendanceHistoryAdapter extends RecyclerView.Adapter<AttendanceHistoryAdapter.UserViewHolder> {

    private List<AttendanceHistory> attendanceHistoryList;

    public AttendanceHistoryAdapter(List<AttendanceHistory> attendanceHistoryList) {
        this.attendanceHistoryList = attendanceHistoryList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_history_item, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.attendanceDate.setText(attendanceHistoryList.get(position).getSession_date());
        holder.attendanceLocation.setText(attendanceHistoryList.get(position).getLocation());
        holder.attendanceSport.setText(attendanceHistoryList.get(position).getSport_name());
    }

    @Override
    public int getItemCount() {
        Log.v(AttendanceHistoryAdapter.class.getSimpleName(), "" + attendanceHistoryList.size());
        return attendanceHistoryList.size();
    }

    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView attendanceDate;
        private AppCompatTextView attendanceLocation;
        private AppCompatTextView attendanceSport;

        public UserViewHolder(View view) {
            super(view);

            attendanceDate = view.findViewById(R.id.attendance_history_date_textView);
            attendanceLocation = view.findViewById(R.id.attendance_history_location_textView);
            attendanceSport = view.findViewById(R.id.attendance_history_sport_textView);
        }
    }
}
