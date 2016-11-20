package br.edu.ufabc.padm.pocketmentaltest.model;

import java.util.Date;

/**
 * Created by victor on 11/20/16.
 */

public class Result {
    private long id;
    private Date data;
    private String teste;
    private long score;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    public String getTeste() {
        return teste;
    }
    public void setTeste(String teste) {
        this.teste = teste;
    }

    public long getScore() {
        return score;
    }
    public void setScore(long score) {
        this.score = score;
    }
}
