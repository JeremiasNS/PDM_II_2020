package com.jeremiasneres.oscarjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FilmeActivity extends AppCompatActivity {

    private ListView listaXML;

    private ArrayList<HashMap<String, String>> listaContatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        listaXML = findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getSharedPreferences("JSON_FILMES", 0);
        String resultSharedPreferences = sharedPreferences.getString("JSON_FILMES", "");

        formatarLista(resultSharedPreferences);
        enviarLista();

    }

    protected void formatarLista(String jsonStr) {

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
    }

    public void enviarLista() {
        ListAdapter adapter = new SimpleAdapter(
                FilmeActivity.this, listaContatos,
                R.layout.list_filme,
                new String[]{"nome", "direcao", "categoria"},
                new int[]{R.id.nome, R.id.direcao, R.id.categoria}
        );
        listaXML.setAdapter(adapter);
    }

}
