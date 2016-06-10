package organizate.compumovil.udea.edu.co.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.fragments.AboutFragment;
import organizate.compumovil.udea.edu.co.fragments.ActivitiesFragment;
import organizate.compumovil.udea.edu.co.fragments.CategoriesFragment;
import organizate.compumovil.udea.edu.co.fragments.ContactsFragment;
import organizate.compumovil.udea.edu.co.services.Countdown;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, FirebaseAuth.AuthStateListener {

    private boolean isLogged = false;

    private ListView listView;

    private ActionBar actionBar;

    private DrawerLayout drawerLayout;

    private FirebaseUser user;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        listView = (ListView) findViewById(R.id.left_frame);

        drawerLayout = (DrawerLayout) findViewById(R.id.container_main);

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();

        if (user != null) {
            isLogged = true;
        }

        firebaseAuth.addAuthStateListener(this);

        replaceOptions();

        loadActivities();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            drawerLayout.openDrawer(listView);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new ActivitiesFragment();
                break;
            case 1:
                fragment = new CategoriesFragment();
                break;
            case 2:
                if (isLogged) {
                    fragment = new ContactsFragment();
                } else {
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);

                    startActivity(intent);

                    return;
                }

                break;
            case 3:
                Intent intent = new Intent(view.getContext(), SettingsActivity.class);

                startActivity(intent);

                return;
            case 4:
                fragment = new AboutFragment();
                break;
            case 5:
                firebaseAuth.signOut();
                return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit();

        listView.setItemChecked(position, true);

        String title = getResources().getStringArray(R.array.options)[position];

        if (actionBar != null) {
            actionBar.setTitle(title);
        }

        drawerLayout.closeDrawer(listView);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        user = firebaseAuth.getCurrentUser();

        if (user != null) {
            isLogged = true;
        } else {
            isLogged = false;
        }

        replaceOptions();
    }

    @Override
    protected void onResume() {
        super.onResume();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (user != null) {
            isLogged = true;
        }

        replaceOptions();
    }

    public void replaceOptions() {
        if (isLogged) {
            listView.setAdapter(ArrayAdapter.createFromResource(this, R.array.options_logged,
                    android.R.layout.simple_spinner_dropdown_item));

            listView.setOnItemClickListener(this);
        } else {
            listView.setAdapter(ArrayAdapter.createFromResource(this, R.array.options,
                    android.R.layout.simple_spinner_dropdown_item));

            listView.setOnItemClickListener(this);
        }
    }

    public void loadActivities() {
        Fragment fragment = new ActivitiesFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit();

        listView.setItemChecked(0, true);

        String title = getResources().getStringArray(R.array.options)[0];

        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
