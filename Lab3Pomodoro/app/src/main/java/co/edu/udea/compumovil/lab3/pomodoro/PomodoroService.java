package co.edu.udea.compumovil.lab3.pomodoro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class PomodoroService extends Service {

    public static final int POMODORO_PAUSED = 1;

    public static final int POMODORO_RUNNING = 2;

    public static final int POMODORO_STOPPED = 3;

    private int status = POMODORO_STOPPED;

    private int period = 1000;

    private long end = 0;

    private AlarmManager alarmManager;

    private PendingIntent pendingIntent;

    private final IBinder iBinder = new PomodoroBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public class PomodoroBinder extends Binder {
        PomodoroService getService() {
            // Return this instance of LocalService so clients can call public methods
            return PomodoroService.this;
        }
    }

    public void stop() {
        if (status == POMODORO_PAUSED) return;

        end = 0;

        status = POMODORO_STOPPED;

        alarmManager.cancel(pendingIntent);
    }

    public void start(int time, boolean debug) {
        if (status == POMODORO_PAUSED) return;
        if (status == POMODORO_RUNNING) return;

        String period_string;

        if (debug) {
            period = 1000;
        } else {
            period = 60000;
        }

        end = System.currentTimeMillis() + (time * period);

        Intent intent = new Intent(this, PomodoroReceiver.class);

        intent.putExtra("pause", false);

        pendingIntent = PendingIntent.getBroadcast(this, (int) Math.random(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, end, pendingIntent);

        if (debug) {
            period_string = "segundos";
        } else {
            period_string = "minutos";
        }

        Toast.makeText(this, "Pomodoro (tarea): " + time + " " + period_string, Toast.LENGTH_LONG).show();

        status = POMODORO_RUNNING;
    }

    public void startPause(int time, boolean debug) {
        if (status == POMODORO_RUNNING) return;

        String period_string;

        if (debug) {
            period = 1000;
        } else {
            period = 60000;
        }

        end = System.currentTimeMillis() + (time * period);

        Intent intent = new Intent(this, PomodoroReceiver.class);

        intent.putExtra("pause", true);

        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 234324243, intent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, end, pendingIntent);

        if (debug) {
            period_string = "segundos";
        } else {
            period_string = "minutos";
        }

        Toast.makeText(this, "Pomodoro (pausa): " + time + " " + period_string, Toast.LENGTH_LONG).show();

        status = POMODORO_PAUSED;
    }

    public void stopPause() {
        end = 0;

        status = POMODORO_STOPPED;

        alarmManager.cancel(pendingIntent);
    }

}
