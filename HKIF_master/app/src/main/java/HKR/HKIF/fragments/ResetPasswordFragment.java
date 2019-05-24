package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import HKR.HKIF.R;

public class ResetPasswordFragment extends Fragment {

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

                if (userEmail.equals("")) {
                    Toast.makeText(getContext(), "Email can't be empty", Toast.LENGTH_SHORT).show();
                    confirmMessage.setText("EMAIL can't be empty!");
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {
                                confirmMessage.setText("Follow link sent to the EMAIL entered");
                            } else {
                                Toast.makeText(getContext(), "Check the email entered", Toast.LENGTH_SHORT).show();
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
