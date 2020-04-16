package com.jeremiasneres.wordgame.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jeremiasneres.wordgame.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class HomeFragment extends Fragment implements View.OnClickListener {


    String letras[] = {"A","B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    String orientacao[] = {"horizontal", "vertical"};
    String[] palavras = {"tomate", "local", "boi", "jumento", "fruta", "verdura"};
    String localPalavras[][] = new String[6][12];

    //Criando uma matriz de inteiros contendo os ids dos TextView
    private static final int[][] idTextViews = {
            { R.id.txtl0c0, R.id.txtl0c1, R.id.txtl0c2, R.id.txtl0c3, R.id.txtl0c4, R.id.txtl0c5,
            R.id.txtl0c6, R.id.txtl0c7, R.id.txtl0c8, R.id.txtl0c9, R.id.txtl0c10, R.id.txtl0c11 },
            { R.id.txtl1c0, R.id.txtl1c1, R.id.txtl1c2, R.id.txtl1c3, R.id.txtl1c4, R.id.txtl1c5,
            R.id.txtl1c6, R.id.txtl1c7, R.id.txtl1c8, R.id.txtl1c9, R.id.txtl1c10, R.id.txtl1c11 },
            { R.id.txtl2c0, R.id.txtl2c1, R.id.txtl2c2, R.id.txtl2c3, R.id.txtl2c4, R.id.txtl2c5,
            R.id.txtl2c6, R.id.txtl2c7, R.id.txtl2c8, R.id.txtl2c9, R.id.txtl2c10, R.id.txtl2c11 },
            { R.id.txtl3c0, R.id.txtl3c1, R.id.txtl3c2, R.id.txtl3c3, R.id.txtl3c4, R.id.txtl3c5,
            R.id.txtl3c6, R.id.txtl3c7, R.id.txtl3c8, R.id.txtl3c9, R.id.txtl3c10, R.id.txtl3c11 },
            { R.id.txtl4c0, R.id.txtl4c1, R.id.txtl4c2, R.id.txtl4c3, R.id.txtl4c4, R.id.txtl4c5,
            R.id.txtl4c6, R.id.txtl4c7, R.id.txtl4c8, R.id.txtl4c9, R.id.txtl4c10, R.id.txtl4c11 },
            { R.id.txtl5c0, R.id.txtl5c1, R.id.txtl5c2, R.id.txtl5c3, R.id.txtl5c4, R.id.txtl5c5,
            R.id.txtl5c6, R.id.txtl5c7, R.id.txtl5c8, R.id.txtl5c9, R.id.txtl5c10, R.id.txtl5c11 },
            { R.id.txtl6c0, R.id.txtl6c1, R.id.txtl6c2, R.id.txtl6c3, R.id.txtl6c4, R.id.txtl6c5,
            R.id.txtl6c6, R.id.txtl6c7, R.id.txtl6c8, R.id.txtl6c9, R.id.txtl6c10, R.id.txtl6c11 },
            { R.id.txtl7c0, R.id.txtl7c1, R.id.txtl7c2, R.id.txtl7c3, R.id.txtl7c4, R.id.txtl7c5,
            R.id.txtl7c6, R.id.txtl7c7, R.id.txtl7c8, R.id.txtl7c9, R.id.txtl7c10, R.id.txtl7c11 },
            { R.id.txtl8c0, R.id.txtl8c1, R.id.txtl8c2, R.id.txtl8c3, R.id.txtl8c4, R.id.txtl8c5,
            R.id.txtl8c6, R.id.txtl8c7, R.id.txtl8c8, R.id.txtl8c9, R.id.txtl8c10, R.id.txtl8c11 },
            { R.id.txtl9c0, R.id.txtl9c1, R.id.txtl9c2, R.id.txtl9c3, R.id.txtl9c4, R.id.txtl9c5,
            R.id.txtl9c6, R.id.txtl9c7, R.id.txtl9c8, R.id.txtl9c9, R.id.txtl9c10, R.id.txtl9c11 },
            { R.id.txtl10c0, R.id.txtl10c1, R.id.txtl10c2, R.id.txtl10c3, R.id.txtl10c4, R.id.txtl10c5,
            R.id.txtl10c6, R.id.txtl10c7, R.id.txtl10c8, R.id.txtl10c9, R.id.txtl10c10, R.id.txtl10c11 }
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

        //Linkar com o componente da view
        for(int linhas=0;linhas<textViewsObject.length;linhas++){
            for(int cols=0;cols<textViewsObject[0].length;cols++){
                textViewsObject[linhas][cols] = (TextView) view.findViewById(idTextViews[linhas][cols]);
                //textViewsObject[i].setTag(i);
                textViewsObject[linhas][cols].setOnClickListener(this);
            }
        }

        for(int linhas=0;linhas<textViewsObject.length;linhas++){
            for(int cols=0;cols<textViewsObject[0].length;cols++){
                textViewsObject[linhas][cols].setText(letras[embaralhar(letras.length)]);
            textViewsObject[linhas][cols].setTextColor(WHITE);
            }
        }

        for (int i = 0; i < palavras.length; i++){
            String palavraSelecionada = palavras[i];
            // posiçoes
            int linhaInicio = 0;
            int colunaInicio = 0;
            int indicePalavra = procurarIndicePalavra(palavraSelecionada);

            //orientação
            boolean x = false;
            char[] arrayPalavra = palavraSelecionada.toCharArray();

            linhaInicio = embaralhar(10);
            colunaInicio = embaralhar(11);
            colunaInicio = verificarLimiteHorizontal(palavraSelecionada.length(), colunaInicio);

            //gerar posições
            for (int h = 0; h < arrayPalavra.length; h++){
                localPalavras[indicePalavra][h] = String.valueOf(linhaInicio) + String.valueOf(colunaInicio);
                colunaInicio++;
            }

            verConflitos(palavraSelecionada);

            /*enviar palavras a view
            for (int h = 0; h < arrayPalavra.length; h++){
                textViewsObject[linhaInicio][colunaInicio].setText(String.valueOf(arrayPalavra[h]));
                textViewsObject[linhaInicio][colunaInicio].setTextColor(RED);
                colunaInicio++;
            }*/
            //if (orientacao[embaralhar(orientacao.length)].equals("horizontal") ){



            /*}else if (orientacao[embaralhar(orientacao.length)].equals("vertical")) {
                colunaInicio = embaralhar(11);
                linhaInicio = embaralhar(10);

                linhaInicio = verificarLimiteVertical(palavraSelecionada.length(), linhaInicio);

                char[] arrayPalavra = palavraSelecionada.toCharArray();
                for (int h = 0; h < arrayPalavra.length; h++){
                    textViewsObject[linhaInicio][colunaInicio].setText(String.valueOf(arrayPalavra[h]));
                    textViewsObject[linhaInicio][colunaInicio].setTextColor(BLUE);
                    localPalavras[indicePalavra][h] = String.valueOf(linhaInicio) + String.valueOf(colunaInicio);
                    linhaInicio++;
                }
             Toast.makeText(getActivity().getApplicationContext(), "...", Toast.LENGTH_LONG).show();
            }*/
            }

        return view;
    }


    public void verConflitos(String palavraEscolhida){
        int indicePalavra = procurarIndicePalavra(palavraEscolhida);
        boolean x = false;
        while (x == false){
            /*int linhaInicio = embaralhar(10);
            int colunaInicio = embaralhar(11);
            colunaInicio = verificarLimiteHorizontal(palavraEscolhida.length(), colunaInicio);*/

            System.out.println("palavra " + palavras[indicePalavra]);
            for (int k = 0; k < palavraEscolhida.length(); k++){
                //if (localPalavras[indicePalavra][k]);
            }
            x = true;
        }
    }

   // public void procurarPalavraMatriz (strin)

    public int embaralhar(int limitador){
        //Embaralhando as letras atráves da posição
        int res = random.nextInt(limitador);
        return res;
    }

    public int procurarIndicePalavra(String palavra){
        for (int j = 0; j < palavras.length; j++){
            if (palavras[j].equals(palavra)){
                return j;
            }
        }
        return 0;
    };

    public int verificarLimiteHorizontal(int tamanhoPalavra, int posicaoInicial){
        if (12 - posicaoInicial < tamanhoPalavra ){
            return 12 - tamanhoPalavra;
        }
        return posicaoInicial;
    }

    public int verificarLimiteVertical(int tamanhoPalavra, int posicaoInicial){
        if (11 - posicaoInicial < tamanhoPalavra ){
            return 11 - tamanhoPalavra;
        }
        return posicaoInicial;
    }


    @Override
    public void onClick(View v) {
        //txt1.setText("a");
        /*
        * switch (v.getId()) {
                    case R.id.button_submit:
                        edttxt_projectname.setText("Test Submit!#@%!#%");
                        break;
                default:
                        break;
                    }*/
               /*
        for (int i = 0; i < textViewsObject.length; i++) {
            int res = random.nextInt(letras.length);
            textViewsObject[i].setText(letras[res]);
        }*/
    }
}
