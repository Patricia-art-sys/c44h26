package com.LYNE.tp1;

import android.graphics.Canvas;

public abstract class Forme {
    int largeur;
    int color;

    public Forme(int largeur, int color) {
        this.largeur = largeur;
        this.color = color;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getColor() {
        return color;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public abstract void dessiner(Canvas c);
}
