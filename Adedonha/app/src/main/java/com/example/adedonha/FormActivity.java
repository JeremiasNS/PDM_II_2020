package com.example.adedonha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    TextView txtDescricao;
    EditText txtFruta;
    EditText txtPaises;
    EditText txtAnimal;
    Button btnEnviarForm;

    String letraEscolhida;
    boolean dadosValidados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        txtDescricao = findViewById(R.id.descricao);
        btnEnviarForm = findViewById(R.id.butaoEnviarForm);

        txtFruta = findViewById(R.id.fruta);
        txtPaises = findViewById(R.id.pais);
        txtAnimal = findViewById(R.id.animal);

        //Recebe a letra e atribui ao text view
        Intent receber = getIntent();
        letraEscolhida = receber.getStringExtra("key_letra");
        txtDescricao.setText("Começãndo com\n a letra " + letraEscolhida + " digite o\n nome de um(a):");

        //Envia os dados preechidos no formulário
        btnEnviarForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dadosValidados = validarCampos();

                if(dadosValidados) {
                    Intent i = new Intent(getApplicationContext(), ResultadoActivity.class);
                    i.putExtra("key_fruta", txtFruta.getText().toString());
                    i.putExtra("key_pais", txtPaises.getText().toString());
                    i.putExtra("key_animal", txtAnimal.getText().toString());
                    i.putExtra("key_letra", letraEscolhida);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplication(), "Preencha os campos", Toast.LENGTH_LONG);
                }
            }
        });


    }

    /*private boolean validarFormulario(){

        boolean retorno = false;

        System.out.println(!TextUtils.isEmpty(txtFruta.getText().toString()));
        System.out.println("primeiraletra=> "+verificarPrimeiraLetra(txtFruta.getText().toString()));
        if(!TextUtils.isEmpty(txtFruta.getText().toString())){
            retorno = true;
        }else{
            txtFruta.setError("*");
            txtFruta.requestFocus();
        }

        if(!TextUtils.isEmpty(txtAnimal.getText().toString())){
                retorno = true;
        }else{
            txtAnimal.setError("*");
            txtAnimal.requestFocus();
        }

        if(!TextUtils.isEmpty(txtPaises.getText().toString())){
                retorno = true;
        }else{
            txtPaises.setError("*");
            txtPaises.requestFocus();
        }

        return retorno;
    }*/

    private boolean validarCampos(){
        boolean retorno = false;

        String fruta = txtFruta.getText().toString();
        String animal = txtAnimal.getText().toString();
        String pais = txtPaises.getText().toString();

        if(isCampoVazio(fruta) || isPrimeiraLetra(fruta)){
            System.out.println("---->Fruta-Vazio="+isCampoVazio(fruta)+ " Letra1=" + isPrimeiraLetra(fruta));
            txtFruta.requestFocus();
        }else if (isCampoVazio(pais) || isPrimeiraLetra(pais)){
            System.out.println("pais-Vazio="+isCampoVazio(pais)+ " Letra1=" + isPrimeiraLetra(pais));
            txtPaises.requestFocus();
        }else if (isCampoVazio(animal) || isPrimeiraLetra(animal)){
            System.out.println("Animal-Vazio="+isCampoVazio(animal)+ " Letra1=" + isPrimeiraLetra(animal));
            txtAnimal.requestFocus();
        }else{
            retorno = true;
        }
            //

            System.out.println("Fruta-Vazio="+isCampoVazio(fruta)+ " Letra1=" + isPrimeiraLetra(fruta));

        /*if(cont == 3){
            retorno = true;
        }*/
        System.out.println(retorno);
        return retorno;
    }

    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    private boolean isPrimeiraLetra(String palavra){
        System.out.println("Antes" + palavra);
        boolean retorno = true;
        palavra = palavra.toLowerCase();
        System.out.println("depois" + palavra);

        System.out.println(letraEscolhida.toLowerCase());
        int i = palavra.indexOf(letraEscolhida.toLowerCase());
        System.out.println("indice "+i);

        if(i == 0){
            retorno = false;
        }

        return retorno;

    }

}
