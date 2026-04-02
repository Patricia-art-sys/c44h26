package com.example.appexamenetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout sectionHaut;
    LinearLayout sectionBas;
    Ecouteur ec;
    Surface surf;
    SeekBar barre;
    TraitContinu trait;
    String direction;
    Point depart, arrivee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionHaut = findViewById(R.id.sectionHaut);
        sectionBas = findViewById(R.id.sectionBas);
        barre = findViewById(R.id.seekBar);
        barre.setProgress(4);

        depart = new Point(500,300);
        arrivee = new Point(500,300);

        trait = new TraitContinu(4);
        surf = new Surface(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));

        ec = new Ecouteur();

        for(int i = 0; i <= sectionBas.getChildCount(); i++){
            if(sectionBas.getChildAt(i) instanceof LinearLayout){
                for(int j = 0; j <= ((LinearLayout) sectionBas.getChildAt(i)).getChildCount(); j++){
                    if(((LinearLayout) sectionBas.getChildAt(i)).getChildAt(j) instanceof SeekBar)
                        ((SeekBar) ((LinearLayout) sectionBas.getChildAt(i)).getChildAt(j)).setOnSeekBarChangeListener(ec);
                    else if(((LinearLayout) sectionBas.getChildAt(i)).getChildAt(j) instanceof ImageButton)
                        ((LinearLayout) sectionBas.getChildAt(i)).getChildAt(j).setOnClickListener(ec);
                }
            }
        }
        sectionHaut.addView(surf);
    }

    private class Surface extends View {

        public Surface(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            trait.dessiner(canvas);
            surf.invalidate();
        }
    }

    private class Ecouteur implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

        @Override
        public void onClick(View source) {
            if(source instanceof ImageButton){
                direction = String.valueOf(source.getTag());

                if(direction.equals("haut")){
                    trait.move(depart);
                    arrivee.y -= 20;
                    depart.y -=20;
                    trait.line(arrivee);

                }else if(direction.equals("droite")){
                    trait.move(depart);
                    arrivee.x += 20;
                    depart.x +=20;
                    trait.line(arrivee);

                }else if(direction.equals("bas")){
                    trait.move(depart);
                    arrivee.y += 20;
                    depart.y += 20;
                    trait.line(arrivee);

                }else if(direction.equals("gauche")){
                    trait.move(depart);
                    arrivee.x -= 20;
                    depart.x -= 20;
                    trait.line(arrivee);
                }
            }
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            seekBar.setProgress(progress);
            trait.setLargeur(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

}