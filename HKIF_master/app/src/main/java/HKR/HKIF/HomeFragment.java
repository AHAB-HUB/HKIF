package HKR.HKIF;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {


    NavigationView navigationView1;

    Button btn1, btn2;
    String id;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        navigationView1 = getActivity().findViewById(R.id.nav_view);

        btn1 = getActivity().findViewById(R.id.volley_btn);
        btn2 = getActivity().findViewById(R.id.badminton_btn);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = "member";
                navigationView1.getMenu().clear();
                navigationView1.inflateMenu(R.menu.drawer_navigation_member);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = "admin";
                navigationView1.getMenu().clear();
                navigationView1.inflateMenu(R.menu.drawer_navigation_admin);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
