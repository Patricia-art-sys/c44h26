package com.LYNE.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Efface extends Forme {
    private Path p;
    public Efface(int largeur, int color) {
        super(largeur, color);
        p = new Path();
    }

    @Override
    public void dessiner(Canvas c) {
        Paint gomme;
        gomme = new Paint(Paint.ANTI_ALIAS_FLAG);
        gomme.setColor(getColor());
        gomme.setStrokeWidth(getLargeur());
        gomme.setStyle(Paint.Style.STROKE);

        c.drawPath(p, gomme);
    }
    public void addPointsLine(Point c){
        p.lineTo(c.x,c.y);

    }
    public void addPointsMove(Point c){
        p.moveTo(c.x,c.y);
    }
}
