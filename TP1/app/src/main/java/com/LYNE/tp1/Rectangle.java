package com.LYNE.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Rectangle extends Forme {

    private Point depart, arrivee;
    public Rectangle(int largeur, int color) {
        super(largeur, color);
    }

    @Override
    public void dessiner(Canvas c) {
        Paint crayon;
        crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setColor(getColor());
        crayon.setStrokeWidth(getLargeur());
        crayon.setStyle(Paint.Style.STROKE);

        c.drawRect(depart.x, depart.y, arrivee.x, arrivee.y, crayon);
    }

    public void setDepart(Point depart) {
        this.depart = depart;
    }

    public void setArrivee(Point arrivee) {
        this.arrivee = arrivee;
    }
}
