package com.lyne.travailfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    SeekBar seekBar;
    TextView totalPointage, mot, pointParMot;
    TableLayout grilleJeu;
    Ecouteur ec;
    GestionBD instance;
    Intent i;

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
        seekBar = findViewById(R.id.seekBar2);
        totalPointage = findViewById(R.id.pointTotal);
        mot = findViewById(R.id.mot);
        pointParMot = findViewById(R.id.point);
        grilleJeu = findViewById(R.id.grilleJeu);


        ec = new Ecouteur();
        //boucle pour rajouter un ecouteur à toutes les conposantes
        seekBar.setOnSeekBarChangeListener(ec);
    }
    private class Ecouteur implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(progress == seekBar.getMax()){
                i = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(i);
                finish();
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}

