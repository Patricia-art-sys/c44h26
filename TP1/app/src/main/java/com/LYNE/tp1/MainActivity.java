package com.LYNE.tp1;

import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseUnsignedInt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Trace;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout parent;
    LinearLayout couleurs;
    LinearLayout outils;
    Ecouteur ec;
    Surface s;
    Point depart, arrivee;
    int color;
    int largeur = 10;
    int couleurFond;
    String control;

    public int getCouleurFond() {
        return couleurFond;
    }

    Forme tracer;
    TraceLibre t;
    ArrayList<Forme> listDeForme;

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

        couleurs = findViewById(R.id.couleurs);
        outils = findViewById(R.id.outils);
        parent = findViewById(R.id.parent);
        listDeForme = new ArrayList<>();

        ec = new Ecouteur();
        s = new Surface(this);
        s.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));

        for(int i = 0; i <= couleurs.getChildCount(); i++){
            if(couleurs.getChildAt(i) instanceof Button){
                couleurs.getChildAt(i).setOnClickListener(ec);
            }
        }

        for(int i = 0; i <= outils.getChildCount(); i++){
            if(outils.getChildAt(i) instanceof ImageButton){
                outils.getChildAt(i).setOnClickListener(ec);
            }
        }

        s.setOnTouchListener(ec);

        parent.addView(s);

    }

    private class Surface extends View {

        public Surface(Context context) {
            super(context);
            couleurFond = Color.parseColor("#FAF0E6");
            this.setBackgroundColor(couleurFond);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            if(tracer != null)
                tracer.dessiner(canvas);

            if(listDeForme != null){
                for(Forme f : listDeForme)
                    f.dessiner(canvas);
            }


        }
    }
    private class Ecouteur implements View.OnClickListener, View.OnTouchListener{

        @Override
        public void onClick(View source) {

            if(source instanceof Button){
                System.out.println("couleur");
                color = Color.parseColor(String.valueOf(source.getTag()));


            }else if(source instanceof ImageButton){
                control = (String) source.getTag();
            }

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                depart = new Point((int)event.getX(), (int)event.getY());

                if(control.equals("crayon")){
                    tracer = new TraceLibre(largeur, color);
                }else if(control.equals("cercle")){
                    tracer = new Cercle(largeur, color);

                }else if(control.equals("efface")){
                    tracer = new Efface(largeur+10, couleurFond);

                }else if(control.equals("triangle")){
                    tracer = new Triangle(largeur, color);

                }else if(control.equals("rectangle")){
                    tracer = new Rectangle(largeur, color);

                }else if()

                if(tracer instanceof TraceLibre) {
                    ((TraceLibre) tracer).addPointsMove(depart);

                }else if(tracer instanceof Cercle){
                     ((Cercle) tracer).setDepart(depart);

                }else if(tracer instanceof Rectangle){
                    ((Rectangle) tracer).setDepart(depart);

                }else if(tracer instanceof Efface){
                    ((Efface) tracer).addPointsMove(depart);
                }

            }else if(event.getAction() == ACTION_MOVE){
                arrivee = new Point((int)event.getX(), (int)event.getY());

                if(tracer instanceof TraceLibre)
                    ((TraceLibre)tracer).addPointsLine(arrivee);

                else if(tracer instanceof Cercle){
                    ((Cercle) tracer).setArrivee(arrivee);

                }else if(tracer instanceof Rectangle){
                    ((Rectangle) tracer).setArrivee(arrivee);

                }else if(tracer instanceof Efface){
                    ((Efface) tracer).addPointsLine(arrivee);
                }
                s.invalidate();

            }else if(event.getAction() == ACTION_UP){
                listDeForme.add(tracer);
                depart = null;
                arrivee = null;
            }
            return true;
        }
    }

}