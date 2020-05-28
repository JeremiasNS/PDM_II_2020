package com.jeremiasneres.app21demaio2020_itents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FormActicity1 extends AppCompatActivity {

    private String action;

    TextInputEditText edtxtNumero;
    TextInputEditText edtxtMensagem;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        edtxtNumero = findViewById(R.id.etxtNumero);
        edtxtMensagem = findViewById(R.id.etxtMensagem);
        textInputLayout = findViewById(R.id.numero);

        Intent i = getIntent();
        action = i.getStringExtra("ACTION");

        if (action.equals("whatts")) {
            textInputLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void enviar(View view) {
        if (action.equals("whatts")){
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, edtxtMensagem.getText().toString());
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent.createChooser(sendIntent,""));
            finish();
        }else if (action.equals("sms")){
            Intent in;
            Uri uri = Uri.parse("smsto:" + edtxtNumero.getText().toString());
            in = new Intent(Intent.ACTION_SENDTO, uri);
            in.putExtra(Intent.EXTRA_TEXT, edtxtMensagem.getText().toString());
            startActivity(in);
            finish();
        }
    }
}
