package HKR.HKIF.adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import HKR.HKIF.MainActivity;
import HKR.HKIF.data.MemberCard;
import HKR.HKIF.R;
import HKR.HKIF.dialogs.DeleteDialog;
import HKR.HKIF.dialogs.SetPositionDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MemberListAdapter extends ArrayAdapter<MemberCard> {

    private List<MemberCard> cardList = new ArrayList<>();

    static class CardViewHolder {
        TextView name;
        TextView position;
        TextView email;
        FloatingActionButton delete;
        FloatingActionButton edit;

    }

    public MemberListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(MemberCard object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public MemberCard getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        CardViewHolder viewHolder;

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_card, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.name =  row.findViewById(R.id.name);
            viewHolder.email =  row.findViewById(R.id.email);
            viewHolder.position =  row.findViewById(R.id.position);
            viewHolder.delete =  row.findViewById(R.id.delete);
            viewHolder.edit =  row.findViewById(R.id.edit);
            row.setTag(viewHolder);

        } else {

            viewHolder = (CardViewHolder)row.getTag();
        }

        final MemberCard card = getItem(position);
        viewHolder.name.setText(card.getName());
        viewHolder.position.setText(card.getPosition());
        viewHolder.email.setText(card.getEmail());

        //TODO add function to these two buttons
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "button " + position, Toast.LENGTH_SHORT).show(); //meh

                FragmentManager manager = ((AppCompatActivity)getContext()).getSupportFragmentManager();// to show the dialog
                new DeleteDialog(card.getName()).show(manager,"delete");
            }
        });




        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "button " + position, Toast.LENGTH_SHORT).show();

                FragmentManager manager = ((AppCompatActivity)getContext()).getSupportFragmentManager(); // to show the dialog
                new SetPositionDialog(position).show(manager,"delete");

            }
        });

        return row;
    }
}