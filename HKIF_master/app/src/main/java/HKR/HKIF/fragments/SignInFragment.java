package HKR.HKIF.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import HKR.HKIF.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignInFragment extends Fragment {

    private EditText email, password;
    private TextView signUpLink;
    private Button logInBtn;
    private ProgressBar progressBar;
    private Context context;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        context = view.getContext();
        firebaseAuth = FirebaseAuth.getInstance();

        email = view.findViewById(R.id.logIn_email);
        password = view.findViewById(R.id.logIn_password);
        signUpLink = view.findViewById(R.id.link_signup);
        logInBtn = view.findViewById(R.id.btn_login);
        progressBar = view.findViewById(R.id.sign_in_progressBar);
        progressBar.setVisibility(view.GONE);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser() != null){


                    Toast.makeText(context, "You already loged in", Toast.LENGTH_LONG).show();

                }
                else {
                    userLogIn();
                }

            }
        });

        return view;


    }



    private void userLogIn(){
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailText)){
            Toast.makeText(context, "The email field is empty", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(passwordText)){
            Toast.makeText(context, "The password field is empty", Toast.LENGTH_LONG).show();
            return;
        }

        progressBar.setVisibility(getView().VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(getView().GONE);
                        if (task.isSuccessful()){
                            Toast.makeText(context, "Log In Successfull", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(context, "Log In Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }
}
