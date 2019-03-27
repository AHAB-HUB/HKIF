package HKR.HKIF.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DateFormat;
import java.util.Calendar;

import HKR.HKIF.ImagesActivity;
import HKR.HKIF.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class SessionManagement extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ListView smListView;
    private String id_sport,current_sport,currentDateString,day,question,dayMismatch;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDb;
    private TextView date,sport,confirmation_question;
    private Button okBtn,noBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_management);
        smListView = findViewById(R.id.lvSession_management);

        date = findViewById(R.id.event_date);
        sport = findViewById(R.id.sport_name);
        confirmation_question = findViewById(R.id.confirmation_question);
        okBtn = findViewById(R.id.okBtn);
        noBtn = findViewById(R.id.noBtn);



        setupSportListView();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        //day = currentDateString.substring(0, currentDateString.indexOf(' '));
        question = "Do you want to cancel the session ?";
        dayMismatch = "is not scheduled today. Check above schedule! ";


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDb = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        final String userKey = user.getUid();


        mDb.child("sport_leaders").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Sport leader key:  "+userKey);
                id_sport = String.valueOf(dataSnapshot.child("sportID").getValue());
                Log.d(TAG, "Sport id : " + id_sport);


                mDb.child("sport").child(id_sport).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println("Sport id : " + id_sport);
                        current_sport = String.valueOf(dataSnapshot.child("sport_name").getValue());
                        System.out.println(current_sport);
                        Log.d(TAG, "Sport name : " + current_sport);



                        switch (current_sport){

                            case "Climbing":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Tuesday,")
                                        || day.equalsIgnoreCase("Sunday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");

                                    switch (day) {
                                        case "Tuesday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_MnlaPxHSSY9ZpUg").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                    date.setText("");
                                                    sport.setText("");
                                                    confirmation_question.setText("");
                                                    okBtn.setVisibility(View.INVISIBLE);
                                                    okBtn.setText("");
                                                    noBtn.setVisibility(View.INVISIBLE);
                                                    noBtn.setText("");
                                                }
                                            });
                                            break;

                                        case "Sunday":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_MnyhR0_29belPsf").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                    }
                                }else {

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;



                            case "Jiu-Jitsu":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Thursday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");

                                    switch (day) {
                                        case "Thursday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_MnrNmpeakWo-_Vk").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                    date.setText("");
                                                    sport.setText("");
                                                    confirmation_question.setText("");
                                                    okBtn.setVisibility(View.INVISIBLE);
                                                    okBtn.setText("");
                                                    noBtn.setVisibility(View.INVISIBLE);
                                                    noBtn.setText("");
                                                }
                                            });
                                            break;
                                    }
                                }else {

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;




                            case "Soccer":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Thursday,")
                                        || day.equalsIgnoreCase("Sunday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");


                                    switch (day) {
                                        case "Thursday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_Mnszyx-H8JZoaBF").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                    date.setText("");
                                                    sport.setText("");
                                                    confirmation_question.setText("");
                                                    okBtn.setVisibility(View.INVISIBLE);
                                                    okBtn.setText("");
                                                    noBtn.setVisibility(View.INVISIBLE);
                                                    noBtn.setText("");
                                                }
                                            });
                                            break;

                                        case "Sunday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_MnwmUgG7vhgfszL").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                    }
                                }else {

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;



                            case "Boxing":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Wednesday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");



                                    switch (day) {
                                        case "Wednesday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_Mnn-sgiGSt1fM_Q").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                    date.setText("");
                                                    sport.setText("");
                                                    confirmation_question.setText("");
                                                    okBtn.setVisibility(View.INVISIBLE);
                                                    okBtn.setText("");
                                                    noBtn.setVisibility(View.INVISIBLE);
                                                    noBtn.setText("");
                                                }
                                            });
                                            break;
                                    }
                                }else {

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;




                            case "Volleyball":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Tuesday,")
                                        || day.equalsIgnoreCase("Friday,")
                                        || day.equalsIgnoreCase("Sunday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");


                                    switch (day) {
                                        case "Tuesday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_MngaaNXoKtfJYH3").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            break;

                                        case "Friday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_MnvV6eyjIql9PCS").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                        case "Sunday":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_MnxJwzgPYafs71T").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                    }
                                }else {

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;






                            case "Swimming":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Monday,")
                                        || day.equalsIgnoreCase("Thursday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");


                                    switch (day) {
                                        case "Monday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_MncjPoY8AcQLLMs").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                    date.setText("");
                                                    sport.setText("");
                                                    confirmation_question.setText("");
                                                    okBtn.setVisibility(View.INVISIBLE);
                                                    okBtn.setText("");
                                                    noBtn.setVisibility(View.INVISIBLE);
                                                    noBtn.setText("");
                                                }
                                            });
                                            break;

                                        case "Thursday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_Mntq996Sn6VLQzS").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                    }
                                }else {

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;





                            case "Basketball":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Monday,")
                                        || day.equalsIgnoreCase("Thursday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");


                                    switch (day) {
                                        case "Monday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_MnaysCdJ-wv89br").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                    date.setText("");
                                                    sport.setText("");
                                                    confirmation_question.setText("");
                                                    okBtn.setVisibility(View.INVISIBLE);
                                                    okBtn.setText("");
                                                    noBtn.setVisibility(View.INVISIBLE);
                                                    noBtn.setText("");
                                                }
                                            });
                                            break;

                                        case "Thursday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_MnqcYUvWXD35dhe").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                    }
                                }else {

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;





                            case "Badminton":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Monday,")
                                        || day.equalsIgnoreCase("Tuesday,")
                                        || day.equalsIgnoreCase("Friday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");

                                    switch (day) {
                                        case "Monday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_Mn_kNFAyVk36ALJ").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                    date.setText("");
                                                    sport.setText("");
                                                    confirmation_question.setText("");
                                                    okBtn.setVisibility(View.INVISIBLE);
                                                    okBtn.setText("");
                                                    noBtn.setVisibility(View.INVISIBLE);
                                                    noBtn.setText("");
                                                }
                                            });
                                            break;

                                        case "Tuesday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_MnfhWu-sGigQPqv").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;

                                        case "Friday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_Mnus8wcUWSvtFyL").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                    }
                                }else {

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;





                            case "Floorball":
                                day = currentDateString.substring(0, currentDateString.indexOf(' '));
                                System.out.println("Day : "+day);
                                if (day.equalsIgnoreCase("Monday,")
                                        || day.equalsIgnoreCase("Wednesday,")
                                        || day.equalsIgnoreCase("Sunday,")){

                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(question);

                                    okBtn.setVisibility(View.VISIBLE);
                                    okBtn.setText("YES");
                                    noBtn.setVisibility(View.VISIBLE);
                                    noBtn.setText("NO");


                                    switch (day) {
                                        case "Monday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatabaseReference query = FirebaseDatabase.getInstance()
                                                            .getReference("schedule").child("-Laf_Mn5cvX7S10hDy9l").child("canceled");
                                                    query.setValue("true");
                                                    Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                    date.setText("");
                                                    sport.setText("");
                                                    confirmation_question.setText("");
                                                    okBtn.setVisibility(View.INVISIBLE);
                                                    okBtn.setText("");
                                                    noBtn.setVisibility(View.INVISIBLE);
                                                    noBtn.setText("");
                                                }
                                            });
                                            break;

                                        case "Wednesday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-Laf_MnpOoJweDMbV84A").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;

                                        case "Sunday,":
                                            okBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    okBtn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            DatabaseReference query = FirebaseDatabase.getInstance()
                                                                    .getReference("schedule").child("-LawKwKzf1bPQesie6Yl").child("canceled");
                                                            query.setValue("true");
                                                            Toast.makeText(SessionManagement.this, "session canceled!", Toast.LENGTH_SHORT).show();

                                                            date.setText("");
                                                            sport.setText("");
                                                            confirmation_question.setText("");
                                                            okBtn.setVisibility(View.INVISIBLE);
                                                            okBtn.setText("");
                                                            noBtn.setVisibility(View.INVISIBLE);
                                                            noBtn.setText("");
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                    }

                                }else {
                                    date.setText(currentDateString);
                                    sport.setText(current_sport);
                                    confirmation_question.setText(dayMismatch);
                                }
                                break;
                        }
                        noBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                date.setText("");
                                sport.setText("");
                                confirmation_question.setText("");
                                okBtn.setVisibility(View.INVISIBLE);
                                okBtn.setText("");
                                noBtn.setVisibility(View.INVISIBLE);
                                noBtn.setText("");

                            }
                        });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setupSportListView() {

        String[] title = getResources().getStringArray(R.array.SessionManagement);

        Items_Adapter simpleAdapter = new Items_Adapter(this, title);
        smListView.setAdapter(simpleAdapter);

        smListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        DialogFragment datePicker = new DatePickerFragment();
                        datePicker.show(getSupportFragmentManager(), "time picker");
                        break;
                    }
                }
            }
        });
    }

    public class Items_Adapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title;
        private String[] titleArray;
        private ImageView imageView;

        public Items_Adapter(Context context, String[] title) {
            mContext = context;
            titleArray = title;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.single_sm_item, null);
            }

            title = convertView.findViewById(R.id.item_title);
            imageView = convertView.findViewById(R.id.item_pic);


            title.setText(titleArray[position]);


            if (titleArray[position].equalsIgnoreCase("Cancel session")) {
                imageView.setImageResource(R.drawable.timetable);
            }

            return convertView;

        }
    }

}
