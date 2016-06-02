package compumovil.udea.edu.co.gr7.lab2apprun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventsActivity extends AppCompatActivity {

    private EventsManager events;

    private Button add;

    private Button back;

    private EditText name, place, distance, contact_name, contact_email, contact_number;

    private DatePicker date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        events = new EventsManager(this);

        getSupportActionBar().setTitle("Agregar un evento");

        add = (Button) findViewById(R.id.add);
        back = (Button) findViewById(R.id.back);
        name = (EditText) findViewById(R.id.name);
        place = (EditText) findViewById(R.id.place);
        distance = (EditText) findViewById(R.id.distance);
        contact_name = (EditText) findViewById(R.id.contact_name);
        contact_email = (EditText) findViewById(R.id.contact_email);
        contact_number = (EditText) findViewById(R.id.contact_number);
        date = (DatePicker) findViewById(R.id.date);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    events.create(
                            new SimpleDateFormat("yyyy-MM-dd").parse(date.getYear() + "-" + date.getMonth() + "-" + date.getDayOfMonth()),
                            name.getText().toString().trim(),
                            place.getText().toString().trim(),
                            Integer.parseInt(distance.getText().toString().trim()),
                            contact_name.getText().toString().trim(),
                            contact_email.getText().toString().trim(),
                            contact_number.getText().toString().trim()
                    );

                    // Redirect to initial view
                    Toast.makeText(v.getContext(), "Evento guardado!.", Toast.LENGTH_LONG).show();

                    name.setText("");
                    place.setText("");
                    distance.setText("");
                    contact_name.setText("");
                    contact_email.setText("");
                    contact_number.setText("");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
