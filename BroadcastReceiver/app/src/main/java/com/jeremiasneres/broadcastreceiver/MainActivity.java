package com.jeremiasneres.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    private static final String TIPO_INTENT = "com.jeremiasneres.broadcastreceiver.MY_INTENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

    }

    public void dispararEvento(View view) {
        Intent i = new Intent();
        i.putExtra("texto", "Jeremias");
        i.setAction(TIPO_INTENT);
        sendBroadcast(i);
    }
}
