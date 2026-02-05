package com.lyne.annexe3;

import java.text.DecimalFormat;
import java.util.Vector;

public class Commande {

    private Vector<Produit> listeCommande;

    public Commande ( )
    {
        listeCommande = new Vector();
    }

    public void ajouterProduit ( Produit p )
    {
        listeCommande.add(p);
    }

    public double total ()
    {
	    double total =0;
      // compléter : total de la commande
        for(Produit p : listeCommande){
           total += p.getPrixUnitaire();
        }
        return total;
    }

    public double taxes()
    {
        double taxes = 0;

	// compléter : montant des taxes sur le total de la commande

        // tps sur le montant avant taxes ( 5% )
        //tvq sur le montant avant taxes ( 9.975% )
        // taxes total = tps + tvq
        taxes = total() * 0.05 + total() * 0.09975;

        return taxes;
    }

    public double grandTotal(){

        double grTotal = 0;

        // compléter
        grTotal = total() + taxes();
        return grTotal;

    }
}
