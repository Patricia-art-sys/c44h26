package com.example.appexamenetch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class TraitContinu {
    private Path p;
    private int largeur;

    public TraitContinu(int largeur) {
        this.p = new Path();
        this.largeur = largeur;
    }

    public void dessiner(Canvas c){
        Paint crayon;
        crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setStyle(Paint.Style.STROKE);
        crayon.setStrokeWidth(getLargeur());

        c.drawPath(p, crayon);
    }
    public void move(Point chemin){
        p.moveTo(chemin.x, chemin.y);
    }
    public void line(Point chemin){
        p.lineTo(chemin.x, chemin.y);
    }

    public int getLargeur() {
        return largeur;
    }
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }


}
