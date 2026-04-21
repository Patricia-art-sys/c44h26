package com.lyne.annexe12;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView texte1, reponse;
    ListView listeBD;
    Ecouteur ec;
    GestionBD instance;
    ArrayList<String> choix;
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

        texte1 = findViewById(R.id.texte);
        reponse = findViewById(R.id.reponse);
        listeBD = findViewById(R.id.listeBd);

        //je crée mon singleton, ne pas mettre this parce qu'on utilise un singleton,
        // possibilité d'avoir plusieurs activitées
        //singleton = référence statique
        instance = GestionBD.getInstance(getApplicationContext());
        System.out.println(instance.retournerInventions().size());

        choix = instance.retournerInventions();
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choix);
        listeBD.setAdapter(adapter);

        ec = new Ecouteur();
        listeBD.setOnItemClickListener(ec);
    }
    public class Ecouteur implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View itemClique, int position, long id) {
            //autres methodes
            //String reponseDonnee = (String) listeBD.getItemAtPosition(position);
            //String reponseDonnee = ((TextView)itemClique).getText().tostring();
            String reponseDonnee = choix.get(position);

            try {
                System.out.println(reponseDonnee);
                if(instance.hasBonneReponse("Mary Anderson", reponseDonnee)){
                    reponse.setText("Bonne réponse !");
                    itemClique.setBackgroundColor(Color.GREEN);
                }else{
                    reponse.setText("Mauvaise réponse !");
                    itemClique.setBackgroundColor(Color.RED);
                    listeBD.getChildAt(instance.trouverIndiceBonneReponse("Mary Anderson")).setBackgroundColor(Color.GREEN);
                }
            }catch (Exception e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            listeBD.setOnItemClickListener(null);


        }
    }
}