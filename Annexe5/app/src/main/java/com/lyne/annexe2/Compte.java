package com.lyne.annexe2;

public class Compte {
    private String nomCompte;
    private int soldeCompte;

    public Compte(String nomCompte, int soldeCompte) {
        this.nomCompte = nomCompte;
        this.soldeCompte = soldeCompte;
    }

    public String getNomCompte() {
        return nomCompte;
    }

    public int getSoldeCompte() {
        return soldeCompte;
    }

    public boolean transfert(int montant){
        if(soldeCompte >= montant){
            soldeCompte -= montant;
            return true;
        }
        return false;
    }
}
