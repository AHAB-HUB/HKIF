package HKR.HKIF.utilities;


import android.content.Context;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotificationListener {

    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;

    public NotificationListener(Context context){

        databaseReference = FirebaseDatabase.getInstance().getReference().child("notification");

        childEventListener = new ChildEventListener() {
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
        };


        databaseReference.addChildEventListener(childEventListener);
    }







    //TODO ADD CONNECTION CONFIGURATION
    private void dbConnection(){



    }
}
