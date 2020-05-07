package com.jeremiasneres.exemplohandlerpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
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
        textView = findViewById(R.id.textViewNumero);
        button = findViewById(R.id.btn);
    }

    public void clicar(View view) {
        if (view.getId() == R.id.btn) {
            Log.i("TAG", String.valueOf(Thread.currentThread()));
            //Cria o objeto handler
            handler = new Handler();
            //Cria uma thread
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //Thread gera um número aleatório emtre 0 e 20
                    Log.i("TAG", String.valueOf(Thread.currentThread()));
                    Random random = new Random();
                    numberG = random.nextInt(21);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Utilizando o handler através do metódo post
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("TAG", String.valueOf(Thread.currentThread()));
                            textView.setText(String.valueOf(numberG));
                        }
                    });
                }
            }).start();
        }
    }
}
