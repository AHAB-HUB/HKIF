package HKR.HKIF.extraReq;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import HKR.HKIF.R;
import HKR.HKIF.adapters.AttendanceHistoryAdapter;
import HKR.HKIF.localDatabase.AttendanceHistory;
import HKR.HKIF.localDatabase.HkifLocalDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AttendanceHistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<AttendanceHistory> attendanceHistories;
    private AttendanceHistoryAdapter attendanceHistoryAdapter;


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
