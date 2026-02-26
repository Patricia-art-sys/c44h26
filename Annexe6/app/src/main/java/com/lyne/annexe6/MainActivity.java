package com.lyne.annexe6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SurfaceDessin surf;
    ConstraintLayout main;
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
        main = findViewById(R.id.main);
        //1
        surf = new SurfaceDessin(this);
        //2
        //surf.setLayoutParams(new  ConstraintLayout.LayoutParams(concertirDpEnPx(200), concertirDpEnPx(200))); //prends toute la place
        // ou bien on met :
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        // etape 3
        main.addView(surf);
    }
    // convertir les dp en public
    public int concertirDpEnPx (int dp){
        float densite = this.getResources().getDisplayMetrics().density; //getdisplaymetrics pour avoir le width et le height de l'ecran
        return Math.round(densite * dp);
    }


    private class SurfaceDessin extends View {
        Paint crayon;
        public SurfaceDessin(Context context) {
            super(context);
            this.setBackgroundColor(Color.CYAN);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);

        }
        // la methode est rappelé automatiquement quand on instancie la surface de dessin
        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            crayon.setColor(Color.GREEN);
            canvas.drawCircle(100,100,80,crayon);

            crayon.setStyle(Paint.Style.STROKE);
            crayon.setStrokeWidth(5);
            crayon.setColor(Color.YELLOW);
            canvas.drawCircle(280,100,80,crayon);

            crayon.setStyle(Paint.Style.FILL);
            crayon.setColor(Color.RED);
            canvas.drawArc(390,200,500, 300,0,120,true,crayon);

            crayon.setStyle(Paint.Style.FILL);
            crayon.setColor(Color.YELLOW);
            canvas.drawArc(390,200,500, 300,120,120,true,crayon);

            crayon.setStyle(Paint.Style.FILL);
            crayon.setColor(Color.BLUE);

            canvas.drawArc(390,200,500, 300,240,120,true,crayon);
        }
    }
}