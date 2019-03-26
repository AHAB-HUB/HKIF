package HKR.HKIF.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import HKR.HKIF.Database.SportLeaders;
import HKR.HKIF.Database.SportTable;
import HKR.HKIF.R;
import HKR.HKIF.Users.Person;
import HKR.HKIF.dB.UpdatePositon;
import HKR.HKIF.fragments.HomeFragment;
import HKR.HKIF.fragments.MembersListFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

@SuppressLint("ValidFragment")
public class SportPickerDialog extends DialogFragment {

    private int selectedCell; // from db
    private DatabaseReference databaseReference;
    private int pos;

    private String personID;
    private String sportName;
    private SportTable sportTable;
    private String sportID;
    private SportLeaders sportLeaders;
    private String personPosition;
    private String sportIDAfter;

    public SportPickerDialog(int position, String personID, String personPosition) {
        this.personID = personID;
        this.personPosition = personPosition;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final CharSequence[] list = {"Badminton", "Soccer", "Floorball", "Basketball"
                , "Swimming", "Volleyball", "Climbing", "Jiu-Jitsu"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the dialog title
        builder.setTitle("Pick a sport: ")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setSingleChoiceItems(list, selectedCell, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pos = which;
                        sportName = list[pos].toString();

                    }
                })
                // Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                getSports();


                                if (!personPosition.equals("TEAM_LEADER")) {
                                    databaseReference = FirebaseDatabase.getInstance()
                                            .getReference("person").child(personID).child("position");

                                    databaseReference.setValue(Person.POSITION.TEAM_LEADER);
                               }


                                FragmentTransaction fragmentHome = getFragmentManager().beginTransaction();
                                fragmentHome.replace(R.id.fragment_container, new MembersListFragment());
                                fragmentHome.commit();


                            }
                        }
                )
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });

        return builder.create();
    }

    private void getSports(){

        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "Loading....", "Please wait...");
        new Thread() {
            public void run() {
                try{
                    sportTable = new SportTable();

                    databaseReference = FirebaseDatabase.getInstance().getReference("sport");

                    databaseReference.orderByChild("sport_name").equalTo(sportName).
                            addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        sportTable = snapshot.getValue(SportTable.class);

                                       String sportID = sportTable.getSport_id();
                                        System.out.println("SportID is: " + sportID);
                                        System.out.println("------------------------------------------------------");


                                        databaseReference = FirebaseDatabase.getInstance().getReference("sport_leaders");

                                sportLeaders = new SportLeaders(sportID, personID);
                             databaseReference.child(personID).setValue(sportLeaders);

                                    }



                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                }
                catch (Exception e) {
                    Log.e("tag", e.getMessage());
                }
                // dismiss the progress dialog
                progressDialog.dismiss();
            }
        }.start();


    }


    //TODO GET POSITION AND WRITE DB CONFIGURATION
    private void connection() {


    }
}