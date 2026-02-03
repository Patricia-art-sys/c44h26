package com.lyne.annexe3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button boutonBidon;
    Button boutonBouteille;
    Button boutonVerre;
    TextView litre;
    ProgressBar barre;
    int eau;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
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
        boutonBidon = findViewById(R.id.boutonBidon);
        boutonBouteille = findViewById(R.id.boutonBouteille);
        boutonVerre = findViewById(R.id.boutonVerre);
        litre = findViewById(R.id.litre);
        barre = findViewById(R.id.progressBar);

        ec = new Ecouteur();

        boutonBidon.setOnClickListener(ec);
        boutonBouteille.setOnClickListener(ec);
        boutonVerre.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source){
            if(source == boutonBidon){
                eau += 1500;
                litre.setText(String.valueOf(eau));
                barre.setProgress(eau);
            }else if(source == boutonBouteille){
                eau += 330;
                litre.setText(String.valueOf(eau));
                barre.setProgress(eau);
            }else{
                eau += 150;
                litre.setText(String.valueOf(eau));
                barre.setProgress(eau);
            }

        }
    }
}