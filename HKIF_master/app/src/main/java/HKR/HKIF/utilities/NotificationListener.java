package HKR.HKIF.utilities;


import android.content.Context;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import HKR.HKIF.dataBase.MySqLite;
import HKR.HKIF.data.NotificationData;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotificationListener {

    private MySqLite db;

    public NotificationListener(final Context context, final View v) {

        sqLite(context);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("notification");

        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                if (dataSnapshot.exists()) {
                    NotificationData data = dataSnapshot.getValue(NotificationData.class);
                    assert data != null;
                    new Notifications(data.getTitle(), data.getMessage(), context).sendOnChannel1(v);

                }

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

    private void sqLite(Context context) {

        db = new MySqLite(context);

        final ArrayList<NotificationData> messagesList = new ArrayList<>();


        final DatabaseReference data = FirebaseDatabase.getInstance().getReference("notification");

        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (dataSnapshot.exists()) {

                    NotificationData notificationData = dataSnapshot.getValue(NotificationData.class);
                    messagesList.add(notificationData);

                }


                db.setMessages(messagesList);

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
}
