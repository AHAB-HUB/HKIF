package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import HKR.HKIF.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;*/

public class CampusFragment extends Fragment {

    View view;
    ListView listView;
    String[] campus;

    public CampusFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_campus,container,false);


        campus = view.getResources().getStringArray(R.array.campus);
        listView = (ListView) view.findViewById(R.id.text_campus);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,campus);

        //listView.setAdapter(listAdapter);

        return view;
    }
}
