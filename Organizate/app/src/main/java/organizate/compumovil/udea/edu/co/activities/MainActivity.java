package organizate.compumovil.udea.edu.co.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.managers.ActivityCursor;
import organizate.compumovil.udea.edu.co.managers.ActivityManager;

public class MainActivity extends AppCompatActivity {

    ActivityCursor adapter;

    ListView listView;

    ActivityManager activityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.app_name);

        activityManager = new ActivityManager(getApplicationContext());

        listView = (ListView) findViewById(R.id.activity_list);

        Cursor activities = activityManager.read();

        adapter = new ActivityCursor(this, activities, 0);

        listView.setAdapter(adapter);
    }

}
