package com.klexy.myprojecttestrun;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.util.Strings;

public class EducationFragment extends Fragment {

    View view;
    TextView textView;
    ListView listView;
    String[] edu;

    public EducationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_education,container,false);

        //Resources res = getResources();
        edu = view.getResources().getStringArray(R.array.education);
        listView = (ListView) view.findViewById(R.id.text_edu);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,edu);

        listView.setAdapter(listAdapter);

        return view;
    }
}
