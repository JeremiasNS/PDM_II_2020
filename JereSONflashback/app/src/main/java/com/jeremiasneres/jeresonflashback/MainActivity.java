package com.jeremiasneres.jeresonflashback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonConsumir, buttonGerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConsumir = findViewById(R.id.buttonConsumir);
        buttonGerar = findViewById(R.id.buttonGerar);

        buttonConsumir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> dados = consumirJson();
                if (dados != null || dados.isEmpty()==false){
                    Toast.makeText(getApplicationContext(), "Dados consumidos!" + dados.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> dados = consumirJson();
                if (dados != null || dados.isEmpty()==false){
                    Toast.makeText(getApplicationContext(), "Dados consumidos!" + dados.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public String gerarJSON(){
        List<User> lista = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();

        lista.add(new User("Nome 1", "Design 1", "Local 1"));
        lista.add(new User("Nome 2", "Design 2", "Local 2"));
        lista.add(new User("Nome 3", "Design 3", "Local 3"));

        for (int i = 0;i<lista.size();i++){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nome", lista.get(i).getName());
                jsonObject.put("designation", lista.get(i).getDesignation());
                jsonObject.put("location", lista.get(i).getLocation());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }

    public String getListData(){
        String jsonStr = "{ \"users\" :[" +
                "{\"name\":\"Suresh Dasari\",\"designation\":\"Team Leader\",\"location\":\"Hyderabad\"}" +
                ",{\"name\":\"Rohini Alavala\",\"designation\":\"Agricultural Officer\",\"location\":\"Guntur\"}" +
                ",{\"name\":\"Trishika Dasari\",\"designation\":\"Charted Accountant\",\"location\":\"Guntur\"}] }";
        return jsonStr;
    }

    private List<User> consumirJson(){
        String jsonStr = getListData();
        List<User> dados = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObject.getJSONArray("users");

            for(int i = 0;i<jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                User u = new User();
                u.setName(object.getString("name"));
                u.setDesignation(object.getString("designation"));
                u.setLocation(object.getString("location"));
                dados.add(u);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dados;
    }

}
