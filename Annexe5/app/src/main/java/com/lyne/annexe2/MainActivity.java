package com.lyne.annexe2;

import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button boutonEnvoyer;
    Spinner spinnerNom;
    EditText champMail;
    EditText champTransfert;
    TextView champSolde;
    ArrayList<String> choix;
    
    Compte compteChoisi;
    int montantTransfert;
    DecimalFormat df = new DecimalFormat("#,##0.00");

    HashMap<String, Compte> hm = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boutonEnvoyer = findViewById(R.id.boutonEnvoyer);
        spinnerNom = findViewById(R.id.compteChoisi);
        champMail = findViewById(R.id.mailAdress);
        champTransfert = findViewById(R.id.transfert);
        champSolde = findViewById(R.id.solde);

        choix = new ArrayList<>();
        hm.put("Chèques", new Compte("Chèque", 1000));
        hm.put("Épargne", new Compte("Épargne", 200));
        hm.put("Épargne plus", new Compte("Épargne plus", 490));

        choix.addAll(hm.keySet()); //place toutes les clés dans le spinner

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choix);
        spinnerNom.setAdapter(adapter);

        // étape 1
        ec = new Ecouteur();

        // étape 2

        boutonEnvoyer.setOnClickListener(ec);
        spinnerNom.setOnItemSelectedListener(ec);
    }

    //étape 3

    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {
        @Override
        public void onClick(View source) {

            String email = champMail.getText().toString();

            if(!email.isEmpty()){
                String transfert = champTransfert.getText().toString();
                montantTransfert = parseInt(transfert);
                if(compteChoisi.transfert(montantTransfert)){
                    //affiche le nouveau solde
                    champSolde.setText(df.format(compteChoisi.getSoldeCompte()));

                    //effacher le champ de transfert
                    champTransfert.setText("");

                }else{
                    //effacer le champ transfert
                    champTransfert.setText("");
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Attention")
                            .setMessage("Il manque des fonds")
                            .show();
                }
            }else{
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Attention")
                        .setMessage("Il faut indiquer le mail")
                        .show();
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //méthode eric
            TextView temp = (TextView) view;
            String texte3 = temp.getText().toString();
            //trouver le compte si je connais le nom du compte
            compteChoisi = hm.get(texte3);

            //afficher le solde du compt dans le champSolde
            champSolde.setText(df.format(compteChoisi.getSoldeCompte()));

//            //méthode Jacob
//            String texte = parent.getAdapter().getItem(position).toString();
//            //méthode Ludovic
//            String texte1 = (String) parent.getSelectedItem();
//            //méthode emile-eric
//            String texte2 = choix.get(position);
//
//            //methode 5
//            String texte4 = (String) parent.getItemAtPosition(position);
//            // texte += spinnerNom.getSelectedItem().toString();
//            Toast.makeText(MainActivity.this,texte3,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}