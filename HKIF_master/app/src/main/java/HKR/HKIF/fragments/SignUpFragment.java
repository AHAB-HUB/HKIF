package HKR.HKIF.fragments;

import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import HKR.HKIF.R;
import HKR.HKIF.Users.Person;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {

    private EditText firstName, lastName, email, password, phoneNumber;
    private Button signUpBtn;
    private DatabaseReference databasePerson;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    private void signUp(){
        String firstNameText = firstName.getText().toString();
        String lastNameText  = lastName.getText().toString();
        String emailText     = email.getText().toString();
        String passwordText  = password.getText().toString();
        String phoneNumberText = phoneNumber.getText().toString();

        if (TextUtils.isEmpty(firstNameText) && TextUtils.isEmpty(lastNameText) &&
            TextUtils.isEmpty(emailText)     && TextUtils.isEmpty(passwordText) &&
            TextUtils.isEmpty(phoneNumberText)){

            Toast.makeText(getContext(), "Please fill all the fields!", Toast.LENGTH_LONG).show();

        } else {

            final DateFormat dateFormat = DateFormat.getDateInstance();
            String format = dateFormat.format(new Date());

            final String position = String.valueOf(Person.POSITION.MEMBER);
            final String ID = databasePerson.push().getKey();


            Person person = new Person(ID, firstNameText, lastNameText, emailText, passwordText,
                    phoneNumberText, position, false, format);

            databasePerson.child(ID).setValue(person);

            Toast.makeText(getContext(), "Done!", Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_sign_up, container, false);

        firstName   = view.findViewById(R.id.signUp_firstName);
        lastName    = view.findViewById(R.id.signUp_lastName);
        email       = view.findViewById(R.id.signUp_email);
        password    = view.findViewById(R.id.signUp_password);
        phoneNumber = view.findViewById(R.id.signUp_phone_number);
        signUpBtn   = view.findViewById(R.id.btn_signUp);

        databasePerson = FirebaseDatabase.getInstance().getReference("person");

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        return view;
    }
}
