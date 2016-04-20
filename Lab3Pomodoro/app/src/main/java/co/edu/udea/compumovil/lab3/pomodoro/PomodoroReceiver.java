package co.edu.udea.compumovil.lab3.pomodoro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class PomodoroReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        Intent service = new Intent(context, PomodoroService.class);

        PomodoroService.PomodoroBinder binder = (PomodoroService.PomodoroBinder) this.peekService(context, service);

        PomodoroService pomodoroService = binder.getService();

        pomodoroService.stopPause();

        if (intent.getBooleanExtra("pause", true)) {
            Toast.makeText(context, "Pomodoro (pausa) terminada", Toast.LENGTH_SHORT).show();
        } else {
            if (sharedPref.getBoolean("vibrate", true)) {
                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

                vibrator.vibrate(1000);
            }

            if (sharedPref.getBoolean("ring", true)) {
                String alarm = sharedPref.getString("ringtone", "default ringtone");

                Uri alert = Uri.parse(alarm);

                Ringtone r = RingtoneManager.getRingtone(context, alert);

                r.play();
            }

            Toast.makeText(context, "Pomodoro (tarea) terminada", Toast.LENGTH_SHORT).show();

            pomodoroService.startPause(Integer.parseInt(sharedPref.getString("short_break", "5")), sharedPref.getBoolean("debug", false));
        }
    }

}
