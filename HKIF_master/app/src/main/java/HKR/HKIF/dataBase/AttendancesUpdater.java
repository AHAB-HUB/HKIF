package HKR.HKIF.dataBase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import HKR.HKIF.data.ScheduleItem;

public class AttendancesUpdater {

    public AttendancesUpdater(final String id) {

        final ArrayList<String> sportList = new ArrayList<>();
        DatabaseReference query = FirebaseDatabase.getInstance().getReference("schedule");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    sportList.add(d.getValue(ScheduleItem.class).getId());
                }

                DatabaseReference data = FirebaseDatabase.getInstance().getReference("attendance");

                for (int i = 0; i < sportList.size(); i++) {
                    System.out.println(id);
                    System.out.println(sportList.get(i) + sportList.size());

                    data.child(id).child(sportList.get(i)).setValue("false");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void attendanceListener(ArrayList<String> sportList, String id) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("attendance");

        for (int i = 0; i < sportList.size(); i++) {
            System.out.println(id);
            System.out.println(sportList.get(i));

            data.child(id).child(sportList.get(i)).setValue("false");
        }
    }
}
