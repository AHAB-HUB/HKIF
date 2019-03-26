package HKR.HKIF.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

import HKR.HKIF.dB.UpdatePositon;
import androidx.fragment.app.DialogFragment;

@SuppressLint("ValidFragment")
public class SportPickerDialog extends DialogFragment {

    private int selectedCell; // from db
    private DatabaseReference databaseReference;
    private int pos;

    public SportPickerDialog(int position) {

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

                    }
                })
                // Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        new UpdatePositon(list[pos].toString());



                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });

        return builder.create();
    }


    //TODO GET POSITION AND WRITE DB CONFIGURATION
    private void connection() {


    }
}