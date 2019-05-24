package HKR.HKIF.extraReq;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import HKR.HKIF.R;
import HKR.HKIF.adapters.AttendanceHistoryAdapter;
import HKR.HKIF.localDatabase.AttendanceHistory;
import HKR.HKIF.localDatabase.HkifLocalDatabase;

public class AttendanceHistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<AttendanceHistory> attendanceHistories;
    private AttendanceHistoryAdapter attendanceHistoryAdapter;
    private Button clearBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.attendance_history_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initObjects();
        clearBtn = getActivity().findViewById(R.id.attendance_history_clear_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HkifLocalDatabase(getContext()).deleteAllRecordsFromAttendance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AttendanceHistoryFragment()).addToBackStack(null).commit();
            }
        });
    }

    private void initViews() {
        recyclerView = getActivity().findViewById(R.id.attendance_recycler_view);
    }

    private void initObjects() {
        attendanceHistories = new ArrayList<>();
        attendanceHistoryAdapter = new AttendanceHistoryAdapter(attendanceHistories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(attendanceHistoryAdapter);
        getDataFromSQLite();
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite() {


        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                attendanceHistories.clear();
                attendanceHistories.addAll(new HkifLocalDatabase(getContext()).
                        getUserAtendance());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                attendanceHistoryAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
