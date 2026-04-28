package com.lyne.examen3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Ecouteur ec;
    GestionBD instance;
    ArrayList<String> choix;
    Intent i;

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
        listView = findViewById(R.id.listeBd);

        instance = GestionBD.getInstance(getApplicationContext());
        choix = instance.retournerEquipes();
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choix);
        listView.setAdapter(adapter);

        ec = new Ecouteur();
        listView.setOnItemClickListener(ec);

    }
    public class Ecouteur implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View itemClique, int position, long id) {
            String nomChoisi = choix.get(position);
            i = new Intent(MainActivity.this, MainActivity2.class);
            i.putExtra("name", nomChoisi);
            startActivity(i);
        }
    }
}