package com.example.adedonha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String letras[] = {"A","E","I","O","U"};
    TextView letrasTxt;
    Button nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letrasTxt = findViewById(R.id.letraRandon);
        nextPage = findViewById(R.id.btnAvancar);

        //Embaralhando as letras atráves da posição
        Random random = new Random();
        int res = random.nextInt(letras.length);
        letrasTxt.setText(letras[res]);

        //Toast.makeText(this, letras[res], Toast.LENGTH_LONG).show();

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Envia para a FormActivity
                Intent i = new Intent(getApplicationContext(),FormActivity.class);

                i.putExtra("key_letra", letrasTxt.getText().toString());
                startActivity(i);
                finish();
            }
        });

    }
}
