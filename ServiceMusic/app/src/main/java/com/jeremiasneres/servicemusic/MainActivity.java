package com.jeremiasneres.servicemusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlay, btnPause, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.play);
        btnPause = findViewById(R.id.pause);
        btnStop = findViewById(R.id.stop);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MyService.class);
        if (v.getId() == btnPlay.getId()) {
            i.putExtra("key_action", "play");
            startService(i);
        } else if (v.getId() == btnPause.getId()){
            i.putExtra("key_action", "pause");
            startService(i);
        } else if (v.getId() == btnStop.getId()){
            stopService(i);
        }

    }
}
