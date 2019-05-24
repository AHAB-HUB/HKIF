package HKR.HKIF.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import HKR.HKIF.R;
import HKR.HKIF.Users.Person;
import HKR.HKIF.adapters.MemberListAdapter;

public class MembersListFragment extends Fragment {

    private MemberListAdapter cardArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.listview, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = Objects.requireNonNull(getActivity()).findViewById(R.id.card_listView);
        listView.addHeaderView(new View(getContext()));
        listView.addFooterView(new View(getContext()));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("person");
        cardArrayAdapter = new MemberListAdapter(getContext(), R.layout.list_item_card);
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot personSnapshot : dataSnapshot.getChildren()) {
                    Person p = personSnapshot.getValue(Person.class);
                    cardArrayAdapter.add(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        listView.setAdapter(cardArrayAdapter);
    }
}
