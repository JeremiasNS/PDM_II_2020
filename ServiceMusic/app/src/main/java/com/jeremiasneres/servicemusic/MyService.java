package com.jeremiasneres.servicemusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getStringExtra("key_action");

        if (action.equals("play")){
                if (mediaPlayer == null ){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.thedelongincident);
                    mediaPlayer.start();
                }else {
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.thedelongincident);
                    mediaPlayer.start();
                }
        }else if (action.equals("pause") && mediaPlayer != null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }else {
                mediaPlayer.start();
            }
        }
        //Toast.makeText(this,"Hello Boy",Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
