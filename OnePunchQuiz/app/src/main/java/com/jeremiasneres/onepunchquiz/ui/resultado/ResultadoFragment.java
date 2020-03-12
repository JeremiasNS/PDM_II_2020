package com.jeremiasneres.onepunchquiz.ui.resultado;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jeremiasneres.onepunchquiz.R;

public class ResultadoFragment extends Fragment {

    TextView acerto;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        acerto = this.getActivity().findViewById(R.id.acertos);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("JSON_FILMES", 0);
        String resultSharedPreferences = sharedPreferences.getString("JSON_FILMES", "");

        //acerto.setText(resultSharedPreferences);

        View root = inflater.inflate(R.layout.fragment_resultado, container, false);

        return root;
    }
}