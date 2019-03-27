package HKR.HKIF.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

import HKR.HKIF.R;
import HKR.HKIF.Users.Person;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SignUpFragment extends Fragment {

    private EditText firstName, lastName, email, password, phoneNumber;
    private Button signUpBtn;
    private DatabaseReference databasePerson;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        firstName = view.findViewById(R.id.signUp_firstName);
        lastName = view.findViewById(R.id.signUp_lastName);
        email = view.findViewById(R.id.signUp_email);
        password = view.findViewById(R.id.signUp_password);
        phoneNumber = view.findViewById(R.id.signUp_phone_number);
        signUpBtn = view.findViewById(R.id.btn_signUp);
        progressBar = view.findViewById(R.id.sign_up_progressBar);
        progressBar.setVisibility(view.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        databasePerson = FirebaseDatabase.getInstance().getReference("person");

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void signUp() {
        final String firstNameText = firstName.getText().toString();
        final String lastNameText = lastName.getText().toString();
        final String emailText = email.getText().toString();
        final String passwordText = password.getText().toString();
        final String phoneNumberText = phoneNumber.getText().toString();

        if (TextUtils.isEmpty(firstNameText) && TextUtils.isEmpty(lastNameText) &&
                TextUtils.isEmpty(emailText) && TextUtils.isEmpty(passwordText) &&
                TextUtils.isEmpty(phoneNumberText)) {

            Toast.makeText(getContext(), "Please fill all the fields!", Toast.LENGTH_LONG).show();

        }
        else {

            final DateFormat dateFormat = DateFormat.getDateInstance();
            final String format = dateFormat.format(new Date());

            final String position = String.valueOf(Person.POSITION.MEMBER);
            final String ID = databasePerson.push().getKey();

            progressBar.setVisibility(getView().VISIBLE);


            firebaseAuth.createUserWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthUserCollisionException existEmail) {
                                    Toast.makeText(getContext(), "This email is already exist", Toast.LENGTH_LONG).show();

                                    FragmentTransaction fragmentHome = getFragmentManager().beginTransaction();
                                    fragmentHome.replace(R.id.fragment_container, new SignUpFragment());
                                    fragmentHome.commit();


                                } catch (Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else {

                                progressBar.setVisibility(getView().GONE);

                                final Person person = new Person(firebaseAuth.getUid(), firstNameText,
                                        lastNameText, emailText, passwordText,
                                        phoneNumberText, position, false, format);


                                databasePerson.child(FirebaseAuth.getInstance().getCurrentUser()
                                        .getUid()).setValue(person).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getContext(), "Done!", Toast.LENGTH_LONG).show();
                                    }
                                });

                            }
                        }

                    });
        }
    }

}
