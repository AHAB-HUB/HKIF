package HKR.HKIF.fragments;

import android.os.Bundle;
import android.util.Log;
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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import HKR.HKIF.R;
import HKR.HKIF.Users.Person;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.paperdb.Paper;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ResetPasswordFragment extends Fragment{

    private EditText resetEmail;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDb;
    private TextView resetMessage, confirmMessage;
    private Button resetBtn;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        resetBtn = getActivity().findViewById(R.id.btn_reset_pass);
        resetEmail = getActivity().findViewById(R.id.reset_email);

        mAuth = FirebaseAuth.getInstance();

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = resetEmail.getText().toString().trim();

                if (userEmail.equals("")){

                    Toast.makeText(getContext(), "Email can't be empty", Toast.LENGTH_SHORT).show();

                    confirmMessage.setText("EMAIL can't be empty!");
                }
                else {

                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()){

                               confirmMessage.setText("Follow link sent to the EMAIL entered");

                            }
                            else {
                                Toast.makeText(getContext(),"Check the email entered",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        resetMessage = view.findViewById(R.id.reset_info);
        confirmMessage = view.findViewById(R.id.reset_confirm_display);
        progressBar = view.findViewById(R.id.reset_pass_progressBar);

        return view;
    }
}
