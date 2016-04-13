package organizate.compumovil.udea.edu.co.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.managers.EventManager;

public class EventActivity extends AppCompatActivity {

    EditText _name;
    EditText _place;

    Switch _alarm;
    Switch _repeat;

    DiscreteSeekBar _duration;

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

        _duration.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                seekBar.setPadding(0, 100, 0, 0);
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                seekBar.setPadding(0, 0, 0, 0);
            }
        });
    }

    public void addEvent(View view) {
        // TODO: Save in the db
        finish();
    }
}
