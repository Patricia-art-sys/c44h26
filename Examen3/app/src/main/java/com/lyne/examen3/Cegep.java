package com.lyne.examen3;

public class Cegep {
    private String nom;
    private int nbrEquipe;
    private String adresse;

    public Cegep(String nom, int nbrEquipe, String adresse) {
        this.nom = nom;
        this.nbrEquipe = nbrEquipe;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public int getNbrEquipe() {
        return nbrEquipe;
    }

    public String getAdresse() {
        return adresse;
    }
}
