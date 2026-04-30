package com.lyne.annexe14;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout main;
    Ecouteur ec;
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
        main = findViewById(R.id.main);
        ec = new Ecouteur();

        //j'ajoute les sources à l'écouteur
        for(int i = 0; i < main.getChildCount(); i++){
            LinearLayout enfant = (LinearLayout) main.getChildAt(i);
            //les conteneurs ont le dragListener
            enfant.setOnDragListener(ec);
            //les jetons ont le touchListener
            enfant.getChildAt(0).setOnTouchListener(ec);
        }
    }
    public class Ecouteur implements View.OnDragListener, View.OnTouchListener{
        Drawable normal = getResources().getDrawable(R.drawable.background_contenant, null);
        Drawable selectionne = getResources().getDrawable(R.drawable.background_contenant_selectionne, null);
        View jeton = null;
        @Override
        public boolean onDrag(View source, DragEvent event) {
            switch (event.getAction())
            {
                case DragEvent.ACTION_DRAG_ENTERED:
                    source.setBackground(selectionne);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    source.setBackground(normal);
                    break;

                case DragEvent.ACTION_DROP:
                    //il s'agit du jeton qu'on a transmis dans l'ombre quand on drag, lié au localstate dans le ontouch
                    jeton = (View) event.getLocalState();
                    //supprimer le jeton resté invisible, trouver d'abord le conteneur d'origine du jeton
                    LinearLayout parent = (LinearLayout) jeton.getParent();
                    parent.removeView(jeton);

                    //chercher la colonne ou déposer le jeton
                    LinearLayout destination = (LinearLayout) source;
                    //on ajoute le jeton dans la colonne de destination
                    destination.addView(jeton);
                    jeton.setVisibility(VISIBLE);//On le rend visible à nouveau
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    source.setBackground(normal);
                    break;
            }
            return true;
        }

        @Override
        public boolean onTouch(View source, MotionEvent event) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source);

            //démarrer le processus de drag and drop
            //data: transporter des informations
            source.startDragAndDrop(null, shadowBuilder, source, 0);
            //cacher la source temporairement pour deplacer son ombre
            source.setVisibility(INVISIBLE);
            return true;
        }
    }
}