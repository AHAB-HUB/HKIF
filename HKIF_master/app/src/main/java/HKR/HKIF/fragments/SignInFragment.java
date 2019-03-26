package HKR.HKIF.fragments;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import HKR.HKIF.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SignInFragment extends Fragment implements View.OnClickListener {

    private EditText email, password;
    private String role;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private NavigationView navigationView1;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDb;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.btn_login).setOnClickListener(this);
        getActivity().findViewById(R.id.link_signup).setOnClickListener(this);
        navigationView1 = getActivity().findViewById(R.id.nav_view);

        mAuth = FirebaseAuth.getInstance();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        email = view.findViewById(R.id.logIn_email);
        password = view.findViewById(R.id.logIn_password);
        progressBar = view.findViewById(R.id.sign_in_progressBar);

        return view;
    }

    private void userLogin() {

        String emailLog = email.getText().toString().trim();
        String passwordLog = password.getText().toString().trim();

        if (emailLog.isEmpty()) {
            email.setError("Email must be enter");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailLog).matches()) {
            email.setError("Invalid email format");
            email.requestFocus();
            return;
        }
        if (passwordLog.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (passwordLog.length() < 6) {
            password.setError("Password must be 6 letters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailLog, passwordLog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {

                    mAuth = FirebaseAuth.getInstance();
                    mDatabase = FirebaseDatabase.getInstance();
                    mDb = mDatabase.getReference();
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userKey = user.getUid();

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
                    FragmentTransaction fragmentHome = getFragmentManager().beginTransaction();
                    fragmentHome.replace(R.id.fragment_container, new HomeFragment());
                    fragmentHome.commit();

                    //if need to create and navigate to user profile
                    //Intent intent = new Intent(getActivity(), HomeFragment.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                userLogin();
                break;

            case R.id.link_signup:

                FragmentTransaction fragment2 = getFragmentManager().beginTransaction();
                fragment2.replace(R.id.fragment_container, new SignUpFragment());
                fragment2.commit();

                break;
        }


    }
}
