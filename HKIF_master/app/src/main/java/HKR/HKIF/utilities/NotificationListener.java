package HKR.HKIF.utilities;


import android.content.Context;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import HKR.HKIF.data.NotificationData;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotificationListener {


    public NotificationListener(final Context context, final View v){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("notification");

        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                NotificationData data = dataSnapshot.getValue(NotificationData.class);

                new Notifications(data.getTitle(), data.getMessage(), context).sendOnChannel1(v);
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

}
