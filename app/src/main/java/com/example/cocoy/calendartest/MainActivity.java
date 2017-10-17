package com.example.cocoy.calendartest;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView tvDate,tvFrom,tvTo;
    Button bFrom, bTo;
    private int Fhour, Fminute;
    private int Thour, Tminute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        tvDate = (TextView) findViewById(R.id.textView1);
        tvFrom = (TextView) findViewById(R.id.tvFrom);
        tvTo = (TextView) findViewById(R.id.tvTo);
        bFrom = (Button) findViewById(R.id.bFrom);
        bTo = (Button) findViewById(R.id.bTo);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOf) {
                //String date = year+"/"+month+"/"+dayOf;
                //Log.d("date","OnSelectedDayChange: date: " + date);
                //tvDate.setText("Date is: " + dayOf + " / " + (month+1) + " / " + year);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                Date date = new Date(year, month, dayOf - 1);
                String dayOfWeek = simpledateformat.format(date);
                tvDate.setText("The day is: " + dayOfWeek);
            }
        });

        bFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tvFrom.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, DateFormat.is24HourFormat(MainActivity.this));//Yes 24 hour time

                mTimePicker.show();
            }
        });

        bTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tvTo.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, DateFormat.is24HourFormat(MainActivity.this));//Yes 24 hour time

                mTimePicker.show();
            }
        });

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");

    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {




        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user

        }

    }
}
