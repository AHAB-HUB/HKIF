package HKR.HKIF;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpFragment extends Fragment {

    private EditText firstName, lastName, email, password, phoneNumber;
    private Button signUpBtn;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    private void signUp(){
        String firstNameText = firstName.getText().toString();
        String lastNameText = lastName.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String phoneNumberText = phoneNumber.getText().toString();

        Toast.makeText(getActivity(), "Done!", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_sign_up, container, false);

        firstName = view.findViewById(R.id.signUp_firstName);
        lastName = view.findViewById(R.id.signUp_lastName);
        email = view.findViewById(R.id.signUp_email);
        password = view.findViewById(R.id.signUp_password);
        phoneNumber = view.findViewById(R.id.signUp_phone_number);
        signUpBtn = view.findViewById(R.id.btn_signUp);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        return view;
    }
}
