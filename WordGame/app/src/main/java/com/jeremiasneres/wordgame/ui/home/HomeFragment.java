package com.jeremiasneres.wordgame.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.jeremiasneres.wordgame.Auxiliar;
import com.jeremiasneres.wordgame.Palavra;
import com.jeremiasneres.wordgame.R;
import com.jeremiasneres.wordgame.ui.gallery.GalleryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private int notificationId = 1;

    String letras[] = {"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    private static String url = "https://api.dicionario-aberto.net/random";

    private String palavra;
    private TextView[] localPalavras = new TextView[palavra.length()];;
    int linhaInicio;
    int colunaInicio;

    //Criando uma matriz de inteiros contendo os ids dos TextView
    private static final int[][] idTextViews = {
            {R.id.txtl0c0, R.id.txtl0c1, R.id.txtl0c2, R.id.txtl0c3, R.id.txtl0c4, R.id.txtl0c5,
                    R.id.txtl0c6, R.id.txtl0c7, R.id.txtl0c8, R.id.txtl0c9, R.id.txtl0c10, R.id.txtl0c11},
            {R.id.txtl1c0, R.id.txtl1c1, R.id.txtl1c2, R.id.txtl1c3, R.id.txtl1c4, R.id.txtl1c5,
                    R.id.txtl1c6, R.id.txtl1c7, R.id.txtl1c8, R.id.txtl1c9, R.id.txtl1c10, R.id.txtl1c11},
            {R.id.txtl2c0, R.id.txtl2c1, R.id.txtl2c2, R.id.txtl2c3, R.id.txtl2c4, R.id.txtl2c5,
                    R.id.txtl2c6, R.id.txtl2c7, R.id.txtl2c8, R.id.txtl2c9, R.id.txtl2c10, R.id.txtl2c11},
            {R.id.txtl3c0, R.id.txtl3c1, R.id.txtl3c2, R.id.txtl3c3, R.id.txtl3c4, R.id.txtl3c5,
                    R.id.txtl3c6, R.id.txtl3c7, R.id.txtl3c8, R.id.txtl3c9, R.id.txtl3c10, R.id.txtl3c11},
            {R.id.txtl4c0, R.id.txtl4c1, R.id.txtl4c2, R.id.txtl4c3, R.id.txtl4c4, R.id.txtl4c5,
                    R.id.txtl4c6, R.id.txtl4c7, R.id.txtl4c8, R.id.txtl4c9, R.id.txtl4c10, R.id.txtl4c11},
            {R.id.txtl5c0, R.id.txtl5c1, R.id.txtl5c2, R.id.txtl5c3, R.id.txtl5c4, R.id.txtl5c5,
                    R.id.txtl5c6, R.id.txtl5c7, R.id.txtl5c8, R.id.txtl5c9, R.id.txtl5c10, R.id.txtl5c11},
            {R.id.txtl6c0, R.id.txtl6c1, R.id.txtl6c2, R.id.txtl6c3, R.id.txtl6c4, R.id.txtl6c5,
                    R.id.txtl6c6, R.id.txtl6c7, R.id.txtl6c8, R.id.txtl6c9, R.id.txtl6c10, R.id.txtl6c11},
            {R.id.txtl7c0, R.id.txtl7c1, R.id.txtl7c2, R.id.txtl7c3, R.id.txtl7c4, R.id.txtl7c5,
                    R.id.txtl7c6, R.id.txtl7c7, R.id.txtl7c8, R.id.txtl7c9, R.id.txtl7c10, R.id.txtl7c11},
            {R.id.txtl8c0, R.id.txtl8c1, R.id.txtl8c2, R.id.txtl8c3, R.id.txtl8c4, R.id.txtl8c5,
                    R.id.txtl8c6, R.id.txtl8c7, R.id.txtl8c8, R.id.txtl8c9, R.id.txtl8c10, R.id.txtl8c11},
            {R.id.txtl9c0, R.id.txtl9c1, R.id.txtl9c2, R.id.txtl9c3, R.id.txtl9c4, R.id.txtl9c5,
                    R.id.txtl9c6, R.id.txtl9c7, R.id.txtl9c8, R.id.txtl9c9, R.id.txtl9c10, R.id.txtl9c11},
            {R.id.txtl10c0, R.id.txtl10c1, R.id.txtl10c2, R.id.txtl10c3, R.id.txtl10c4, R.id.txtl10c5,
                    R.id.txtl10c6, R.id.txtl10c7, R.id.txtl10c8, R.id.txtl10c9, R.id.txtl10c10, R.id.txtl10c11}
    };
    Random random = new Random();

    TextView txt1;
    //Criando matriz de objetos textView com o tamanho array de id´s
    private TextView[][] textViewsObject = new TextView[idTextViews.length][idTextViews[0].length];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Obtendo a view do fragmento
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Collections.shuffle(Arrays.asList(letras));

        ObterRecurso or = new ObterRecurso();

        try {
            palavra = or.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Linkar com o componente da view
        for (int linhas = 0; linhas < textViewsObject.length; linhas++) {
            for (int cols = 0; cols < textViewsObject[0].length; cols++) {
                textViewsObject[linhas][cols] = (TextView) view.findViewById(idTextViews[linhas][cols]);
                textViewsObject[linhas][cols].setOnClickListener(this);
            }
        }

        for (int linhas = 0; linhas < textViewsObject.length; linhas++) {
            for (int cols = 0; cols < textViewsObject[0].length; cols++) {
                textViewsObject[linhas][cols].setText(letras[embaralhar(letras.length)]);
            }
        }

        // pega as letras da palavra e coloca em um array de char
        char[] arrayPalavra = palavra.toCharArray();

        // recebe as posiçoes iniciais em que as palavras serão lançadas
        linhaInicio = embaralhar(10);
        colunaInicio = embaralhar(11);
        colunaInicio = verificarLimiteHorizontal(palavra.length(), colunaInicio);

         //enviar palavras a view
            for (int h = 0; h < arrayPalavra.length; h++){
                textViewsObject[linhaInicio][colunaInicio].setText(String.valueOf(arrayPalavra[h]));
                localPalavras[h] = textViewsObject[linhaInicio][colunaInicio];
                System.out.println(String.valueOf(linhaInicio) + String.valueOf(colunaInicio));
                colunaInicio++;
            }



        return view;
    }

    public int embaralhar(int limitador) {
        //Embaralhando as letras atráves da posição
        int res = random.nextInt(limitador);
        return res;
    }

    public int verificarLimiteHorizontal(int tamanhoPalavra, int posicaoInicial){
        if (12 - posicaoInicial < tamanhoPalavra ){
            return 12 - tamanhoPalavra;
        }
        return posicaoInicial;
    }

    int u = palavra.length();

    @Override
    public void onClick(View v) {

        if (u > 0){
            for (int i = 0; i < palavra.length(); i++){
                if (v.getId() == localPalavras[i].getId()) {
                    localPalavras[i].setBackgroundColor(YELLOW);
                    localPalavras[i].setClickable(false);;
                    System.out.println(localPalavras[i].getId());
                }
            }
            System.out.println(u);
            u--;
        }else {
            Toast.makeText(getActivity().getApplicationContext(), "Fim de Jogo!", Toast.LENGTH_SHORT).show();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), "11")
                    .setSmallIcon(R.drawable.ic_menu_slideshow)
                    .setContentTitle("Vocẽ Finalizou o Jogo!")
                    .setContentText("Parabéns")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            //Registro da notificação no gerenciador
            NotificationManagerCompat nm = NotificationManagerCompat.from(getActivity().getApplicationContext());
            nm.notify(notificationId, builder.build());

        }
    }

private class ObterRecurso extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity().getApplicationContext(), "download JSON...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Void... strings) {
                String resultado = "";
                Auxiliar auxiliar = new Auxiliar();
                String jsonStr = auxiliar.consumir(url);
                Gson gson = new Gson();
                //Object porque é uma lista de pessoas, se fosse uma colocarias Pessoa.class
                Palavra palavra = gson.fromJson(jsonStr,Palavra.class);

            return palavra.getWord();
        }

    /*@Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }*/


    }

}
