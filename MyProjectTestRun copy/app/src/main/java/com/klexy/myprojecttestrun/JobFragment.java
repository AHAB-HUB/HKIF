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

public class JobFragment extends Fragment {

    View view;
    ListView listView;
    String[] jobb;

    public JobFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_job,container,false);


        jobb = view.getResources().getStringArray(R.array.job);
        listView = (ListView) view.findViewById(R.id.text_job);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,jobb);

        listView.setAdapter(listAdapter);

        return view;
    }
}
