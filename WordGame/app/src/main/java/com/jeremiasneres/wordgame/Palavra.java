package com.jeremiasneres.wordgame;

public class Palavra {
    private int wid;
    private String word;
    private int sense;

    @Override
    public String toString() {
        return "Palavra{" +
                "wid=" + wid +
                ", word='" + word + '\'' +
                ", sense=" + sense +
                '}';
    }

    public Palavra() {
    }

    public Palavra(int wid, String word, int sense) {
        this.wid = wid;
        this.word = word;
        this.sense = sense;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getSense() {
        return sense;
    }

    public void setSense(int sense) {
        this.sense = sense;
    }
}
