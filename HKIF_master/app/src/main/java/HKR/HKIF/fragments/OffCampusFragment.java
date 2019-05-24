package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import HKR.HKIF.R;


public class OffCampusFragment extends Fragment {

    public OffCampusFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offcampus, container, false);
        String[] offCampus = view.getResources().getStringArray(R.array.offcampus);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, offCampus);

        return view;
    }
}
