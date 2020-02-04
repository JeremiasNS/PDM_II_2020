package com.example.adedonha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String letras[] = {"A","E","I","O","U"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Embaralhando as letras atráves da posição
        Toast.makeText(this, letras[(int)Math.random()], Toast.LENGTH_LONG).show();



    }
}
