package com.LYNE.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Triangle extends Forme {
    private Path p;
    private Point start, end;

    public Triangle(int largeur, int color) {
        super(largeur, color);
        p = new Path();
    }

    public void setStart(Point start) {
        this.start= start;
    }
    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void dessiner(Canvas c) {
        Paint crayon;
        crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setColor(getColor());
        crayon.setStrokeWidth(getLargeur());
        crayon.setStyle(Paint.Style.STROKE);

        c.drawLine((float) (end.x - start.x) /2, start.y, end.x, end.y, crayon);
        c.drawLine((float) (end.x - start.x) /2, start.y, end.x, end.y, crayon);
        c.drawLine(start.x, start.y, end.x, end.y, crayon);


    }



}
