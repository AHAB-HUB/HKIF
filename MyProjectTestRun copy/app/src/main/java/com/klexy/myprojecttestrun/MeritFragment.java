package com.klexy.myprojecttestrun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MeritFragment extends Fragment {

    View view;
    ListView listView;
    String[] mer;

    public MeritFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_merit,container,false);

        mer = view.getResources().getStringArray(R.array.merit);
        listView = (ListView) view.findViewById(R.id.text_merit);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,mer);

        listView.setAdapter(listAdapter);

        return view;
    }
}
