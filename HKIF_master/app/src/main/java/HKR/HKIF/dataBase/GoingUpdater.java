package HKR.HKIF.dataBase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import HKR.HKIF.data.ScheduleItem;

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
//        sportList.add("-Lb-pL3pL8zgFVOlXbRK");
//        sportList.add("-Lb0bf3-rO3fu9FiMhMY");
//        sportList.add("-Lb161Fn6cWzE66YOUO5");
//
//
//        for (int i = 0; i < sportList.size(); i++) {
//            data.child("51As5hOk3kWmtGJkyoawFfQOuTS2").child(sportList.get(i)).setValue("false");
//            data.child("74L9rAoEjJamYdrB95iJdgvNkqx1").child(sportList.get(i)).setValue("false");
//            data.child("8T5xLX24cUaeNoY3qUhykSuYoTp1").child(sportList.get(i)).setValue("false");
//            data.child("BC6SrS42PjPRgKlKU6uLPABgyPl2").child(sportList.get(i)).setValue("false");
//            data.child("QFs1o9LEV9aKYfBTMJmIY3WNivQ2").child(sportList.get(i)).setValue("false");
//            data.child("U16MzZQ35BS5OmKKaT8tnrhionN2").child(sportList.get(i)).setValue("false");
//            data.child("Y6kJOKDO4jag9zibjbLJVrgbPtC3").child(sportList.get(i)).setValue("false");
//
//            data.child("iCF311t6YkO8jnnzjq0q3ZBMB8I3").child(sportList.get(i)).setValue("false");
//            data.child("jDeb6uSFaeQbvoM24kFldtWxh2J3").child(sportList.get(i)).setValue("false");
//            data.child("jFKASMjoAIR6TLbqQmjaHm5MOOt1").child(sportList.get(i)).setValue("false");
//            data.child("kOJ6LU0HvkOoXXRxYDsSLIIUatf1").child(sportList.get(i)).setValue("false");
//            data.child("rQ8JLc3zIzPXYWkNvpWaavLesho1").child(sportList.get(i)).setValue("false");
//            data.child("tCfRCP7c28WO7c48lSkphdSRLUd2").child(sportList.get(i)).setValue("false");
//            data.child("uccodBbN8tZ5FIoy36wACvIGtwS2").child(sportList.get(i)).setValue("false");
//            data.child("vbzv7TkDO1YcJ4iU8ThAY5hzfyJ3").child(sportList.get(i)).setValue("false");
//        }


        final ArrayList<String> sportList = new ArrayList<>();
        DatabaseReference query = FirebaseDatabase.getInstance().getReference("schedule");
        query.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                sportList.add(dataSnapshot.getValue(ScheduleItem.class).getId());
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

        try {
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
        } catch (NullPointerException e) {
        }
    }

    //saving data in schedule table
    private void setGoing(String event, int going) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("schedule").child(event).child("going");
        data.setValue(String.valueOf(going));
    }
}