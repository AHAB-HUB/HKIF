package HKR.HKIF.adapters;


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

import HKR.HKIF.data.Card;
import HKR.HKIF.R;

public class CardArrayAdapter  extends ArrayAdapter<Card> {

    private static final String TAG = "CardArrayAdapter";
    private List<Card> cardList = new ArrayList<>();

    static class CardViewHolder {
        TextView name;
        TextView position;
        TextView email;
        FloatingActionButton delete;
        FloatingActionButton edit;

    }

    public CardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(Card object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public Card getItem(int index) {
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






        Card card = getItem(position);
        viewHolder.name.setText(card.getName());
        viewHolder.position.setText(card.getPosition());
        viewHolder.email.setText(card.getEmail());

        //TODO add function to these two buttons
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "button " + position, Toast.LENGTH_SHORT).show();

            }
        });

        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "button " + (position + 1), Toast.LENGTH_SHORT).show();

            }
        });

        return row;
    }

}

