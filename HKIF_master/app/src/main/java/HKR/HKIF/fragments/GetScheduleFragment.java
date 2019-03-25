package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import HKR.HKIF.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GetScheduleFragment extends Fragment {


    private TextView listView;
    private DatabaseReference databaseReference;
    private Button button, updateData;
    private String isUpdated;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.get_schedule_fragment, container, false);

        listView = view.findViewById(R.id.schedule_listView);
        button = view.findViewById(R.id.get_schedule);
        updateData = view.findViewById(R.id.update_data_schedule);

        databaseReference = FirebaseDatabase.getInstance().getReference("schedule").child("2019-03-18");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();

            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updated();
            }
        });

        return view;

    }



    private void updated(){
       databaseReference = FirebaseDatabase.getInstance()
               .getReference("schedule").child("2019-03-18")
               .child("Campus").child("Badminton").child("session_updated");


       boolean session = true;

        session = !isUpdated.equals("true");

      databaseReference.setValue(session);
    }



    private void getData(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String session_updated = dataSnapshot.child("Campus")
                        .child("Badminton").child("session_updated").getValue().toString();
                isUpdated = session_updated;

                listView.setText(session_updated);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
