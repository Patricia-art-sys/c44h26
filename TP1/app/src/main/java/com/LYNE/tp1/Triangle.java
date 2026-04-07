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

        //points du triangles
        float x1 = start.x;
        float y1 = end.y;

        float x2 = end.x;
        float y2 = end.y;

        float x3 = (float) (start.x + end.x) / 2;
        float y3 = start.y;

        c.drawLine(x1, y1, x2, y2, crayon);
        c.drawLine(x1, y1, x3, y3, crayon);
        c.drawLine(x2, y2, x3, y3, crayon);

    }



}
