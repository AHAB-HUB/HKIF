package HKR.HKIF;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import HKR.HKIF.fragments.HomeFragment;
import HKR.HKIF.fragments.MembersListFragment;
import HKR.HKIF.fragments.ScheduleFragment;
import HKR.HKIF.fragments.SessionManagement;
import HKR.HKIF.fragments.SignUpFragment;
import HKR.HKIF.utilities.NotificationListener;
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

        //activate the Notification listener
        new NotificationListener(this,findViewById(android.R.id.content));


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
                new MembersListFragment()).commit();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_guest_sports:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SignUpFragment()).commit();
                break;

            case R.id.nav_guest_gallary:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SignUpFragment()).commit();
                break;

            case R.id.nav_management:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new SessionManagement()).commit();
                Intent intent = new Intent(this, SessionManagement.class);
                startActivity(intent);
                break;

            case R.id.nav_guest_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MembersListFragment()).commit();
                break;

            case R.id.nav_guest_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScheduleFragment()).commit();
                break;

            case R.id.nav_profile:

                break;

                //TODO ADD THE REST OF THE BUTTONS


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //TODO UPDATE THIS METHOD
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment selectFragment;

                    //TODO add the corresponding fragments here
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectFragment = new HomeFragment();
                            break;
                        case R.id.nav_contact:

                            default:
                                selectFragment = new HomeFragment();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectFragment).commit();

                    return true;
                }
            };

}
