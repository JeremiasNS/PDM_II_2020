package com.jeremiasneres.notificacaoprogressbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    private static final String CHANNEL_ID = "22";
    private int notificacaoId = 1;

    private Handler handler = new Handler();
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        criarCanalNotificacao();
    }


    public void gerar ( View view ) {

        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Picture Download")
                .setContentText("Download in Progress")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        final int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 0;
        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);

        notificationManagerCompat.notify(notificacaoId,builder.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                i = 0;
                while (i < 100){
                    i = i + 10;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            builder.setProgress(PROGRESS_MAX, i, false);
                            notificationManagerCompat.notify(notificacaoId, builder.build());
                            if (i>=PROGRESS_MAX){}
                            builder.setContentText("Download complete")
                                    .setProgress(0, 0, false);
                            notificationManagerCompat.notify(notificacaoId, builder.build());
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e ){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }).start();
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
