package com.LYNE.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Cercle extends Forme {
    private Point depart, arrivee;
    private float rayon;
    public Cercle(int largeur, int color) {
        super(largeur, color);
    }

    @Override
    public void dessiner(Canvas c) {
        Paint cercle;
        cercle = new Paint(Paint.ANTI_ALIAS_FLAG);
        cercle.setColor(color);
        cercle.setStrokeWidth(largeur);
        cercle.setStyle(Paint.Style.STROKE);

        c.drawCircle(depart.x,depart.y,calculeRayon(),cercle);
    }

    public float calculeRayon(){
        return rayon = (float) Math.sqrt(Math.pow(arrivee.x - depart.x,2) + Math.pow(arrivee.y - depart.y,2));
    }
    public void setDepart(Point depart) {
        this.depart = depart;
    }

    public void setArrivee(Point arrivee) {
        this.arrivee = arrivee;
    }

}
