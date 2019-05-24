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
import HKR.HKIF.adapters.InboxAdapter;
import HKR.HKIF.data.NotificationData;
import HKR.HKIF.dataBase.MySqLite;


public class InboxFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<NotificationData> listUsers;
    private InboxAdapter usersRecyclerAdapter;
    private MySqLite db;
    private Button delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inbox_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
        initObjects();

        delete = getActivity().findViewById(R.id.clear_inbox);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.clearMessages();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InboxFragment()).addToBackStack(null).commit();
            }
        });
    }

    private void initViews() {
        recyclerView = getActivity().findViewById(R.id.recycler_view);
    }

    private void initObjects() {
        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new InboxAdapter(listUsers);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(usersRecyclerAdapter);
        db = new MySqLite(getActivity());
        getDataFromSQLite();
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite() {

        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(db.getMessages());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                usersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
