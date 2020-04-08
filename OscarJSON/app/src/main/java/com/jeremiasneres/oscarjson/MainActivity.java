package com.jeremiasneres.oscarjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nome;
    EditText direcao;
    EditText categoria;
    Button cadastrar;
    Button enviar;

    private List<Filme> dados = new ArrayList<>();
    private String strJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviar = findViewById(R.id.enviar);
        cadastrar = findViewById(R.id.cadastrar);
        nome = findViewById(R.id.etxt_nome);
        direcao = findViewById(R.id.etxt_direcao);
        categoria = findViewById(R.id.etxt_categoria);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar(nome.getText().toString(), direcao.getText().toString(), categoria.getText().toString());
                Toast.makeText(getApplicationContext(), nome.getText().toString() + "\nCadastrado com sucesso!", Toast.LENGTH_LONG).show();
                nome.setText("");
                direcao.setText("");
                categoria.setText("");
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();

                //Enviando para a FilmeActivity
                Intent i = new Intent(getApplicationContext(), FilmeActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void cadastrar(String nome, String direcao, String categoria) {

        dados.add(new Filme(nome, direcao, categoria));

        //Utilizando biblioteca gson na conversão de uma Lista para formato json
        Gson gson = new Gson();
        strJson = gson.toJson(dados);
    }

    public void salvar() {
        //Utilizando o shared preferences para armazenar dados dos filmes
        SharedPreferences sharedPreferences = getSharedPreferences("JSON_FILMES", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("JSON_FILMES", strJson);
        editor.apply();
    }


}
