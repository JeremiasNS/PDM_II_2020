package com.jeremiasneres.app21demaio2020_itents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private RadioButton btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btnWhattsApp);
        btn2 = findViewById(R.id.btnEmail);
        btn3 = findViewById(R.id.btnSMS);
    }

    public void clicar(View view) {
        Intent sendIntent = null;

        if (view.getId() == R.id.btnWhattsApp) {
            sendIntent = new Intent(getApplicationContext(),FormActicity1.class);
            sendIntent.putExtra("ACTION","whatts");
            startActivity(sendIntent);
            finish();

        } else if (view.getId() == R.id.btnEmail) {
            sendIntent = new Intent(getApplicationContext(),FormEmailActivity.class);
            startActivity(sendIntent);
            finish();
        } else if (view.getId() == R.id.btnSMS) {
            sendIntent = new Intent(getApplicationContext(),FormActicity1.class);
            sendIntent.putExtra("ACTION","sms");
            startActivity(sendIntent);
            finish();
        }
    }
}
