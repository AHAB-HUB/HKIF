package HKR.HKIF.dB;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


import HKR.HKIF.data.ScheduleItem;
import androidx.annotation.NonNull;

public class FireBaseConnection {


    public static void getList(String eventHandler) {
        Query query = FirebaseDatabase.getInstance().getReference("schedule").orderByChild("day").equalTo(eventHandler);



        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<ScheduleItem> items = new ArrayList<>();

                if (dataSnapshot.exists()){

                    for (DataSnapshot i : dataSnapshot.getChildren()) {

                        ScheduleItem s = i.getValue(ScheduleItem.class);
                        items.add(s);


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


    }
}