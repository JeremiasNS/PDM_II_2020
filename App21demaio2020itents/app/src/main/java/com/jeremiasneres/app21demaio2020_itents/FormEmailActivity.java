package com.jeremiasneres.app21demaio2020_itents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class FormEmailActivity extends AppCompatActivity {

    TextInputEditText destinatario;
    TextInputEditText titulo;
    TextInputEditText conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_email);

        destinatario = findViewById(R.id.etxtDestinatario);
        titulo = findViewById(R.id.etxtTitulo);
        conteudo = findViewById(R.id.etxtConteudo);
    }

    public void send(View view) {
        String address = destinatario.getText().toString();
        String title = titulo.getText().toString();
        String message = conteudo.getText().toString();
        Intent intent = new Intent (Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        //intent.setPackage("com.google.android.gm");
        startActivity(intent);
        /*Uri uri = Uri.parse("mailto:" + address);
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(sendIntent, ""));*/
    }
}
