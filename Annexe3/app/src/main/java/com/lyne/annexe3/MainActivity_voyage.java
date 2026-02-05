package com.lyne.annexe3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.spec.ECField;
import java.text.DecimalFormat;

public class MainActivity_voyage extends AppCompatActivity {
    Ecouteur ec;
    ImageButton onAvion;
    ImageButton onHotel;
    Button totalBouton;
    TextView billetAvion;
    TextView chambreHotel;
    TextView montantTotal;
    int nbreBillets;
    int nbreChambre;
    double somme;
    Commande com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_voyage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        onAvion = findViewById(R.id.avionButton);
        onHotel = findViewById(R.id.hotelButton);
        billetAvion = findViewById(R.id.avionNbre);
        chambreHotel = findViewById(R.id.hotelNbre);
        totalBouton = findViewById(R.id.totalButton);
        montantTotal = findViewById(R.id.totalText);

        ec = new Ecouteur();
        onAvion.setOnClickListener(ec);
        onHotel.setOnClickListener(ec);
        totalBouton.setOnClickListener(ec);
        com = new Commande();

    }
    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View source){
            if(source == onAvion){
                nbreBillets+=1;
                billetAvion.setText(String.valueOf(nbreBillets));
                com.ajouterProduit(new BilletAvion(nbreBillets));
            }else if(source == onHotel){
                nbreChambre+=1;
                chambreHotel.setText(String.valueOf(nbreChambre));
                com.ajouterProduit(new HebergementHotel(nbreChambre));
            }else if(source == totalBouton){
                DecimalFormat df = new DecimalFormat("0.00$");

                montantTotal.setText(String.valueOf(df.format(com.grandTotal())));
            }

        }

    }
}