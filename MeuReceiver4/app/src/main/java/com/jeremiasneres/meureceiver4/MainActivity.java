package com.jeremiasneres.meureceiver4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyReceiver mr = new MyReceiver();
        IntentFilter i = new IntentFilter(Intent.ACTION_SCREEN_ON);
        i.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(mr, i);
    }
}
