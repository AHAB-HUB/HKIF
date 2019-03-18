package com.e.hkif_app;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    NavigationView navigationView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView1 = findViewById(R.id.nav_view);
        navigationView1.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new com.e.hkif_app.HomeFragment()).commit();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_about:
                //TODO add the new fragments here
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new com.e.hkif_app.ScheduleFragment()).commit();
                break;
            case R.id.nav_sport | R.id.nav_sports | R.id.nav_sprot:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SignUpFragment()).commit();
                break;
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SignUpFragment()).commit();
                break;
            case R.id.nav_management:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new com.e.hkif_app.SessionManagement()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment selectFragment;

                    //TODO add the corresponding fragments here
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectFragment = new com.e.hkif_app.HomeFragment();
                            break;
                        case R.id.nav_management:
                            selectFragment = new com.e.hkif_app.SessionManagement();
                            break;

                            default:
                            selectFragment = new com.e.hkif_app.HomeFragment();


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectFragment).commit();

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);

                    return true;
                }
            };

}
