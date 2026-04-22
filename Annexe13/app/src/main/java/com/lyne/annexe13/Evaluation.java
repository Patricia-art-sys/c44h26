package com.lyne.annexe13;

public class Evaluation {
    private String nom;
    private String microbrasserie;
    private int etoile;

    public Evaluation(String nom, String microbrasserie, int etoile) {
        this.nom = nom;
        this.microbrasserie = microbrasserie;
        this.etoile = etoile;
    }

    public String getNom() {
        return nom;
    }

    public String getMicrobrasserie() {
        return microbrasserie;
    }

    public int getEtoile() {
        return etoile;
    }
}
