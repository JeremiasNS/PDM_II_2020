package com.jeremiasneres.cronogramaestudantil;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String email = intent.getStringExtra("key_mail");
        String materia = intent.getStringExtra("key_materia");
        Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:")); // only email apps should handle this
            i.putExtra(Intent.EXTRA_EMAIL, email);
            i.putExtra(Intent.EXTRA_CC, email);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        int idStringPort = context.getResources().getIdentifier("questoes_portugues",
                "string", context.getPackageName());
        int idStringMat = context.getResources().getIdentifier("questoes_matematica",
                "string", context.getPackageName());

        if( materia.equals("Português")){
            i.putExtra(Intent.EXTRA_SUBJECT, "Hora de estudar Português!");
            i.putExtra(Intent.EXTRA_TEXT,  context.getResources().getString(idStringPort));
        }else {
            i.putExtra(Intent.EXTRA_SUBJECT, "Hora de estudar Matemática!");
            i.putExtra(Intent.EXTRA_TEXT,  context.getResources().getString(idStringMat));
        }
                context.startActivity(i);


        /* Create an explicit intent for an Activity in your app
        Intent j;
        String channel;
        int id;
        if( materia.equals("Português")){
            j = new Intent(context, Questoes1Activity.class);
            id = 1;
            channel = "1";
        }else {
            j = new Intent(context, Questoes2Activity.class);
            id = 2;
            channel = "2";
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, j, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle("Hora de estudar " + materia)
                .setContentText("Clique para ser redirecionado!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(id, builder.build());

        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone toque = RingtoneManager.getRingtone(context, som);
        toque.play();
        */

    }
}
