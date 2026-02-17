package com.lyne.annexe4b;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    TextView password;
    LinearLayout main;
    String motDePasse1 = "";
    String motDePasse2 = "1237";

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

        password = findViewById(R.id.Password);
        main = findViewById(R.id.main);
        ec = new Ecouteur();

        for(int i = 0; i < main.getChildCount(); i++){
            if(main.getChildAt(i) instanceof LinearLayout){             //exclut les textView
                LinearLayout temp = (LinearLayout) main.getChildAt(i);
                for(int j = 0; j < temp.getChildCount(); j++){
                    if(temp.getChildAt(j) instanceof Button){           //on ne prends que les boutons
                        temp.getChildAt(j).setOnClickListener(ec);      //rajoute un écouteur
                    }
                }
            }
            else if(main.getChildAt(i) instanceof Button){
                main.getChildAt(i).setOnClickListener(ec);
            }
        }

    }
    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source) {

            if(motDePasse1.length() < 4) {
                motDePasse1 += ((Button) source).getText();
                password.setText(motDePasse1);
                if (motDePasse1.length() == 4) {
                    if (motDePasse1.equals(motDePasse2)) {
                        main.setBackgroundColor(Color.GREEN);
                    } else {
                        main.setBackgroundColor(Color.RED);
                        motDePasse1 = "";
                        password.setText(motDePasse1);
                    }
                }
            }

        }
    }


}