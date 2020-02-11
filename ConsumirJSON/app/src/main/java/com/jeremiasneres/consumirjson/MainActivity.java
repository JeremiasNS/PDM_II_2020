package com.jeremiasneres.consumirjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private static String url = "https://jsonplaceholder.typicode.com/posts/";
    List<HashMap<String,String>> listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = new ArrayList<>();
        lv = findViewById(R.id.listView);

        ObterRecurso or = new ObterRecurso();
        or.execute();
    }

    private class ObterRecurso extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "download JSON...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... strings) {
            //System.out.println("cheguei");
            Auxiliar auxiliar = new Auxiliar();
            String jsonStr = auxiliar.consumir(url);
            return null;
        }


        /*
        @Override
        protected Void doInBackground(Void... strings) {

            if (jsonStr!=null){
                try {
                    //Pega a string e converte em um objeto
                    //System.out.println(jsonStr);
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    //Rotulo do objeto json da pagina
                    JSONArray jsonArray = jsonObject.getJSONArray("posts");

                    //Cada indice para um objeto
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject ob = jsonArray.getJSONObject(i);
                        String userId = ob.getString("userId");
                        String id = ob.getString("id");
                        String title = ob.getString("title");
                        String body = ob.getString("body");

                        HashMap<String,String> contato = new HashMap<>();
                        //pega o valor e joga na lista
                        contato.put("userId",userId);
                        contato.put("id",id);
                        contato.put("title",title);
                        contato.put("body",body);

                        listaContatos.add(contato);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }*/

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, listaContatos,
                    R.layout.list_item,
                    new String[]{"nome","email","endereco","movel"},
                    new int[]{R.id.userId,R.id.id,R.id.title,R.id.body}
            );
            lv.setAdapter(adapter);
        }
    }

}
