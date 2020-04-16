package com.jeremiasneres.exemplonotificacaotoque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "22";
    private Button button;
    private int notificatioId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);

        criarCanalNotificacao();
    }

    public void gerar ( View view ) {
        Intent i = new Intent(this, Main2Activity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        Bitmap myBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_hospital);

        Notification builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Titulo")
                .setContentText("Counteúdo")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .setLargeIcon(myBitmap)
                /*.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(myBitmap)
                .bigLargeIcon(null))
                .setAutoCancel(true)*/
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Lorem Ipsum is simply dummy text of the printing and \n" +
                        "typesetting industry. Lorem Ipsum has been the industry's\n" +
                        " standard dummy text ever since the 1500s, when an unknown\n" +
                        " printer took a galley of type and scrambled it to make a\n" +
                        " type specimen book"))
                .build();

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(notificatioId, builder);

    }

    private void criarCanalNotificacao() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
