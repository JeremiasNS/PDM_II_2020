package com.jeremiasneres.adivinhesepuder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private Handler handler;
    private Integer numberG;
    private int[] numerosSorteados  = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.numberTxtView);
        button = findViewById(R.id.buttonClick);
    }

    public void clicar(final View view) {

        if (view.getId() == button.getId()) {
            handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    button.setClickable(false);
                    gerarValores();
                    for (Integer a : numerosSorteados) {
                        final Integer b = a;
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(String.valueOf(b));
                            }
                        });
                    }
                    Intent i = new Intent(getApplicationContext(), DigitarSequencia.class);
                    i.putExtra("sequencia",numerosSorteados);
                    startActivity(i);
                    finish();
                }
            }).start();
        }


    }

    private void gerarValores() {
        Random random = new Random();
        int c;
        for(int i = 0; i < numerosSorteados.length; i++) {
            numberG = random.nextInt(21);
            if (i == 0) {
                numerosSorteados[i] = numberG;
            } else {
                c = 0;
                while (c < i) {
                    if (numerosSorteados[c] == numberG) {
                        numberG = random.nextInt(21);
                        c = 0;
                    } else {
                        c++;
                    }
                }
                numerosSorteados[i] = numberG;
            }
            System.out.println("num " + i + " " + numberG);
        }
    }
}
