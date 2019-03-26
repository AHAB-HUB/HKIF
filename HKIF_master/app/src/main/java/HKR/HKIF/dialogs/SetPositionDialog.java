package HKR.HKIF.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

@SuppressLint("ValidFragment")
public class SetPositionDialog extends DialogFragment {

    private int selectedCell; // from db
    int position;

    public SetPositionDialog(int position) {
        this.position = position;

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


                    }
                })

                // Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager(); // to show the dialog
                        new SportPickerDialog(position).show(manager, "delete");
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog


                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(getContext(),"Action canceled",Toast.LENGTH_SHORT).show();

                    }
                });

        return builder.create();
    }


    //TODO GET POSITION AND WRITE DB CONFIGURATION
    private void connection() {


    }
}