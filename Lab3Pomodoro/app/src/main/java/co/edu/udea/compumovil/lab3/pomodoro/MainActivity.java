package co.edu.udea.compumovil.lab3.pomodoro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean bound = false;

    private PomodoroService pomodoroService;

    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            bound = true;

            PomodoroService.PomodoroBinder binder = (PomodoroService.PomodoroBinder) service;

            pomodoroService = binder.getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            bound = false;
            pomodoroService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(getBaseContext(), PomodoroService.class);

        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

    public void stopPomodoro(View view) {
        if (!bound) return;

        pomodoroService.stop();
    }

    public void startPomodoro(View view) {
        if (!bound) return;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        pomodoroService.start(25, sharedPref.getBoolean("debug", false));
    }

    public void configPomodoro(View view) {
        startActivity(new Intent(getBaseContext(), SettingsActivity.class));
    }

}
