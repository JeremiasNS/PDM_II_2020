package com.jeremiasneres.onepunchquiz.ui.pergunta;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.jeremiasneres.onepunchquiz.FragmentListener;
import com.jeremiasneres.onepunchquiz.R;

public class PerguntasFragment extends Fragment{

    private FragmentListener mListener;

    EditText pergunta1;
    EditText pergunta2;
    EditText pergunta3;
    EditText pergunta4;

    int acertos;
    int erros;
    int total;

    Button btnEnviar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perguntas, container, false);

        //pergunta1 = root.findViewById(R.id.pergunta_1);
        //pergunta2 = root.findViewById(R.id.pergunta_2);
        //pergunta3 = root.findViewById(R.id.pergunta_3);
        //pergunta4 = root.findViewById(R.id.pergunta_4);

        //btnEnviar = this.getActivity().findViewById(R.id.enviar);
        btnEnviar = (Button) root.findViewById(R.id.enviar);
        btnEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //pontuar();
                Toast.makeText(getActivity(), "acertos"+String.valueOf(acertos), Toast.LENGTH_SHORT).show();
            }
        });

        return root;

    }

    public void pontuar() {
        if (pergunta1.getText().toString() == "17") {
            total += 25;
            acertos += 1;
        } else {
            erros += 1;
        }
        if (pergunta2.getText().toString() == "one") {
            total += 25;
            acertos += 1;
        } else {
            erros += 1;
        }
        if (pergunta3.getText().toString() == "0") {
            total += 25;
            acertos += 1;
        } else {
            erros += 1;
        }
        if (pergunta4.getText().toString() == "vermelho") {
            total += 25;
            acertos += 1;
        } else {
            erros += 1;
        }
    }

    public void salvar() {

        //Utilizando o shared preferences para armazenar dados dos filmes
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("JSON_ACERTOS", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("JSON_ACERTOS", String.valueOf(acertos));
        editor.apply();
    }

}