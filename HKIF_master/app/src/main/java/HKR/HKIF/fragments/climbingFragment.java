package HKR.HKIF.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import HKR.HKIF.R;
import androidx.fragment.app.Fragment;



public class climbingFragment extends Fragment {
    private TextView climDesriptopnTextView;
    private String description;

    public climbingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_climbing, container, false);
    }
}