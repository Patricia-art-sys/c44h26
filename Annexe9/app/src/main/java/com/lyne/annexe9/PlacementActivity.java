package com.lyne.annexe9;



import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PlacementActivity extends AppCompatActivity {

    private EditText champMontant;
    private NumberPicker numberPicker;
    private TextView labelReponse;
    private Button bouton;
    private Ecouteur ec;





    public DecimalFormat d = new DecimalFormat("0.00$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        champMontant =  findViewById(R.id.champMontant);
        numberPicker = findViewById(R.id.numberPicker);
        labelReponse =  findViewById(R.id.labelReponse);
        bouton = findViewById(R.id.bouton);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 12;
                return "" + temp;
            }
        };


        numberPicker.setFormatter(formatter);
        
        // 3 étapes

        ec = new Ecouteur();

        bouton.setOnClickListener(ec);
    }


    //pour créer une boite de dialogue simple
    public void creerAlertDialog(String message) {


        AlertDialog.Builder builder = new AlertDialog.Builder(PlacementActivity.this);

        //on peut faire ca !!
        builder.setMessage(message)
                .setTitle("Erreur");


        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //bloc de code susceptible de lancer un NumberFormatException (non-contrôlé)
            try {
                 //créer un objet Placement
                Placement p = new Placement(parseDouble(String.valueOf(champMontant.getText())), numberPicker.getValue()*12);
                //afficher le montant final
                labelReponse.setText(d.format(p.calculerMontantFinal()));
            }
            catch (NumberFormatException nfe){
                creerAlertDialog("Montant invalide... Recommencez");
                champMontant.setText("");
                champMontant.setHint("ex.: 1000");
                champMontant.requestFocus(); //place le curseur sur le champ100
            }
            finally {

            }



        }
    }
}








