package com.jeremiasneres.exemploalarmmanager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "HORA DE ESTUDAR", Toast.LENGTH_LONG).show();

        // Create an explicit intent for an Activity in your app
        Intent j = new Intent(context, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, j, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")
                .setSmallIcon(R.drawable.ic_notifications_active_red)
                .setContentTitle("Olá")
                .setContentText("Clique para ser redirecionado!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());


    }
}
