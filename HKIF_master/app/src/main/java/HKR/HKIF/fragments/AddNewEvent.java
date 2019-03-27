package HKR.HKIF.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

import HKR.HKIF.R;
import HKR.HKIF.dB.AddNewEventDB;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class AddNewEvent extends Fragment {

    private TextView setFrom, setTo, from, to, setDate, date;
    private Spinner sportPicker;
    private DatePickerDialog datePicker;
    private EditText location;
    private CircularProgressButton loadingMe;

    private String year, day, month, hourF, minuteF,
            hourT, minuteT, sportName, locationText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_event, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setFrom = getActivity().findViewById(R.id.set_time_from);
        setTo = getActivity().findViewById(R.id.set_time_to);
        from = getActivity().findViewById(R.id.time_from);
        to = getActivity().findViewById(R.id.time_to);
        sportPicker = getActivity().findViewById(R.id.sport_picker);
        date = getActivity().findViewById(R.id.date_text);
        setDate = getActivity().findViewById(R.id.set_date);
        location = getActivity().findViewById(R.id.event_location);
        loadingMe = getActivity().findViewById(R.id.loadingMe);

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(setFrom, 0);
            }
        });

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(setTo, 1);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cld = Calendar.getInstance();
                int day = cld.get(Calendar.DAY_OF_MONTH);
                int month = cld.get(Calendar.MONTH);
                int year = cld.get(Calendar.YEAR);


                datePicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                setYear(String.valueOf(year));
                                setMonth(String.valueOf(monthOfYear + 1));
                                setDay(String.valueOf(dayOfMonth));

                                setDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }

                        }, year, month, day);
                datePicker.show();
            }
        });


        final List<String> list = new ArrayList<>();

        list.add("Floorball");
        list.add("Badminton");
        list.add("Basketball");
        list.add("Swimming");
        list.add("Volleyball");
        list.add("Climbing");
        list.add("Boxing");
        list.add("Jiu-Jitsu");
        list.add("Soccer");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportPicker.setAdapter(arrayAdapter);

        sportPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sportName = list.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        loadingMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                locationText = location.getText().toString();

                System.out.println(sportName + "   " + year + "   " + month + "   " + day + "   " + hourF + "   " + minuteF + "   " + hourT + "   " + minuteT + "   " + locationText);

                if (sportName != null && year != null && month != null && day != null && hourF != null && minuteT != null && locationText != null) {
                    System.out.println("5ra");
                    new AddNewEventDB(sportName, year, month, day, hourF, minuteF, hourT, minuteT, locationText);
                } else {
                    System.out.println("mo 5ra");

                }
            }
        });

    }

    private void getTime(final TextView setTime, final int i) {


        int mHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int mMinute = Calendar.getInstance().get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int pHour,
                                          int pMinute) {

                        if (i == 0) {
                            setHourF(String.valueOf(pHour));
                            setMinuteF(String.valueOf(pMinute));
                        } else {
                            setHourT(String.valueOf(pHour));
                            setMinuteT(String.valueOf(pMinute));
                        }


                        if (pMinute < 10) {
                            setTime.setText(String.valueOf(pHour) + ":0" + String.valueOf(pMinute));

                        } else {
                            setTime.setText(String.valueOf(pHour) + ":" + String.valueOf(pMinute));

                        }


                    }
                }, mHour, mMinute, true);

        timePicker.show();
    }


    public TextView getSetFrom() {
        return setFrom;
    }

    public void setSetFrom(TextView setFrom) {
        this.setFrom = setFrom;
    }

    public TextView getSetTo() {
        return setTo;
    }

    public void setSetTo(TextView setTo) {
        this.setTo = setTo;
    }

    public TextView getFrom() {
        return from;
    }

    public void setFrom(TextView from) {
        this.from = from;
    }

    public TextView getTo() {
        return to;
    }

    public void setTo(TextView to) {
        this.to = to;
    }

    public TextView getSetDate() {
        return setDate;
    }

    public void setSetDate(TextView setDate) {
        this.setDate = setDate;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public Spinner getSportPicker() {
        return sportPicker;
    }

    public void setSportPicker(Spinner sportPicker) {
        this.sportPicker = sportPicker;
    }

    public DatePickerDialog getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePickerDialog datePicker) {
        this.datePicker = datePicker;
    }

    public EditText getLocation() {
        return location;
    }

    public void setLocation(EditText location) {
        this.location = location;
    }

    public CircularProgressButton getLoadingMe() {
        return loadingMe;
    }

    public void setLoadingMe(CircularProgressButton loadingMe) {
        this.loadingMe = loadingMe;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getHourF() {
        return hourF;
    }

    public void setHourF(String hourF) {
        this.hourF = hourF;
    }

    public String getMinuteF() {
        return minuteF;
    }

    public void setMinuteF(String minuteF) {
        this.minuteF = minuteF;
    }

    public String getHourT() {
        return hourT;
    }

    public void setHourT(String hourT) {
        this.hourT = hourT;
    }

    public String getMinuteT() {
        return minuteT;
    }

    public void setMinuteT(String minuteT) {
        this.minuteT = minuteT;
    }
}
