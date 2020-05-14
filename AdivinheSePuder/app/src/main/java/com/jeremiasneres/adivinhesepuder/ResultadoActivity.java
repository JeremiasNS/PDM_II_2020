package com.jeremiasneres.adivinhesepuder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView acertos;
    private TextView erros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        acertos = findViewById(R.id.acertos);
        erros = findViewById(R.id.erros);

        Intent i = getIntent();
        acertos.setText(String.valueOf(i.getIntExtra("acertos",0)));
        erros.setText(String.valueOf(i.getIntExtra("erros", 0)));

    }
}
