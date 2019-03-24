package HKR.HKIF.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;
import HKR.HKIF.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class SessionManagement extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ListView smListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_management);
        smListView = findViewById(R.id.lvSession_management);

        setupSportListView();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        String question = "Do you want to cancel the session ?";
        String current_sport = "Football";
        Button okBtn,noBtn;

        TextView date =  findViewById(R.id.event_date);
        TextView sport =  findViewById(R.id.sport_name);
        TextView confirmation_question =  findViewById(R.id.confirmation_question);
        okBtn = findViewById(R.id.okBtn);
        noBtn = findViewById(R.id.noBtn);

        


        date.setText(currentDateString);
        sport.setText(current_sport);
        confirmation_question.setText(question);

        okBtn.setVisibility(view.VISIBLE);
        okBtn.setText("YES");
        noBtn.setVisibility(view.VISIBLE);
        noBtn.setText("NO");


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void setupSportListView(){

        String[] title = getResources().getStringArray(R.array.SessionManagement);

        Items_Adapter simpleAdapter = new Items_Adapter(this, title);
        smListView.setAdapter(simpleAdapter);

        smListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
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
