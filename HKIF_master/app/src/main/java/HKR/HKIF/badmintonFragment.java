package HKR.HKIF;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class badmintonFragment extends Fragment {
    private TextView badDesriptopnTextView;
    private String description = "The aim of badminton is to hit the shuttle with your racket so that it passes over the net" +
            " and lands inside your opponent's half of the court. " +
            "Whenever you do this, you have won a rally; win enough rallies, and you win the match. " +
            "Your opponent has the same goal.";


    public badmintonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_badminton, container, false);

    }

}
