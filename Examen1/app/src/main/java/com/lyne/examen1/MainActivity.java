package com.lyne.examen1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Ecouteur ec;
    Button onService;
    Button onEnregistrer;
    EditText matriculeEtu;
    EditText nbreServices;
    TextView nbreEtu;
    TextView meilleurEtu;
    Evaluation ev;
    Groupe group;
    int nombre = 0;

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
        onEnregistrer = findViewById(R.id.buttonEnregistrer);
        onService = findViewById(R.id.buttonServices);

        matriculeEtu = findViewById(R.id.matriculeEdit);
        nbreServices = findViewById(R.id.servicesEdit);

        nbreEtu = findViewById(R.id.nbreEtudiants);
        meilleurEtu = findViewById(R.id.meilleurMat);

        ec = new Ecouteur();

        onService.setOnClickListener(ec);
        onEnregistrer.setOnClickListener(ec);
        group = new Groupe();
    }
    public  class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if(source == onService){
                nombre++;
                nbreServices.setText(String.valueOf(nombre));
            }else if(source == onEnregistrer){
                String mat = "";
                mat += matriculeEtu.getText();
                if(mat.length() == 7){
                    ev = new Evaluation(mat, nombre);

                    group.ajouterEvaluation(ev);
                    nbreEtu.setText(String.valueOf(group.nombreEvaluation()));
                    meilleurEtu.setText(group.meilleureEvaluation());

                    Toast toast = Toast.makeText(MainActivity.this, "Evaluation enregistrée!", Toast.LENGTH_LONG);
                    toast.show();

                    matriculeEtu.setText("");
                    nbreServices.setText("");
                    nombre = 0;
                }else{
                    Toast toast = Toast.makeText(MainActivity.this, "Matricule invalide!", Toast.LENGTH_LONG);
                    toast.show();
                    matriculeEtu.setText("");
                }
            }
        }
    }
}