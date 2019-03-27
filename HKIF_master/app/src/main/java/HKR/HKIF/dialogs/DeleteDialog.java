package HKR.HKIF.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import HKR.HKIF.R;
import HKR.HKIF.fragments.MembersListFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

@SuppressLint("ValidFragment")
public class DeleteDialog extends DialogFragment {

    private String memberName;
    private String personId;
    private String personPosition;


    public DeleteDialog(String name, String personId, String personPosition) {
        this.memberName = name;
        this.personId = personId;
        this.personPosition = personPosition;

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //set up the connection with database
        dbConnection();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage("Are you sure you want to delete \"" + memberName + "\"" + ".")
                .setPositiveButton("Yes.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (personPosition.equals("ADMIN")){
                            Toast.makeText(getContext(), "This is an Admin it can't be deleted", Toast.LENGTH_LONG).show();
                        }
                        else {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                    .getReference("person");

                            databaseReference.child(personId).removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });

                            FragmentTransaction fragmentHome = getFragmentManager().beginTransaction();
                            fragmentHome.replace(R.id.fragment_container, new MembersListFragment());
                            fragmentHome.commit();
                        }


                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }


    //TODO CONNECT TO DATABASE
    private void dbConnection() {


    }
}