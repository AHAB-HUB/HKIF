package HKR.HKIF.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

@SuppressLint("ValidFragment")
public class DeleteDialog extends DialogFragment {

    private String memberName;


    public DeleteDialog (String name){
        this.memberName = name;



    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //set up the connection with database
        dbConnection();




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage("Are you sure you want to delete \"" + memberName + "\".")
                .setPositiveButton("Yes.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



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
    private void dbConnection(){


    }
}