package com.lyne.travailfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    TextView nomJoueur, nbreMots;
    ListView listePointage;
    Button retour;
    Intent i;
    Ecouteur ec;
    GestionBD instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nomJoueur = findViewById(R.id.nomJoueur);
        nbreMots = findViewById(R.id.nbreMots);
        retour = findViewById(R.id.boutonRetour);
        listePointage = findViewById(R.id.listePointage);

        ec = new Ecouteur();
        retour.setOnClickListener(ec);
    }
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if(source == retour){
                i = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(i);
                finish();
            }

        }
    }
}
