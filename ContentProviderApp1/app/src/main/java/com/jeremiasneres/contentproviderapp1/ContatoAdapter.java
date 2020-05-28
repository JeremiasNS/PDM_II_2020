package com.jeremiasneres.contentproviderapp1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {

    private final List<Contato> contatos;
    private final Activity act;

    public ContatoAdapter(List<Contato> contatos, Activity act) {
        this.contatos = contatos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.item_list, parent, false);
        Contato contato = contatos.get(position);

        //pegando as referências das Views
        TextView nome = (TextView) view.findViewById(R.id.nome);
        TextView tel = (TextView) view.findViewById(R.id.contato);

        //populando as Views
        nome.setText(contato.getNome());
        tel.setText(contato.getFone());

        return view;
    }
}

