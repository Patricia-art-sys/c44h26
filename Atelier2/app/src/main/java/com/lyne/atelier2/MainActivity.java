package com.lyne.atelier2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    View view;
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
        view  = findViewById(R.id.view);
        MonTimer m = new MonTimer();
        m.start();
    }
    private class MonTimer extends CountDownTimer{
        int r, v, b;
        //millisInFuture : à chaque 20 secondes on va dans le onfinish
        //countDownInterval: à chaque 2 secondes on va dans le ontick
        public MonTimer() {
            super(2000, 200);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            r += 20;
            v += 8;
            b += 14;
            view.setBackgroundColor(Color.rgb(r, v, b));
        }

        @Override
        public void onFinish() {

        }
    }
}