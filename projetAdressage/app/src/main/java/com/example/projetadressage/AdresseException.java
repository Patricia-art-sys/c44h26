package com.example.projetadressage;

public class AdresseException extends Exception {
    private String valeurErronee;
    public AdresseException(String capitale, String etat)
    {
        super ("la capitale " + capitale + " n'est pas dans l'état " + etat);


    }

    public AdresseException (String champVide)
    {
        super ( "Le " + champVide + " est vide. Veuillez le remplir");
        valeurErronee = champVide;
    }

    public String getValeurErronee() {
        return valeurErronee;
    }
}
