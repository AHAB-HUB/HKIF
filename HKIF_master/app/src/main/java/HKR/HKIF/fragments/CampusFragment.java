package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import HKR.HKIF.R;

/*import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;*/

public class CampusFragment extends Fragment {

    private View view;
    private ListView listView;
    private String[] campus;

    public CampusFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_campus, container, false);
        campus = view.getResources().getStringArray(R.array.campus);
        listView = view.findViewById(R.id.text_campus);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, campus);

        return view;
    }
}
