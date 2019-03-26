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

public class GoingUpdater {


    public GoingUpdater() {

//        DatabaseReference data = FirebaseDatabase.getInstance().getReference("attendance");
//        ArrayList<String> sportList = new ArrayList<>();
//
//        sportList.add("-Laf_Mn5cvX7S10hDy9l");
//        sportList.add("-Laf_Mn_kNFAyVk36ALJ");
//        sportList.add("-Laf_MnaysCdJ-wv89br");
//        sportList.add("-Laf_MncjPoY8AcQLLMs");
//        sportList.add("-Laf_MnfhWu-sGigQPqv");
//        sportList.add("-Laf_MngaaNXoKtfJYH3");
//        sportList.add("-Laf_MnlaPxHSSY9ZpUg");
//        sportList.add("-Laf_Mnn-sgiGSt1fM_Q");
//        sportList.add("-Laf_MnpOoJweDMbV84A");
//        sportList.add("-Laf_MnqcYUvWXD35dhe");
//        sportList.add("-Laf_MnrNmpeakWo-_Vk");
//        sportList.add("-Laf_Mnszyx-H8JZoaBF");
//        sportList.add("-Laf_Mntq996Sn6VLQzS");
//        sportList.add("-Laf_Mnus8wcUWSvtFyL");
//        sportList.add("-Laf_MnvV6eyjIql9PCS");
//        sportList.add("-Laf_MnwmUgG7vhgfszL");
//        sportList.add("-Laf_MnxJwzgPYafs71T");
//        sportList.add("-Laf_MnyhR0_29belPsf");
//
//
//
//        for (int i = 0; i < sportList.size(); i++) {
//            data.child("QFs1o9LEV9aKYfBTMJmIY3WNivQ2").child(sportList.get(i)).setValue("false");
//
//        }



        final ArrayList<String> sportList = new ArrayList<>();

        DatabaseReference query = FirebaseDatabase.getInstance().getReference("schedule");

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    if (dataSnapshot.exists()) {
                        sportList.add(dataSnapshot.getValue(ScheduleItem.class).getId());
                    }
                attendanceListener(sportList);
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


    private void attendanceListener(final ArrayList<String> sportList) {

        DatabaseReference attendance = FirebaseDatabase.getInstance().getReference("attendance");
        attendance.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                for (int i = 0; i < sportList.size(); i++) {
                    updateEvent(sportList.get(i));
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (int i = 0; i < sportList.size(); i++) {
                    updateEvent(sportList.get(i));
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < sportList.size(); i++) {
                    updateEvent(sportList.get(i));
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (int i = 0; i < sportList.size(); i++) {
                    updateEvent(sportList.get(i));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    //fetch the number of going members from attendance table
    private void updateEvent(final String key) {

        Query query = FirebaseDatabase.getInstance().getReference("attendance").orderByChild(key).equalTo("true");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setGoing(key, (int) dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //saving data in schedule table
    private void setGoing(String event, int going) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("schedule").child(event).child("going");

        data.setValue(String.valueOf(going));

    }
}
