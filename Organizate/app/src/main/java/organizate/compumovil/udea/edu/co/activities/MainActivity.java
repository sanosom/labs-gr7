package organizate.compumovil.udea.edu.co.activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ListView;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.managers.EventCursor;
import organizate.compumovil.udea.edu.co.managers.EventManager;

public class MainActivity extends AppCompatActivity {

    EventCursor adapter;

    ListView listView;

    EventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }

        eventManager = new EventManager(getApplicationContext());

        listView = (ListView) findViewById(R.id.event_list);

        Cursor activities = eventManager.read();

        adapter = new EventCursor(this, activities, 0);

        listView.setAdapter(adapter);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_alarm)
                .setProgress(100, 10, false)
                .addAction(R.drawable.ic_alarm_off, "Terminar", null)
                .addAction(R.drawable.ic_alarm_add, "Agregar 10 minutos", null)
                .setContentTitle("Actividad 2")
                .setContentText("Universidad de Antioquia - 27 minutos restantes");

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());
    }

    public void addEvent(View view) {
        Intent intent = new Intent(getApplicationContext(), EditorActivity.class);

        startActivity(intent);
    }

}
