package com.lyne.annexe3;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    ImageButton onBidon;
    ImageButton onGourde;
    ImageButton onVerre;
    TextView litre;
    ProgressBar barre;
    int eau;



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

        onBidon = findViewById(R.id.boutonBidon);
        onGourde = findViewById(R.id.boutonBouteille);
        onVerre = findViewById(R.id.boutonVerre);
        litre = findViewById(R.id.litre);
        barre = findViewById(R.id.progressBar);

        ec = new Ecouteur();

        onBidon.setOnClickListener(ec);
        onGourde.setOnClickListener(ec);
        onVerre.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source){

            if(source == onBidon){
                eau += 1500;
                litre.setText(String.valueOf(eau));
                barre.setProgress(eau);

            }else if(source == onGourde){
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