package com.example.adedonha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;

public class ResultadoActivity extends AppCompatActivity {


    String letraEscolhida;

    String fruta[] = {"ameixa", "engkala", "ingá", "oiti", "uva"};
    String pais[] = {"África", "Espanha", "Índia", "Omã", "Uruguai"};
    String animal[] = {"avestruz", "ema", "iguana", "ovelha", "urso"};

    TextView txtAnimalUser;
    TextView txtPaisUser;
    TextView txtFrutaUser;
    TextView txtAnimalAndroid;
    TextView txtPaisAndroid;
    TextView txtFrutaAndroid;
    TextView txtPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        txtPontuacao = findViewById(R.id.total);

        txtAnimalUser = findViewById(R.id.animalUser);
        txtFrutaUser = findViewById(R.id.frutaUser);
        txtPaisUser = findViewById(R.id.paisUser);

        txtPaisAndroid = findViewById(R.id.paisAndroid);
        txtAnimalAndroid = findViewById(R.id.animalAndroid);
        txtFrutaAndroid = findViewById(R.id.frutaAndroid);

        //Recebe os valores
        Intent receber = getIntent();
        letraEscolhida = receber.getStringExtra("key_letra");

        txtFrutaUser.setText(receber.getStringExtra("key_fruta"));
        txtPaisUser.setText(receber.getStringExtra("key_pais"));
        txtAnimalUser.setText(receber.getStringExtra("key_animal"));

        txtFrutaAndroid.setText(verificarPalavras(fruta));
        txtPaisAndroid.setText(verificarPalavras(pais));
        txtAnimalAndroid.setText(verificarPalavras(animal));

        pontuar();


    }


    private String verificarPalavras(String string[]){
        String retorno = "";

        for( String palavra : string)
        {

            int i = removerAcentos(palavra.toLowerCase()).indexOf(letraEscolhida.toLowerCase());
            if(i == 0 ){
                retorno = palavra;
            }
        }
        return retorno;
    }

    private String removerAcentos(String valorAcentuado){
        return Normalizer
                .normalize(valorAcentuado, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    private void pontuar(){
        int pontos = 0;

        System.out.println("cheguei");

        if(removerAcentos(txtAnimalUser.getText().toString()).toLowerCase().equals(
                removerAcentos(txtAnimalAndroid.getText().toString()).toLowerCase())){
            pontos += 33;
        }
        if(removerAcentos(txtFrutaUser.getText().toString()).toLowerCase().equals(
                removerAcentos(txtFrutaAndroid.getText().toString()).toLowerCase())){
            pontos += 33;
        }
        if(removerAcentos(txtPaisUser.getText().toString()).toLowerCase().equals(
                removerAcentos(txtPaisAndroid.getText().toString()).toLowerCase())){
            pontos += 33;
        }

        txtPontuacao.setText(String.valueOf(pontos));
    }





}
