package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import HKR.HKIF.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;*/

public class OffCampusFragment extends Fragment {

    View view;
    TextView textView;
    ListView listView;

    String[] offCampus;

    public OffCampusFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_offcampus, container, false);

        //Resources res = getResources();
        offCampus = view.getResources().getStringArray(R.array.offcampus);

        //listView = (ListView) view.findViewById(R.id.text_offcampus);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, offCampus);

        //listView.setAdapter(listAdapter);

        return view;
    }
}
