package HKR.HKIF.dB;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseConnetion {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public FireBaseConnetion(){

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("messages");


    }
}
