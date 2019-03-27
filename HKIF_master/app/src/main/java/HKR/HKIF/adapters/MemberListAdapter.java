package HKR.HKIF.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

import HKR.HKIF.R;
import HKR.HKIF.Users.Person;
import HKR.HKIF.dialogs.DeleteDialog;
import HKR.HKIF.dialogs.SetPositionDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MemberListAdapter extends ArrayAdapter<Person> {

    private List<Person> cardList = new ArrayList<>();

    static class CardViewHolder {
        TextView name;
        TextView position;
        TextView email;
        TextView payment;
        FloatingActionButton delete;
        FloatingActionButton edit;

    }

    public MemberListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(Person object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public Person getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        final CardViewHolder viewHolder;

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_card, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.name = row.findViewById(R.id.name);
            viewHolder.email = row.findViewById(R.id.email);
            viewHolder.position = row.findViewById(R.id.position);
            viewHolder.payment = row.findViewById(R.id.payment);
            viewHolder.delete = row.findViewById(R.id.delete);
            viewHolder.edit = row.findViewById(R.id.edit);
            row.setTag(viewHolder);

        } else {

            viewHolder = (CardViewHolder) row.getTag();
        }

        final Person person = getItem(position);
        viewHolder.name.setText(person.getFullName());
        viewHolder.position.setText(person.getPosition());
        viewHolder.email.setText(person.getEmail());
        if (!person.isHasPaid()) {
            viewHolder.payment.setText("Has not paid");
        } else {
            viewHolder.payment.setText("Has paid");
        }

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();// to show the dialog
                    new DeleteDialog(person.getFullName(), person.getPersonID(), person.getPosition()).show(manager, "delete");
                }

        });

        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager(); // to show the dialog
                    new SetPositionDialog(position, person.getPersonID(), person.getPosition()).show(manager, "delete");
                }

        });

        return row;
    }
}