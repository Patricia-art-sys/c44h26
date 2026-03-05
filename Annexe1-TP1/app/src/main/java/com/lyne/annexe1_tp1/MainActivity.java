package com.lyne.annexe1_tp1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout parent;
    Button onEnter;
    TextView texteX, texteY;
    Surface s;
    Ecouteur ec;
    Path chemin;
    int x, y;

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

        parent = findViewById(R.id.parent);
        onEnter = findViewById(R.id.button);
        texteX = findViewById(R.id.texteX);
        texteY = findViewById(R.id.texteY);

        s = new Surface(this);
        s.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        parent.addView(s);

        ec = new Ecouteur();
        onEnter.setOnClickListener(ec);
        chemin = new Path();
    }
    private class Ecouteur implements  View.OnClickListener{

        @Override
        public void onClick(View source) {
            x = Integer.parseInt(texteX.getText().toString());
            y = Integer.parseInt(texteY.getText().toString());

            if(chemin.isEmpty()){
                chemin.moveTo(x,y);
            }else
                chemin.lineTo(x, y);

            s.invalidate(); //redessiner...
        }
    }
    private class Surface extends View {
        Paint crayon;

        public Surface(Context context){
            super(context);
            setBackgroundColor(Color.MAGENTA);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setStyle(Paint.Style.STROKE);
            crayon.setStrokeWidth(10);
            crayon.setColor(Color.BLACK);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(chemin, crayon);

        }
    }

}