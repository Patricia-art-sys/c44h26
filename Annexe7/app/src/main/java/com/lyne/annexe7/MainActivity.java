package com.lyne.annexe7;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.graphics.PaintCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SurfaceDessin surf;
    ConstraintLayout main;
    Ecouteur ec;

    Point depart, arrivee;

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
        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        surf.setBackgroundResource(R.drawable.carte);

        main.addView(surf);

        //gestion d'évènement
        //étape 1
        ec = new Ecouteur();

        //etape 2
        surf.setOnTouchListener(ec);


    }
    private class SurfaceDessin extends View{
        Paint crayon;
        public SurfaceDessin(Context context) {
            super(context);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.RED);
            crayon.setStrokeWidth(15);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            if(depart != null){
                canvas.drawRect(depart.x-25,depart.y-25,depart.x+25, depart.y+25, crayon);
            }
            if(depart != null && arrivee != null){
                canvas.drawLine(depart.x,depart.y,arrivee.x,arrivee.y,crayon);
                canvas.drawRect(arrivee.x-25,arrivee.y-25,arrivee.x+25, arrivee.y+25, crayon);
            }

        }
    }
    private class Ecouteur implements View.OnTouchListener{

        @Override
        public boolean onTouch(View source, MotionEvent event) {

            if(event.getAction() == ACTION_DOWN){
                depart = new Point((int)event.getX(), (int)event.getY());
                surf.invalidate();
            }else if(event.getAction() == ACTION_MOVE){
                arrivee = new Point((int)event.getX(), (int)event.getY());
                surf.invalidate();
            }else if(event.getAction() == ACTION_UP){
                surf.invalidate();
            }

            return true;
        }
    }
}