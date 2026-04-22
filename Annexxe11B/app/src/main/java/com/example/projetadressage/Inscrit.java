package com.example.projetadressage;


import bla.HashtableAssociation;

public class Inscrit {
    private String nom;
    private String prenom;
    private String adresse;
    private String capitale;
    private String etat;
    private String codeZip;
    private String valeurErronee;

    public Inscrit(String nom, String prenom, String adresse, String capitale, String etat, String codeZip) throws AdresseException {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codeZip = codeZip;


        // vérifier si la capitale fait partie de l'état à l'aide d'une Hashtable secrète ( classe HashtableAssociation )
        HashtableAssociation h = new HashtableAssociation();

        if(!h.get(capitale).equals(etat)){
            throw new AdresseException(capitale, etat);

        }
        if(nom.trim().isEmpty())
            throw new AdresseException("nom");

        else if(prenom.trim().isEmpty())
            throw new AdresseException("prenom");

        else if(adresse.trim().isEmpty())
            throw new AdresseException("adresse");

        else if(codeZip.trim().isEmpty())
            throw new AdresseException("codeZip");


    }
}
