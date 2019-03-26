package HKR.HKIF.dB;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import HKR.HKIF.Users.Person;
import HKR.HKIF.data.ScheduleItem;
import androidx.annotation.NonNull;

public class AddNewEventDB {

    private String location, sportName, year, month, day, hourF, minuteF, hourT, minuteT;


    public AddNewEventDB(String sportName, String year, String month, String day, String hourF, String minuteF, String hourT, String minuteT, String location) {

        this.sportName = sportName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hourF = hourF;
        this.minuteF = minuteF;
        this.hourT = hourT;
        this.minuteT = minuteT;
        this.location = location;

        addToSchedule();
    }

    public void addToSchedule() {

        DatabaseReference data = FirebaseDatabase.getInstance().getReference("schedule");

        if (day.length() == 1)
            day = "0" + day;

        String date = day + "/" + month + "/" + year;

        String key = data.push().getKey();
        data.child(key).setValue(new ScheduleItem("0", (hourF + ":" + minuteF), (hourT + ":" + minuteT), key, sportName, "Event", location, date, " ", "false"));


        getMembersKeys(key);
    }

    private void getMembersKeys(final String key) {

        final ArrayList<String> list = new ArrayList<>();

        DatabaseReference query = FirebaseDatabase.getInstance().getReference("person");

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        list.add(i.getValue(Person.class).getPersonID());
                    }
                    updateAttendance(list, key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void updateAttendance(ArrayList<String> list, String newValue) {

        DatabaseReference data = FirebaseDatabase.getInstance().getReference("attendance");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(0));
            data.child(list.get(i)).child(newValue).setValue("false");
        }
    }
}
