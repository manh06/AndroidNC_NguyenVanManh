package com.example.thuchanh2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomProgressBar extends View {
    private int progress = 0;
    private Paint paint;

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        CustomProgressBar progressBar = findViewById(R.id.customProgressBar);
        progressBar.setProgress(70); // Đặt tiến trình 70%

    }

    public void setProgress(int value) {
        progress = value;
        invalidate(); // Yêu cầu vẽ lại view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float widthProgress = getWidth() * (progress / 100f);
        canvas.drawRect(0, 0, widthProgress, getHeight(), paint);
    }
}
