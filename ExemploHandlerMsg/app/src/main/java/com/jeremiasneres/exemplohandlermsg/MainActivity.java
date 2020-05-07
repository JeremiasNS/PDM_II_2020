package com.jeremiasneres.exemplohandlermsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private Handler handler;
    private Integer numberG;
    private static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.btnClicar);

    }

    public void clicar(View view) {
        if (view.getId() == R.id.btnClicar){
            Log.i(TAG, String.valueOf(Thread.currentThread()));
            handler = new MyHandler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, String.valueOf(Thread.currentThread()));
                    Random random = new Random();
                    final int num = random.nextInt(21);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    msg.arg1 = num;
                    handler.sendMessage(msg);
                }
            }).start();
        }
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            textView.setText(String.valueOf(msg.arg1));
        }
    }
}
