package com.LYNE.tp1;

import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseUnsignedInt;

import android.content.Context;
import android.graphics.Bitmap;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Layouts principaux
    ConstraintLayout parent;
    LinearLayout couleurs;
    LinearLayout outils;
    ChipGroup groupCouleurs;

    //gestion d'évènements
    Ecouteur ec;

    //surface de dessin
    Surface s;
    Point depart, arrivee;

    // Dialogue pour choisir largeur du trait
    DialogLargeurTrait dialog;

    //paramètres de dessin
    int color = Color.WHITE;
    int largeur = 10;
    int couleurFond = Color.parseColor("#FAF0E6");
    String control = "crayon";
    Bitmap bitmap;


    public void changerLargeur(int l){ this.largeur = l;}

    Forme tracer;
    ArrayList<Forme>  temp;
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
        groupCouleurs = findViewById(R.id.Group);
        outils = findViewById(R.id.outils);
        parent = findViewById(R.id.parent);

        //Inistialisation des listes les objets
        listDeForme = new ArrayList<>();
        temp = new ArrayList<>();
        dialog = new DialogLargeurTrait(this);

        ec = new Ecouteur();
        s = new Surface(this);
        s.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));

        //Associer les couleurs à l'écouteur
        for(int i = 0; i < groupCouleurs.getChildCount(); i++){
            if(groupCouleurs.getChildAt(i) instanceof Chip){
                groupCouleurs.getChildAt(i).setOnClickListener(ec);
            }
        }

        //Associer les outils à l'écouteur
        for(int i = 0; i <= outils.getChildCount(); i++){
            if(outils.getChildAt(i) instanceof ImageButton){
                outils.getChildAt(i).setOnClickListener(ec);
            }
        }

        s.setOnTouchListener(ec);
        parent.addView(s);

    }

    // ================= SURFACE DE DESSIN =================
    private class Surface extends View {

        public Surface(Context context) {
            super(context);
        }

        // Capture la vue en bitmap (utile pour la pipette)
        public Bitmap getBitmapImage(){
            this.buildDrawingCache();
            bitmap = Bitmap.createBitmap(this.getDrawingCache());
            this.destroyDrawingCache();
            return bitmap;
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            // Dessiner la forme en cours
            if(tracer != null)
                tracer.dessiner(canvas);

            // Dessiner toutes les formes sauvegardées
            if(listDeForme != null){
                System.out.println(listDeForme.size());
                for(Forme f : listDeForme){
                    if(f instanceof Efface) {
                        f.setColor(couleurFond);
                    }
                    f.dessiner(canvas);
                }
            }
        }
    }

    // ================= GESTION DES ÉVÉNEMENTS =================
    private class Ecouteur implements View.OnClickListener, View.OnTouchListener{
        Bitmap img;
        @Override
        public void onClick(View source) {

            //choix de couleur
            if(source instanceof Chip){
                color = Color.parseColor(String.valueOf(source.getTag()));
            }
            //Choix d'outil
            else if(source instanceof ImageButton){
                control = (String) source.getTag();
                if(control.equals("remplissage")){
                    // Change la couleur de fond
                    couleurFond = color;
                    s.setBackgroundColor(couleurFond);

                }else if(control.equals("undo")){
                    // Annuler dernière forme
                    if(!listDeForme.isEmpty()){
                       Forme last = listDeForme.remove(listDeForme.size()-1);
                       temp.add(last);
                       s.invalidate();
                       tracer = null;
                    }
                }else if(control.equals("redo")){
                    // Restaurer une forme annulée
                    if(temp != null && !temp.isEmpty()){
                        Forme redo = temp.remove(temp.size()-1);
                        listDeForme.add(redo);
                        s.invalidate();
                    }
                }else if(control.equals("largeurTrait")){
                    control = "crayon";
                    dialog.show();
                }
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                depart = new Point((int)event.getX(), (int)event.getY());

                //Création la forme selon l'outil
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

                }else if(control.equals("pipette")){
                    // Récupérer la couleur du pixel
                    img = s.getBitmapImage();
                    int couleurChoisie = img.getPixel(depart.x, depart.y);
                    color = couleurChoisie;

                    control = "crayon";
                    tracer = new TraceLibre(largeur, color);
                }

                // Initialisation de la forme
                if(tracer instanceof TraceLibre) {
                    ((TraceLibre) tracer).addPointsMove(depart);

                }else if(tracer instanceof Cercle){
                     ((Cercle) tracer).setDepart(depart);

                }else if(tracer instanceof Rectangle){
                    ((Rectangle) tracer).setDepart(depart);

                }else if(tracer instanceof Efface){
                    ((Efface) tracer).addPointsMove(depart);

                }else if(tracer instanceof Triangle){
                    ((Triangle) tracer).setStart(depart);
                    ((Triangle) tracer).setEnd(depart);

                }
            }
            else if(event.getAction() == ACTION_MOVE){
                arrivee = new Point((int)event.getX(), (int)event.getY());

                if(tracer instanceof TraceLibre) {
                    ((TraceLibre) tracer).addPointsLine(arrivee);
                }
                else if(tracer instanceof Cercle){
                    ((Cercle) tracer).setArrivee(arrivee);

                }else if(tracer instanceof Rectangle){
                    ((Rectangle) tracer).setArrivee(arrivee);

                }else if(tracer instanceof Efface){
                    ((Efface) tracer).addPointsLine(arrivee);

                }else if(tracer instanceof Triangle){
                    ((Triangle) tracer).setEnd(arrivee);
                }

                //redessiner la vue
                s.invalidate();

            }else if(event.getAction() == ACTION_UP){
                listDeForme.add(tracer);
                depart = null;
                arrivee = null;
                tracer = null;
            }
            return true;
        }
    }

}