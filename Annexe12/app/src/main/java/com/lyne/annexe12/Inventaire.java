package com.lyne.annexe12;

public class Inventaire {
    private String nom;
    private String origin;
    private String invention;
    private int annee;

    public Inventaire(String nom, String origin, String invention, int annee) {
        this.nom = nom;
        this.origin = origin;
        this.invention = invention;
        this.annee = annee;
    }

    public String getNom() {
        return nom;
    }

    public String getOrigin() {
        return origin;
    }

    public String getInvention() {
        return invention;
    }

    public int getAnnee() {
        return annee;
    }
}
