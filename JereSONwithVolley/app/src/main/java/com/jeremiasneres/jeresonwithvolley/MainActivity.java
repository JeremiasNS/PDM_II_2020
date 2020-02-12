package com.jeremiasneres.jeresonwithvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MYTAG";
    RequestQueue QUEUE;
    String URLHTTP = "https://jsonplaceholder.typicode.com/posts/";
    ArrayList<HashMap<String,String>> listaContatos;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = new ArrayList<>();
        lv = findViewById(R.id.listView);
        QUEUE = Volley.newRequestQueue(this);
        httpGET(URLHTTP);

    }

    public void httpGET(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   // Log.d(TAG,"RESPOSTA DO SERVIDOR: "+ response);
                    if (response!=null){
                        try {
                            JSONArray contacts = new JSONArray(response);

                            //Cada indice para um objeto
                            for(int i=0;i<contacts.length();i++){
                                JSONObject ob = contacts.getJSONObject(i);
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

                            ListAdapter adapter = new SimpleAdapter(
                                    MainActivity.this, listaContatos,
                                    R.layout.list_item,
                                    new String[]{"userId","id","title","body"},
                                    new int[]{R.id.userId,R.id.id,R.id.title,R.id.body}
                            );
                            lv.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                "utf-8");
                        Log.d(TAG, "ERRO "+responseBody);
                    }catch (UnsupportedEncodingException errorr){
                        Log.d(TAG,errorr.toString());
                    }
                }
            }
        );
        QUEUE.add(stringRequest);
    }
}
