package com.jeremiasneres.myapplicationtimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.myTimePicker);
        timePicker.setIs24HourView(true);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textViewTime);

    }

    public void clicar(View view) {
        textView.setText(obterTempo());
    }

    private String obterTempo() {
        int hora = 0, minuto = 0;
        String time = "";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hora = timePicker.getHour();
            minuto = timePicker.getMinute();
        }else {
            hora = timePicker.getCurrentHour();
            minuto = timePicker.getCurrentMinute();
        }
        time = hora + ":" + minuto;
        return time;
    }
}
