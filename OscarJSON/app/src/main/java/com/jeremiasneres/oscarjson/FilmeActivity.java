package com.jeremiasneres.oscarjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.Touch;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilmeActivity extends AppCompatActivity {

    TextView filme;
    private ListView lv;
    private String result;

    ArrayList<HashMap<String, String>> listaContatos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        lv = findViewById(R.id.listView);
        filme = findViewById(R.id.filmesView);

        SharedPreferences sharedPreferences = getSharedPreferences("JSON_FILMES", 0);
        result = sharedPreferences.getString("JSON_FILMES", "");
        //filme.setText("Resultado --> " + result);

       //formatar(result);
        //algo();

        ObterRecurso or = new ObterRecurso();
        or.execute();

    }


    private class ObterRecurso extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "download JSON...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... strings) {
            String jsonStr = result;

            if (jsonStr != null) {
                try {
                    JSONArray contacts = new JSONArray(jsonStr);
                    System.out.println(contacts.get(0).toString());

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject ob = contacts.getJSONObject(i);
                        String nome = ob.getString("nome");
                        String direcao = ob.getString("direcao");
                        String categoria = ob.getString("categoria");

                        HashMap<String, String> contato = new HashMap<>();
                        //pega o valor e joga na lista
                        contato.put("nome", nome);
                        contato.put("direcao", direcao);
                        contato.put("categoria", categoria);

                        listaContatos.add(contato);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ListAdapter adapter = new SimpleAdapter(
                    FilmeActivity.this, listaContatos,
                    R.layout.list_filme,
                    new String[]{"nome", "direcao", "categoria"},
                    new int[]{R.id.nome, R.id.direcao, R.id.categoria}
            );
            lv.setAdapter(adapter);
        }
    }
    /*
    protected void formatar(String jsonStr) {
        System.out.println(jsonStr);

        try {
            JSONArray contacts = new JSONArray(jsonStr);

            JSONObject ob = contacts.getJSONObject(0);

            String nome = ob.getString("nome");
            String direcao = ob.getString("direcao");
            String categoria = ob.getString("categoria");


            HashMap<String, String> contato = new HashMap<>();
            //pega o valor e joga na lista
            contato.put("nome", nome);
            contato.put("direcao", direcao);
            contato.put("categoria", categoria);

            //Toast.makeText(getApplicationContext(), "nome= " + contato.get("nome"), Toast.LENGTH_LONG ).show();

            listaContatos.add(contato);

            //System.out.println(contacts.get(0).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*if (jsonStr != null) {
            try {
                JSONArray contacts = new JSONArray(jsonStr);
                System.out.println(contacts.get(0).toString());

                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject ob = contacts.getJSONObject(i);
                    String nome = ob.getString("nome");
                    String direcao = ob.getString("direcao");
                    String categoria = ob.getString("categoria");

                    HashMap<String, String> contato = new HashMap<>();
                    //pega o valor e joga na lista
                    contato.put("nome", nome);
                    contato.put("direcao", direcao);
                    contato.put("categoria", categoria);

                    listaContatos.add(contato);
                    //System.out.println("AKI-------------------");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
    }

    public void algo() {
        ListAdapter adapter = new SimpleAdapter(
                FilmeActivity.this, listaContatos,
                R.layout.list_filme,
                new String[]{"userId", "id", "title", "body"},
                new int[]{R.id.nome, R.id.direcao, R.id.categoria}
        );
        lv.setAdapter(adapter);
    }*/

}
