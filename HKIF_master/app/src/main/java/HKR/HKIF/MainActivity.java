package HKR.HKIF;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import HKR.HKIF.dB.GoingUpdater;
import HKR.HKIF.extraReq.AttendanceHistoryFragment;
import HKR.HKIF.extraReq.InboxFragment;
import HKR.HKIF.fragments.AboutFragment;
import HKR.HKIF.fragments.DaysFragment;
import HKR.HKIF.fragments.HomeFragment;
import HKR.HKIF.fragments.LocationFragment;
import HKR.HKIF.fragments.MembersListFragment;
import HKR.HKIF.fragments.MessageFragment;
import HKR.HKIF.fragments.ProfileFragment;
import HKR.HKIF.fragments.SessionManagement;
import HKR.HKIF.fragments.SignInFragment;
import HKR.HKIF.utilities.NotificationListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import static HKR.HKIF.R.id;
import static HKR.HKIF.R.layout;
import static HKR.HKIF.R.string;
import static android.Manifest.permission.CALL_PHONE;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView1;
    private ImageButton imageMessage, imagePhone;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        //activate the Notification listener
        new NotificationListener(this, findViewById(android.R.id.content));

        //Update Going
        new GoingUpdater();


        BottomNavigationView bottomNav = findViewById(id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(id.drawer_layout);
        navigationView1 = findViewById(id.nav_view);
        navigationView1.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                string.navigation_drawer_open, string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                new HomeFragment()).commit();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case id.nav_guest_gallery:
            case id.leader_gallery:
            //case id.admin_gallery:
                Intent gallery = new Intent(this, MyAlbum.class);
                startActivity(gallery);
                break;

            case id.leader_management:
                Intent sessionManagement = new Intent(this, SessionManagement.class);
                startActivity(sessionManagement);
                break;

            case id.nav_guest_contact:
                contactDialog();   // this open the option dialog for call/message
                break;

            case id.member_contact:
            case id.leader_contact:
            //case id.admin_members:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                        new MembersListFragment()).addToBackStack(null).commit();
                break;

            case id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                        new AboutFragment()).addToBackStack(null).commit();
                break;


            case id.nav_guest_logIn:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                        new SignInFragment()).addToBackStack(null).commit();
                break;

            case id.mem_history:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                        new AttendanceHistoryFragment()).addToBackStack(null).commit();
                break;
            case id.mem_inbox:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                        new InboxFragment()).addToBackStack(null).commit();
                break;

            case id.member_logOut:
                //sign out
                FirebaseAuth.getInstance().signOut();
                //set side menu
                navigationView1.getMenu().clear();
                navigationView1.inflateMenu(R.menu.drawer_navigation_guest);
                //go back home
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                        new HomeFragment()).commit();

                break;

            //case id.admin_profile:
            case id.leader_profile:
            case id.member_profile:

                getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                        new ProfileFragment()).commit();

                break;

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

                    switch (menuItem.getItemId()) {
                        case id.bottom_nav_home:
                            getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                                    new HomeFragment()).commit();
                            break;

                        case id.bottom_nav_findUs:
                            getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                                    new LocationFragment()).addToBackStack(null).commit();

                            break;

                        case id.bottom_nav_schedule:
                            getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                                    new DaysFragment()).addToBackStack(null).commit();
                            break;

                    }
                    return true;
                }
            };

    public void contactDialog() {

        final AlertDialog.Builder contactAlert = new AlertDialog.Builder(MainActivity.this);
        View alertView = getLayoutInflater().inflate(R.layout.contact_dialog, null);

        imagePhone = (ImageButton) alertView.findViewById(R.id.contact_phone);
        imageMessage = (ImageButton) alertView.findViewById(R.id.contact_message);
        cancelButton = (Button) alertView.findViewById(R.id.cancel_contact);

        contactAlert.setView(alertView);

        final AlertDialog alertDialog = contactAlert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        imagePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        imageMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                        new MessageFragment()).addToBackStack(null).commit();

            }
        });

        alertDialog.show();
    }

    public void callPhone() {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:0736565835"));

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }
        }


    }

}
