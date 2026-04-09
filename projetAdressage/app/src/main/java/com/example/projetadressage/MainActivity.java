package com.example.projetadressage;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;


import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import bla.HashtableAssociation;


public class MainActivity extends AppCompatActivity {

    EditText champPrenom, champNom, champAdresse, champZip;
    Spinner spinnerCapitale, spinnerEtat;
    ArrayList<String> listeCapitale;
    ArrayList<String> listeEtat;
    Button bouton;
    Ecouteur ec;
    ArrayList<Inscrit> listElecteur;
    HashtableAssociation h = new HashtableAssociation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champPrenom = findViewById(R.id.champPrenom);
        champNom= findViewById(R.id.champNom);
        champAdresse = findViewById(R.id.champAdresse);
        champZip = findViewById(R.id.champZip);

        spinnerCapitale = findViewById(R.id.spinnerCapitale);
        spinnerEtat = findViewById(R.id.spinnerEtat);

        bouton = findViewById(R.id.boutonInscrire);

        listeEtat = new ArrayList<>();
        listeCapitale = new ArrayList<>();
        listElecteur = new ArrayList<>();

        listeEtat.addAll(h.values());
        Collections.sort(listeEtat);
        listeCapitale.addAll(h.keySet());
        Collections.sort(listeCapitale);

        //méthode prof
//        Set<String> enCles = h.keySet();
//        ArrayList<String> enclesA = new ArrayList<>(enCles);
//        ArrayAdapter adapterEtat = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, enclesA);

        // remplir les spinner à l'aide de la Hashtable
        ArrayAdapter adapterCapitale = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listeCapitale);
        spinnerCapitale.setAdapter(adapterCapitale);

        ArrayAdapter adapterEtat = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listeEtat);
        spinnerEtat.setAdapter(adapterEtat);

        ec = new Ecouteur();
        bouton.setOnClickListener(ec);

    }
    //pour créer une boite de dialogue simple
    private class Ecouteur implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            String nom, prenom, adresse, code, capitale, etat;
            nom = champNom.getText().toString();
            prenom = champPrenom.getText().toString();
            adresse = champAdresse.getText().toString();
            code = champZip.getText().toString();
            capitale = spinnerCapitale.getSelectedItem().toString();
            etat = spinnerEtat.getSelectedItem().toString();

            try {
                Inscrit i = new Inscrit(nom, prenom, adresse, capitale, etat, code);
                creerAlertDialog("Électeur inscrit", "Message");
                listElecteur.add(i);
            }
            catch (AdresseException ae){
                creerAlertDialog(ae.getMessage(), "Erreur");

                if(ae.getValeurErronee().equals("nom")){
                    champNom.requestFocus();
                }
                else  if(ae.getValeurErronee().equals("prenom")){
                    champPrenom.requestFocus();
                }
                else if(ae.getValeurErronee().equals("adresse")){
                    champAdresse.requestFocus();
                }
                else if(ae.getValeurErronee().equals("codeZip")){
                    champZip.requestFocus();
                }

            }

        }
    }
    public void creerAlertDialog(String message, String titre) {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        //on peut faire ca !!
        builder.setMessage(message)
                .setTitle(titre);


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}