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


public class boxingFragment extends Fragment {
    private TextView boxDesriptopnTextView;
    private String description;


    public boxingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boxing, container, false);
    }

}
