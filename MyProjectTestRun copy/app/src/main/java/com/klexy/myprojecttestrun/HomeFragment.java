package com.klexy.myprojecttestrun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;

    private AppBarLayout appBarLayout;

    private ViewPager viewPager;
    NavigationView navigationView1;

    Button btn1,btn2;
    String id;

    public HomeFragment(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        tabLayout = (TabLayout) getActivity().findViewById(R.id.tablayout_id);
//        appBarLayout = getActivity().findViewById(R.id.appbarid);
//        viewPager = getActivity().findViewById(R.id.viewpager_id);
//
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
//        viewPagerAdapter.AddFragment(new EducationFragment(),"Education");
//        viewPagerAdapter.AddFragment(new JobFragment(),"Job");
//        viewPagerAdapter.AddFragment(new MeritFragment(), "Merit/Skill");
//
//        viewPager.setAdapter(viewPagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);

        navigationView1 = getActivity().findViewById(R.id.nav_view);




        btn1 = getActivity().findViewById (R.id.volley_btn);
        btn2 = getActivity(). findViewById(R.id.badminton_btn);

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
        return inflater.inflate(R.layout.fragment_home,container,false);
    }


}
