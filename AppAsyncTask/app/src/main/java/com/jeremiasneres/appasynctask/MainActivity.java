package com.jeremiasneres.appasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ImageView imageView;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.btnCliclar);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterRecurso(editText.getText().toString());
            }
        });


    }

    private void obterRecurso(String url){
        Download d = new Download();
        d.execute(url);
    }

    private class Download extends AsyncTask<String, Void, Bitmap> {

        //Executado na thread principal
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this,
                    "AGUARDE", "download do recurso...");
        }

        //Vai para outra thread
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = Auxiliar.baixarImagem(strings[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            dialog.dismiss();
        }
    }
}
