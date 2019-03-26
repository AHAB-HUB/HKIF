package HKR.HKIF.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import HKR.HKIF.R;
import HKR.HKIF.Users.Person;
import HKR.HKIF.fragments.MembersListFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

@SuppressLint("ValidFragment")
public class SetPositionDialog extends DialogFragment {

    private int selectedCell; // from db
    int position;
    private int numberTwo;
    private String personID;
    private String personPosition;
    private DatabaseReference databaseReference;

    public SetPositionDialog(int position, String personID, String personPosition) {
        this.position = position;
        this.personID = personID;
        this.personPosition = personPosition;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CharSequence[] list = {"Member", "Team leader"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the dialog title
        builder.setTitle("Pick position: ")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setSingleChoiceItems(list, selectedCell, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        numberTwo = which;

                    }
                })

                // Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if (numberTwo == 0) {

                            if (personPosition.equals("MEMBER")) {
                                Toast.makeText(getContext(), "This is already a member", Toast.LENGTH_LONG).show();
                            } else if (personPosition.equals("TEAM_LEADER")) {
                                databaseReference = FirebaseDatabase.getInstance().getReference("person")
                                        .child(personID).child("position");
                                databaseReference.setValue(Person.POSITION.MEMBER);

                                databaseReference = FirebaseDatabase.getInstance().getReference("sport_leaders");
                                databaseReference.child(personID).removeValue();

                                FragmentTransaction fragmentHome = getFragmentManager().beginTransaction();
                                fragmentHome.replace(R.id.fragment_container, new MembersListFragment());
                                fragmentHome.commit();
                            } else {
                                Toast.makeText(getContext(), "This is an Admin can't be a member", Toast.LENGTH_LONG).show();
                            }

                        } else {

                            if (personPosition.equals("ADMIN")) {
                                Toast.makeText(getContext(), "This is an Admin can't be a team leader", Toast.LENGTH_LONG).show();
                            } else {
                                FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager(); // to show the dialog
                                new SportPickerDialog(position, personID, personPosition).show(manager, "delete");
                                // User clicked OK, so save the selectedItems results somewhere
                                // or return them to the component that opened the dialog
                            }


                        }


                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(getContext(), "Action canceled", Toast.LENGTH_SHORT).show();

                    }
                });

        return builder.create();
    }


    //TODO GET POSITION AND WRITE DB CONFIGURATION
    private void connection() {


    }
}