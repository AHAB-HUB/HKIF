package HKR.HKIF.dB;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class GoingUpdater {



    public GoingUpdater(){
        Query query = FirebaseDatabase.getInstance().getReference("attendance");
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("schedule");




    }




}
