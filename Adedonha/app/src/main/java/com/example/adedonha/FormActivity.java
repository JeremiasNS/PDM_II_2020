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
                dadosValidados = validarFormulario();

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

    private boolean validarFormulario(){

        boolean retorno = false;
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

        if(!!TextUtils.isEmpty(txtPaises.getText().toString())){
                retorno = true;
        }else{
            txtPaises.setError("*");
            txtPaises.requestFocus();
        }

        return retorno;
    }

    /*private boolean verificarPrimeiraLetra(String palavra){

        System.out.println(palavra);
        boolean retorno = false;
        System.out.println(letraEscolhida.toLowerCase());

        int i = palavra.indexOf(letraEscolhida.toLowerCase());
        System.out.println(i);

        if(i != 0){
            retorno = true;
        }

        return retorno;

    }*/

}
