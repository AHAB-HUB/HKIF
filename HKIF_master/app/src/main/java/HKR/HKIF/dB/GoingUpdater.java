package HKR.HKIF.dB;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GoingUpdater {



    public GoingUpdater(){


        DatabaseReference attendance = FirebaseDatabase.getInstance().getReference("attendance");
        attendance.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


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
            ArrayList<String> sportList = new ArrayList<>();

            sportList.add("-Laf_Mn5cvX7S10hDy9l");
            sportList.add("-Laf_Mn_kNFAyVk36ALJ");
            sportList.add("-Laf_MnaysCdJ-wv89br");
            sportList.add("-Laf_MncjPoY8AcQLLMs");
            sportList.add("-Laf_MnfhWu-sGigQPqv");
            sportList.add("-Laf_MngaaNXoKtfJYH3");
            sportList.add("-Laf_MnlaPxHSSY9ZpUg");
            sportList.add("-Laf_Mnn-sgiGSt1fM_Q");
            sportList.add("-Laf_MnpOoJweDMbV84A");
            sportList.add("-Laf_MnqcYUvWXD35dhe");
            sportList.add("-Laf_MnrNmpeakWo-_Vk");
            sportList.add("-Laf_Mnszyx-H8JZoaBF");
            sportList.add("-Laf_Mntq996Sn6VLQzS");
            sportList.add("-Laf_Mnus8wcUWSvtFyL");
            sportList.add("-Laf_MnvV6eyjIql9PCS");
            sportList.add("-Laf_MnwmUgG7vhgfszL");
            sportList.add("-Laf_MnxJwzgPYafs71T");
            sportList.add("-Laf_MnyhR0_29belPsf");

            updateEvent(sportList.get(0));

    }

    private void updateEvent(String key){

        Query query = FirebaseDatabase.getInstance().getReference("attendance").child(key);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int c = 0;
                for (DataSnapshot i : dataSnapshot.getChildren()){
                    System.out.println(i.getValue());
                    c++;

                }

                System.out.println(c);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
