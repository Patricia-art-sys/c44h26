package com.LYNE.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class TraceLibre extends Forme {
    private Path p;
    public TraceLibre(int largeur, int color) {
        super(largeur, color);
        p = new Path();
    }

    public Path getP() {
        return p;
    }

    @Override
    public void dessiner(Canvas c) {
        Paint crayon;
        crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setColor(getColor());
        crayon.setStrokeWidth(getLargeur());
        crayon.setStyle(Paint.Style.STROKE);

        c.drawPath(p, crayon);
    }

    public void addPointsLine(Point chemin){
        p.lineTo(chemin.x,chemin.y);

    }
    public void addPointsMove(Point chemin){
        p.moveTo(chemin.x,chemin.y);
    }


}
