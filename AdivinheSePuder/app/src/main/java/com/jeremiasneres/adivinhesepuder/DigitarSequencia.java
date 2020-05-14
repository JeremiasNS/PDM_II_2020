package com.jeremiasneres.adivinhesepuder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

public class DigitarSequencia extends AppCompatActivity {

    private int[] numerosSorteados;
    private int[] numerosDigitados = new int[10];
    private int erros, acertos;

    private GridLayout gridLayout;
    private Button button;

    public DigitarSequencia() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitar_sequencia);

        button = findViewById(R.id.conferir);

        Intent i = getIntent();
        numerosSorteados = i.getIntArrayExtra("sequencia");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup group = (ViewGroup)findViewById(R.id.sequencia);
                int j = 0;
                for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                    View view = group.getChildAt(i);
                    if (view instanceof EditText) {
                        if (j < 10){
                            try {
                                numerosDigitados[j] = Integer.valueOf(((EditText)view).getText().toString());
                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Erro! Formato inválido.\n Preencha somente com números.", Toast.LENGTH_LONG).show();
                            }
                        }
                        j++;
                    }
                }
                pontuar();
                Intent k = new Intent(getApplicationContext(), ResultadoActivity.class);
                k.putExtra("erros", erros);
                k.putExtra("acertos", acertos);
                startActivity(k);
                finish();
            }
        });


    }

    private void pontuar() {
        for (int i = 0; i < numerosDigitados.length; i++) {
            if (numerosSorteados[i] == numerosDigitados[i]){
                acertos++;
            }else{
                erros++;
            }
        }
    }
}
