package com.lyne.examen3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView nomEquipe, nbrEquipe, adresse;
    GestionBD instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nomEquipe = findViewById(R.id.nomCegep);
        nbrEquipe = findViewById(R.id.nbrEquipe);
        adresse = findViewById(R.id.adresse);

        instance = GestionBD.getInstance(getApplicationContext());
        String nomCegep = getIntent().getStringExtra("name");

        nomEquipe.setText(instance.retournerCegep(nomCegep).getNom());
        nbrEquipe.setText(String.valueOf(instance.retournerCegep(nomCegep).getNbrEquipe()));
        adresse.setText(instance.retournerCegep(nomCegep).getAdresse());

    }
}