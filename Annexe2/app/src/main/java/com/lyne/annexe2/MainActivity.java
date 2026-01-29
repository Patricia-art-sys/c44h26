package com.lyne.annexe2;

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
    EditText champNomCompte;
    TextView champSolde;
    ArrayList<String> choix;

    int solde;

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
        champNomCompte = findViewById(R.id.compteChoisi);
        champSolde = findViewById(R.id.solde);

        choix = new ArrayList<>();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");

        // étape 1
        ec = new Ecouteur();
        // étape 2
        boutonValider.setOnClickListener(ec);
    }

    //étape 3

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //quand on clic on est ici
            String nomCompte = champNomCompte.getText().toString();
            //trim retire les espaces au début et à la fin
            nomCompte = nomCompte.trim().toUpperCase();
            if(choix.contains(nomCompte)){
                solde = 500;
                champSolde.setText(String.valueOf(solde));
            }
        }
    }
}