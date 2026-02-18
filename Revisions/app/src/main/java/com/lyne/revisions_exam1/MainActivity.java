package com.lyne.revisions_exam1;

import static java.lang.Integer.parseInt;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Ecouteur ec;
    ImageButton onMarche;
    ImageButton onNatation;
    ImageButton onDanse;
    Button onEnregistrer;
    ProgressBar objectifBar;
    EditText heureD;
    EditText heureF;
    EditText minutesD;
    EditText minutesF;
    int minutes = 0;
    Exercice exo;
    PlanEnterainement plan;
    String message = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        onMarche = findViewById(R.id.buttonMarche);
        onDanse = findViewById(R.id.buttonDanse);
        onNatation = findViewById(R.id.buttonNage);
        onEnregistrer = findViewById(R.id.buttonEnregistrer);

        heureD = findViewById(R.id.heureDebut);
        heureF = findViewById(R.id.heureFin);
        minutesD = findViewById(R.id.minuteDebut);
        minutesF = findViewById(R.id.minuteFin);

        objectifBar = findViewById(R.id.progressBar);
        ec = new Ecouteur();

        onMarche.setOnClickListener(ec);
        onDanse.setOnClickListener(ec);
        onNatation.setOnClickListener(ec);
        onEnregistrer.setOnClickListener(ec);
        plan = new PlanEnterainement(200);

    }
    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if(source == onMarche){
                exo = new Exercice(minutes);
                exo.setType("MARCHE");

            }else if(source == onDanse){
                exo = new Exercice(minutes);
                exo.setType("DANSE");

            }else if(source == onNatation){
                exo = new Exercice(minutes);
                exo.setType("NATATION");

            }else if(source == onEnregistrer){
               minutes = (  (   (parseInt(heureF.getText().toString()) * 60) + parseInt(minutesF.getText().toString())) -
                       ((parseInt(heureD.getText().toString()) * 60) + parseInt(minutesD.getText().toString()))
               );
               exo.setMinutes(minutes);
               plan.ajouterExercice(exo);


               objectifBar.setMax(plan.getObjectif());
               objectifBar.setProgress(plan.totalMinutes());

               if(objectifBar.getProgress() == plan.getObjectif()){
                   message += "objectif de " + exo.getType() + " atteint !";
                   Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
                   toast.show();
               }else if(parseInt(heureF.getText().toString()) < parseInt(heureD.getText().toString()))
                {
                    message += "Attention à vos heures!";
                    Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
                    toast.show();
                    heureD.setText("");
                    heureF.setText("");
                    minutesD.setText("");
                    minutesF.setText("");
                }
            }
        }
    }
}