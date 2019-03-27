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



        //setupSportListView();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
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
