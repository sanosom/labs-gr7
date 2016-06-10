package organizate.compumovil.udea.edu.co.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.Calendar;
import java.util.Date;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.managers.EventManager;
import organizate.compumovil.udea.edu.co.services.Countdown;

public class EditorActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText _name;
    EditText _place;

    Switch _alarm;
    Switch _repeat;

    Integer duration;

    DiscreteSeekBar _duration;

    int mYear = 0, mMonth = 0, mDay = 0, mHour = 0, mMinute = 0;

    EventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        eventManager = new EventManager(getApplicationContext());

        _name = (EditText) findViewById(R.id.event_name);
        _place = (EditText) findViewById(R.id.event_place);

        _alarm = (Switch) findViewById(R.id.event_alarm);
        _repeat = (Switch) findViewById(R.id.event_repeat);

        _duration = (DiscreteSeekBar) findViewById(R.id.event_duration);

        _alarm.setOnCheckedChangeListener(this);

        _duration.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                duration = value;
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                seekBar.setPadding(0, 120, 0, 0);
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                seekBar.setPadding(0, 0, 0, 0);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.event_alarm) {
            if (isChecked) {
                openDateDialog();
            } else {
                mYear = 0;
                mMonth = 0;
                mDay = 0;
                mHour = 0;
                mMinute = 0;
            }
        } else if (buttonView.getId() == R.id.event_repeat) {

        }
    }

    public void openDateDialog() {
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;

                        openTimeDialog();
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    public void openTimeDialog() {
        final Calendar c = Calendar.getInstance();

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void addEvent(View view) {

        Date alarm = new Date(0);

        if (mYear != 0) {
            alarm.setYear(mYear);
            alarm.setMonth(mMonth);
            alarm.setDate(mDay);
            alarm.setHours(mHour);
            alarm.setMinutes(mMinute);
        }

        String name = _name.getText().toString();
        String place = _place.getText().toString();
        String repeat = "";
        Integer category = 0;

        eventManager.create(alarm, name, place, repeat, category, duration);

        Intent countdown = new Intent(getApplicationContext(), Countdown.class);

        countdown.putExtra("time", (long)duration * 1000);
        countdown.putExtra("name", name);
        countdown.putExtra("place", place);

        startService(countdown);

        finish();
    }
}
