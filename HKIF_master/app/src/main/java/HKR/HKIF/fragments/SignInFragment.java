package HKR.HKIF.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import HKR.HKIF.MainActivity;
import HKR.HKIF.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SignInFragment extends Fragment implements View.OnClickListener {

    private EditText email, password;
    private TextView signUpLink;
    private Button logInBtn;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.btn_login).setOnClickListener(this);
        getActivity().findViewById(R.id.link_signup).setOnClickListener(this);

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

    private void userLogin(){

        String emailLog = email.getText().toString().trim();
        String passwordLog = password.getText().toString().trim();

        if (emailLog.isEmpty()){
            email.setError("Email must be enter");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailLog).matches()){
            email.setError("Invalid email format");
            email.requestFocus();
            return;
        }
        if (passwordLog.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (passwordLog.length() < 6){
            password.setError("Password must be 6 letters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailLog,passwordLog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()){

                    FragmentTransaction fragmentHome = getFragmentManager().beginTransaction();
                    fragmentHome.replace(R.id.fragment_container,new HomeFragment());
                    fragmentHome.commit();

                    //if need to create and navigate to user profile
                    //Intent intent = new Intent(getActivity(), HomeFragment.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_login:
                userLogin();

                break;

            case R.id.link_signup:

                FragmentTransaction fragment2 = getFragmentManager().beginTransaction();
                fragment2.replace(R.id.fragment_container,new SignUpFragment());
                fragment2.commit();

                break;
        }


    }
}
