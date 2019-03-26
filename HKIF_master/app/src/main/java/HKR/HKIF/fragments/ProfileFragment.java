package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import HKR.HKIF.R;
import HKR.HKIF.Users.UserProfile;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {


    private EditText firstName, lastName, phoneNumber, payment;
    private Button saveData, payBtn;
    private DatabaseReference databaseReference;
    private UserProfile userProfile;
    private ArrayList<UserProfile> userProfileArrayList;
    private String userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        firstName = view.findViewById(R.id.profile_firstName);
        lastName = view.findViewById(R.id.profile_lastName);
        phoneNumber = view.findViewById(R.id.profile_phone_number);
        payment = view.findViewById(R.id.profile_has_paid);
        saveData = view.findViewById(R.id.btn_profile_saveBtn);
        payBtn = view.findViewById(R.id.btn_profile_payment);

        userProfileArrayList = new ArrayList<>();

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("person").child(userID);


        getInformation();


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    private void getInformation() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfile = dataSnapshot.getValue(UserProfile.class);
                userProfileArrayList.add(userProfile);

                firstName.setText(userProfileArrayList.get(0).getFirstName());
                lastName.setText(userProfileArrayList.get(0).getLastName());
                phoneNumber.setText(userProfileArrayList.get(0).getPhoneNumber());
                payment.setText(String.valueOf(userProfileArrayList.get(0).isUserHasPaid()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
