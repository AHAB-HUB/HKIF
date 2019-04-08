package HKR.HKIF.dB;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import HKR.HKIF.data.ScheduleItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UpdateSqlite {

    public UpdateSqlite(String id){

        final Query data = FirebaseDatabase.getInstance().getReference("attendance").child(id);
        final ArrayList<String> keyList = new ArrayList<>();

        data.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (dataSnapshot.getValue().equals("true")){
                        keyList.add(dataSnapshot1.getKey());
                    }
                }

                getSchedule(keyList);

            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getSchedule(final ArrayList<String> keyList) {

        DatabaseReference data = FirebaseDatabase.getInstance().getReference("Schedule");
        final ArrayList<ScheduleItem> list = new ArrayList<>();


        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot i : dataSnapshot.getChildren()) {

                        if (keyList.contains(i.getKey())) {
                            list.add(i.getValue(ScheduleItem.class));
                        }
                    }


                    writeToSqLite(list);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //TODO write data in local database
    private void writeToSqLite(ArrayList<ScheduleItem> list) {
    }
}
