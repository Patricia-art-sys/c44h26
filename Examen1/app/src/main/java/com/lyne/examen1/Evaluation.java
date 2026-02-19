package com.lyne.examen1;

public class Evaluation {
    private String matricule;
    private int nbServices;

    public Evaluation(String matricule, int nbServices) {
        this.matricule = matricule;
        this.nbServices = nbServices;
    }

    public String getMatricule() {
        return matricule;
    }

    public int getNbServices() {
        return nbServices;
    }
}
