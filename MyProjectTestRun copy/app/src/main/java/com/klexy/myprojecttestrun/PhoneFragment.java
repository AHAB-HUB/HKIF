package com.klexy.myprojecttestrun;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PhoneFragment extends Fragment {

    static MainActivity mainActivity = new MainActivity();

    private static final int REQUEST_CALL =1;
    private EditText editTextNumber;
    private ImageView imageCallIcon;

    public PhoneFragment(){

    }


    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        editTextNumber = getActivity().findViewById(R.id.edit_number);
        imageCallIcon = getActivity().findViewById(R.id.phone_call);

        imageCallIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }

    /*private void makePhoneCall(){

        String number = editTextNumber.getText().toString();

        if (number.trim().length() > 0){

            if (ContextCompat.checkSelfPermission(PhoneFragment.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(PhoneFragment.this,
                new String[]{ Manifest.permission.CALL_PHONE},REQUEST_CALL);

            }else{
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }else {
            Toast.makeText(PhoneFragment.this,"Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CALL){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else {
                Toast.makeText(this,"Permission DENIED",Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phone,container,false);
    }
}
