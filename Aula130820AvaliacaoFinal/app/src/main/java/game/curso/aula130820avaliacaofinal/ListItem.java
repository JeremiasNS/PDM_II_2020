package game.curso.aula130820avaliacaofinal;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Spinner;

public class ListItem {
    private String nome;
    private Integer icone;
    private Spinner spinner;

    public ListItem(String nome, Integer icone, Spinner spinner) {
        this.spinner = spinner;
        this.nome = nome;
        this.icone = icone;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIcones() {
        return icone;
    }

    public void setIcones(Integer icones) {
        this.icone = icones;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }
}
