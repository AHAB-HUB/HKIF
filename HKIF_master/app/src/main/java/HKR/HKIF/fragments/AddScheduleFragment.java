package HKR.HKIF.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import HKR.HKIF.Database.AddSchedule;
import HKR.HKIF.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddScheduleFragment extends Fragment {


    private TextView dateView;
    private Spinner locationSpinner, sportSpinner, startTime, endTime;
    private Button saveDataBtn;
    private DatabaseReference databaseReference;
    private AddSchedule addSchedule;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_schedule_fragment, container, false);

        dateView = view.findViewById(R.id.schedule_editText_datePicker);
        saveDataBtn = view.findViewById(R.id.schedule_add_data_btn);
        locationSpinner = view.findViewById(R.id.location_schedule_spinner);
        sportSpinner = view.findViewById(R.id.sport_schedule_spinner);
        startTime = view.findViewById(R.id.schedule_startTime_spinner);
        endTime = view.findViewById(R.id.schedule_endTime_spinner);

        databaseReference = FirebaseDatabase.getInstance()
                .getReference("schedule");


        final Calendar calendar = Calendar.getInstance();

        dateView.setText("2019-03-18");

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

                dateView.setText(sdf.format(calendar.getTime()));
            }

        };

        dateView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSchedule();

                System.out.println("Helloooo");
            }
        });


        return view;

    }


    private void createSchedule() {
        String sport = sportSpinner.getSelectedItem().toString();
        String location = locationSpinner.getSelectedItem().toString();
        String date = dateView.getText().toString();
        String sTime = startTime.getSelectedItem().toString();
        String eTime = endTime.getSelectedItem().toString();
        addSchedule = new AddSchedule(sTime, eTime, false);

        String id = databaseReference.push().getKey();


        System.out.println("HI THERE");
        databaseReference.child(date).child(location).child(sport).setValue(addSchedule)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e.getMessage());
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                System.out.println("Canceled");
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                System.out.println("DONE!");
            }
        });

    }

}
