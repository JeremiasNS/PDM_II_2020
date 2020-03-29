package com.jeremiasneres.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;

public class MeuRecebedorTransmissao extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String texto = intent.getStringExtra("texto");
        Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();

        /*NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, "canal1");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("My receiver");
        builder.setContentText("Bem-vindo " + texto);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, builder.build());*/

    }
}
