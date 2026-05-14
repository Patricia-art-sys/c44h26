package com.lyne.travailfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button onStart;
    ListView hautPointage;
    Ecouteur ec;
    Intent i;
    GestionBD instance;
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
        onStart = findViewById(R.id.boutonDebut);
        hautPointage = findViewById(R.id.hautPointage);

        ec = new Ecouteur();
        onStart.setOnClickListener(ec);
    }
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if(source == onStart){
                i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("info", "Je viens de main activity");
                startActivity(i);
                finish();
            }

        }
    }
}