package game.curso.aula130820avaliacaofinal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private int num = 0;
    private ArrayList<ListItem> listData;
    private LayoutInflater layoutInflater;
    public CustomListAdapter(Context aContext, ArrayList<ListItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list, null);
            holder = new ViewHolder();
            holder.uNome = (TextView) convertView.findViewById(R.id.txtList);
            holder.uOptions = (Spinner) convertView.findViewById(R.id.spnOptions);
            holder.uIcones = (ImageView) convertView.findViewById(R.id.iconPerguntas);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        GridLayout gridLayout = convertView.findViewById(R.id.layoutLista);
        if (num == 0) {
            gridLayout.setBackgroundColor(convertView.getContext().getResources().getColor(R.color.verde0));
            num = 1;
        }else{
            gridLayout.setBackgroundColor(convertView.getContext().getResources().getColor(R.color.verde1));
            num = 0;
        }

        holder.uNome.setText(listData.get(position).getNome());
        holder.uIcones.setBackground(
                convertView.getContext().getDrawable(listData.get(position).getIcones()));

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(convertView.getContext(),
                R.array.opcoesSpin, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        holder.uOptions.setAdapter(adapter);

        return convertView;
    }
    static class ViewHolder {
        TextView uNome;
        Spinner uOptions;
        ImageView uIcones;
    }
}
