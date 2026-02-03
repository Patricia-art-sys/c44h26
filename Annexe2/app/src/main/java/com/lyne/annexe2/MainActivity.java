package com.lyne.annexe2;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button boutonValider;
    Button boutonEnvoyer;
    EditText champNomCompte;
    EditText champMail;
    EditText champTransfert;
    TextView champSolde;
    ArrayList<String> choix;

    int solde;
    int montantTransfert;

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

        boutonValider = findViewById(R.id.boutonValider);
        boutonEnvoyer = findViewById(R.id.boutonEnvoyer);
        champNomCompte = findViewById(R.id.compteChoisi);
        champMail = findViewById(R.id.mailAdress);
        champTransfert = findViewById(R.id.transfert);
        champSolde = findViewById(R.id.solde);

        choix = new ArrayList<>();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");

        // étape 1
        ec = new Ecouteur();
        // étape 2
        boutonValider.setOnClickListener(ec);
        boutonEnvoyer.setOnClickListener(ec);
    }

    //étape 3

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source) {
            if(source ==boutonValider){
                //quand on clic on est ici
                String nomCompte = champNomCompte.getText().toString();
                //trim retire les espaces au début et à la fin
                nomCompte = nomCompte.trim().toUpperCase();
                if(choix.contains(nomCompte)){
                    solde = 500;
                    champSolde.setText(String.valueOf(solde));
                }
                else{
                    champSolde.setText("Compte Inexistant");
                    champNomCompte.setText(" ");
                }
            }
            //boutonEnvoyer
            else{
                String email = champMail.getText().toString();
                String transfert = champTransfert.getText().toString();
                montantTransfert = parseInt(transfert);
                if(!email.isEmpty()){
                    if(solde < montantTransfert){
                        champMail.setText("Montant indisponible");
                        champTransfert.setText("");
                    }else{
                        champMail.setText("Transfert réussi!");
                        solde -= montantTransfert;
                        champSolde.setText(String.valueOf(solde));
                    }
                }else{

                }

            }

        }
    }
}