package com.example.thuchanh2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Spinner;

@SuppressLint("AppCompatCustomView")
public class CustomSpinner extends Spinner {
    private int spinnerColor;
    private Paint paint;

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomSpinner, 0, 0);
        try {
            spinnerColor = a.getColor(R.styleable.CustomSpinner_spinnerColor, Color.BLACK);
        } finally {
            a.recycle();
        }
        paint.setColor(spinnerColor);
    }
    CustomSpinner spinner = findViewById(R.id.customSpinner);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(spinnerColor); // Tô màu nền spinner
    }
}

