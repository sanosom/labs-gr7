package organizate.compumovil.udea.edu.co.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.activities.MainActivity;

/**
 * Created by santiago on 6/9/16.
 */
public class Countdown extends Service {

    private static final String TAG = "organizate.countdown";

    CountDownTimer timerTask;
    LocalBroadcastManager broadcaster;

    long actual;

    public Countdown() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
        Log.d(TAG, "Servicio creado...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado...");

        Bundle bundle = intent.getExtras();
        long tiempo = bundle.getLong("tiempo");

        timerTask = new CountDownTimer(tiempo,1000) {
            Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intentMain, 0);

            Notification notificacion = new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.drawable.ic_alarm)
                    .setProgress(100, 10, false)
                    .addAction(R.drawable.ic_alarm_off, "Terminar", null)
                    .addAction(R.drawable.ic_alarm_add, "Agregar 10 minutos", null)
                    .setContentIntent(pIntent)
                    .setContentTitle("Actividad 2")
                    .setContentText("Universidad de Antioquia - 27 minutos restantes")
                    .build();

            @Override
            public void onTick(long millisUntilFinished) {
                notificacion = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_alarm)
                        .setProgress(100, 10, false)
                        .addAction(R.drawable.ic_alarm_off, "Terminar", null)
                        .addAction(R.drawable.ic_alarm_add, "Agregar 10 minutos", null)
                        .setContentIntent(pIntent)
                        .setContentTitle("Actividad 2")
                        .setContentText("Universidad de Antioquia - 27 minutos restantes")
                        .build();

                actual = millisUntilFinished;
                startForeground(1, notificacion);

                Intent intent = new Intent("tiempo");
                intent.putExtra("tiempo", millisUntilFinished);

                broadcaster.sendBroadcast(intent);
            }

            @Override
            public void onFinish() {
                Intent intentFin = new Intent("tiempo");
                intentFin.putExtra("tiempo", 0);

                broadcaster.sendBroadcast(intentFin);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("tiempo", 0);

                Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                Notification noti = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_alarm)
                        .setProgress(100, 10, false)
                        .setContentIntent(pIntent)
                        .setContentTitle("Actividad 2")
                        .setContentText("Universidad de Antioquia - 27 minutos restantes")
                        .build();

                noti.sound = uri;
                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                //Se esconde la notificación tras ser seleccionada
                noti.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(0, noti);

                stopForeground(true);
            }
        };

        timerTask.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        timerTask.cancel();

        Log.d(TAG, "Servicio destruido...");
    }

}
