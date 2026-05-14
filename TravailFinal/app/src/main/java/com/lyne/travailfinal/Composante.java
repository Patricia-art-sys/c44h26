package com.lyne.travailfinal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Composante extends LinearLayout {
    private TextView multiplicateur, lettre, pointage;
    public Composante(Context context) {
        super(context);
        init(context);
    }

    public Composante(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Composante(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.composante, this, true);
        multiplicateur = findViewById(R.id.multiplicateur);
        lettre = findViewById(R.id.lettre);
        pointage = findViewById(R.id.pointage);
    }

    public TextView getMultiplicateur() {
        return multiplicateur;
    }

    public void setMultiplicateur(TextView multiplicateur) {
        this.multiplicateur = multiplicateur;
    }

    public TextView getLettre() {
        return lettre;
    }

    public void setLettre(TextView lettre) {
        this.lettre = lettre;
    }

    public TextView getPointage() {
        return pointage;
    }

    public void setPointage(TextView pointage) {
        this.pointage = pointage;
    }
}
