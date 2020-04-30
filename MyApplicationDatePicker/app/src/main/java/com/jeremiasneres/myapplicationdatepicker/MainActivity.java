package com.jeremiasneres.myapplicationdatepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        button = findViewById(R.id.buttomD);


    }

    public void clicar(View view) {
        Toast.makeText(getApplicationContext(), obterData(), Toast.LENGTH_LONG).show();
    }

    private String obterData() {
        String data = "";
        int dia = datePicker.getDayOfMonth();
        int mes = datePicker.getMonth();
        int ano = datePicker.getYear();
        data = dia + "/" + mes + "/" + ano;
        return data;

    }
}
