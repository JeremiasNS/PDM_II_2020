package com.jeremiasneres.myapplicationtimepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TimePickerDialog timePickerDialog;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.myTextView);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = "";
        time = hourOfDay + ":" + minute;
        textView.setText(time);
    }

    public void clicar(View view) {
        if (view.getId() == R.id.myTextView) {
            Calendar calendar = Calendar.getInstance();
            int hora = calendar.get(Calendar.HOUR);
            int minuto = calendar.get(Calendar.MINUTE);

            timePickerDialog = new TimePickerDialog(MainActivity.this, this,
                    hora, minuto, true);
            timePickerDialog.show();
        }
    }
}
