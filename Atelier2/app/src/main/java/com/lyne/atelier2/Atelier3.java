package com.lyne.atelier2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Atelier3 extends ConstraintLayout {

    private TextView textView, textView2, textView3;
    public Atelier3(@NonNull Context context) {
        super(context);
        init(context);
    }

    public Atelier3(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Atelier3(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //init = oncreate pour les composantes qui ne sont pas des activités
    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.atelier3, this, true);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

    }

    public TextView getTextView() {
        return textView;
    }

    public TextView getTextView2() {
        return textView2;
    }

    public TextView getTextView3() {
        return textView3;
    }

}
