package compumovil.udea.edu.co.gr7.lab2apprun;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private UsersManager users;

    private ListView listView;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.app_name);

        users = new UsersManager(this);

        if (users.is_logged()) {
            // Load normal activities
            listView = (ListView) findViewById(R.id.left_frame);

            drawerLayout = (DrawerLayout) findViewById(R.id.container_main);

            listView.setAdapter(ArrayAdapter.createFromResource(this, R.array.options,
                    android.R.layout.simple_spinner_dropdown_item));

            listView.setOnItemClickListener(this);

            Fragment fragment = new EventsFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit();

            listView.setItemChecked(0, true);

            String title = getResources().getStringArray(R.array.options)[0];

            getSupportActionBar().setTitle(title);
        } else {
            // Open login activity
            Intent intent = new Intent(this, SignInActivity.class);

            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new EventsFragment();
                break;
            case 1:
                fragment = new ProfileFragment();
                break;
            case 2:
                fragment = new AboutFragment();
                break;
            case 3:
                users.logout();
                Intent intent = new Intent(view.getContext(), SignInActivity.class);

                startActivity(intent);
                return;
        }

         FragmentManager fragmentManager = getSupportFragmentManager();

         fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit();

         listView.setItemChecked(position, true);

         String title = getResources().getStringArray(R.array.options)[position];

         getSupportActionBar().setTitle(title);

         drawerLayout.closeDrawer(listView);
    }
}
