package com.jeremiasneres.consumirjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private static String url = "https://jsonplaceholder.typicode.com/posts/";
    List<HashMap<String,String>> listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = new ArrayList<>();
        listView = findViewById(R.id.listView);

        ObterRecurso or = new ObterRecurso();
    }

    private class ObterRecurso extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "download JSON...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... Strings) {
            Auxiliar auxiliar = new Auxiliar();
            String jsonStr = auxiliar.consumir(url);
            if (jsonStr!=null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    //JSONArray jsonArray = jsonObject.getJSONArray();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}
