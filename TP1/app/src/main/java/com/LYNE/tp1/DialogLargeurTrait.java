package com.LYNE.tp1;

import static java.lang.Integer.parseInt;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class DialogLargeurTrait extends Dialog {
    MainActivity m;
    SeekBar onSeek;
    Button onEntrer;
    TextView taille;
    Ecouteur ec;
    public DialogLargeurTrait(@NonNull Context context) {
        super(context);
        m = (MainActivity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogtaille);
        onSeek = findViewById(R.id.seekBar);
        onEntrer = findViewById(R.id.entrer);
        taille = findViewById(R.id.taille);

        ec = new Ecouteur();

        onEntrer.setOnClickListener(ec);
        onSeek.setOnSeekBarChangeListener(ec);

    }
    private class Ecouteur implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{
        @Override
        public void onClick(View source){
            if(source == onEntrer){
                System.out.println(m.largeur);
                dismiss();
            }

        }
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            taille.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
//            Toast.makeText(m, "Started tracking", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
//            Toast.makeText(m, "Stopped at: " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            m.changerLargeur(seekBar.getProgress());
        }
    }
}
