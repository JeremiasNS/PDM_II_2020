package com.jeremiasneres.oscarjson;

public class Filme {
    private String nome;
    private String direcao;
    private String categoria;

    public Filme(String nome, String direcao, String categoria) {
        this.nome = nome;
        this.direcao = direcao;
        this.categoria = categoria;
    }

    public Filme() {
    }

    @Override
    public String toString() {
        return "Filme{" +
                "nome='" + nome + '\'' +
                ", direcao='" + direcao + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
