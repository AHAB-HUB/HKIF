package HKR.HKIF.utilities;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rey.material.widget.CheckBox;

public class LoadingSplash extends AppCompatActivity {


    static LottieAnimationView lottieAnimationView;
    private static int splashTime = 5000;
    CheckBox checkBox;
    private EditText email, password;
    private String role;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private NavigationView navigationView1;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDb;
    private TextView resetPass;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_splash);

        mAuth = FirebaseAuth.getInstance();
        navigationView1 = findViewById(R.id.nav_view);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mAuth.signInWithEmailAndPassword(SignInFragment.emailLog, SignInFragment.passwordLog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            mAuth = FirebaseAuth.getInstance();
                            mDatabase = FirebaseDatabase.getInstance();
                            mDb = mDatabase.getReference();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userKey = user.getUid();
                            System.out.println(userKey);


                            mDb.child("person").child(userKey).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    role = String.valueOf(dataSnapshot.child("position").getValue());
                                    Log.d(TAG, "Position : " + role);



                                    switch (role) {
                                        case "MEMBER":
                                            navigationView1.getMenu().clear();
                                            navigationView1.inflateMenu(R.menu.drawer_navigation_member);

                                            break;
                                        case "TEAM_LEADER":
                                            navigationView1.getMenu().clear();
                                            navigationView1.inflateMenu(R.menu.drawer_navigation_team_leader);
                                            break;
                                        case "ADMIN":
                                            navigationView1.getMenu().clear();
                                            navigationView1.inflateMenu(R.menu.drawer_navigation_admin);
                                            break;
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }

                            });

                            startActivity(new Intent(LoadingSplash.this, LoadExtention.class));
                            finish();

                            *//* FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    ft.replace(R.id.fragment_container,
                                    new HomeFragment());
                                    ft.commit();*//*

                        } else {
                            startActivity(new Intent(LoadingSplash.this, SignInFragment.class));
                            //Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        }, splashTime);
    }*/
}



