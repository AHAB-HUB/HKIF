package HKR.HKIF.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Objects;

import HKR.HKIF.data.MemberCard;
import HKR.HKIF.adapters.MemberListAdapter;
import HKR.HKIF.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MembersListFragment extends Fragment {


    private static final String TAG = "CardListActivity";

    private MemberListAdapter cardArrayAdapter;
    private ListView listView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        listView = Objects.requireNonNull(getActivity()). findViewById(R.id.card_listView);

        listView.addHeaderView(new View(getContext()));
        listView.addFooterView(new View(getContext()));

        cardArrayAdapter = new MemberListAdapter(getContext(), R.layout.list_item_card);

        //TODO add items here
        for (int i = 0; i < 10; i++) {
            MemberCard card = new MemberCard("Ahmad Abdulal" ,"AhmadAbdulal@outlook.com", "Member");

            cardArrayAdapter.add(card);
        }


        listView.setAdapter(cardArrayAdapter);

    }
}
