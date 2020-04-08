package com.jeremiasneres.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MeuRecebedorTransmissao extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String texto = intent.getStringExtra("texto");
        /*Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();*/



        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, "1");
        builder.setSmallIcon(R.drawable.ic_warning_black_24dp);
        builder.setContentTitle("My receiver");
        builder.setContentText("Bem-vindo " + texto);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0, builder.build());

    }



}
