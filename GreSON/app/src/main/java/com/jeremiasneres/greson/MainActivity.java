package com.jeremiasneres.greson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonEnviar, buttonConsumir;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEnviar = findViewById(R.id.button_enviar);
        buttonConsumir = findViewById(R.id.button_consumir);
        editText = findViewById(R.id.editText);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(gerarJSON());
            }
        });

        buttonConsumir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumirJSON();
            }
        });

    }

    private String gerarJSON(){
        List<Pessoa> dados = new ArrayList<>();
        dados.add(new Pessoa("GOKU",100,80));
        dados.add(new Pessoa("Hyoga",20,70));
        dados.add(new Pessoa("Madimbu",1000,1000));

        Gson gson = new Gson();
        String strJson = gson.toJson(dados);
        return strJson;
    }

    private void consumirJSON(){
        String resultado = "";
        Gson gson = new Gson();
        //Object porque é uma lista de pessoas, se fosse uma colocarias Pessoa.class
        Object o = gson.fromJson(editText.getText().toString(),Object.class);
        //Transforma o vetor em uma matriz[]
        List lista = Arrays.asList(o);
        //Toast.makeText(getApplicationContext(), lista.toString(),Toast.LENGTH_LONG).show();
        for (int i=0;i<lista.size();i++){
            List<Pessoa> dados = (List<Pessoa>) lista.get(i);
            for (int z=0;z<dados.size();z++){
                resultado = resultado+dados.get(z)+"\n";
            }
        }
        Toast.makeText(getApplicationContext(),resultado, Toast.LENGTH_SHORT).show();
    }
}
