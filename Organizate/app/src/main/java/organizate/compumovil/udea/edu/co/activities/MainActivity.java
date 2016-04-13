package organizate.compumovil.udea.edu.co.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        getSupportActionBar().setTitle(R.string.app_name);

        eventManager = new EventManager(getApplicationContext());

        listView = (ListView) findViewById(R.id.event_list);

        Cursor activities = eventManager.read();

        adapter = new EventCursor(this, activities, 0);

        listView.setAdapter(adapter);
    }

    public void addEvent(View view) {
        Intent intent = new Intent(getApplicationContext(), EventActivity.class);

        startActivity(intent);
    }

}
