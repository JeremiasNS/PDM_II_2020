package com.jeremiasneres.cronogramaestudantil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TimePicker timePicker;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private int hora;
    private int minuto;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnCadastrar);
        timePicker = findViewById(R.id.tempo);
        spinner = (Spinner) findViewById(R.id.materia);
        editText = findViewById(R.id.edtTxtMail);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.materias_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        /*createNotificationChannel("1");
        createNotificationChannel("2");*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    hora = timePicker.getHour();
                    minuto = timePicker.getMinute();
                disparar();
            }
        });

    }

    private void disparar (){
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent i = new Intent(this, AlarmReceiver.class);
        i.putExtra("key_materia", spinner.getSelectedItem().toString());
        i.putExtra("key_mail", editText.getText().toString());
        editText.setText("");

        final int id = (int) System.currentTimeMillis();
        pendingIntent = PendingIntent.getBroadcast(this, id, i, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minuto);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()
                , pendingIntent);

    }

/*    private void createNotificationChannel(String CHANNEL_ID) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }*/
}
