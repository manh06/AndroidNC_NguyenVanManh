package com.example.thuchanh2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ClockViewGroup extends ViewGroup {
    private Paint paint;

    public ClockViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ClockViewGroup clockView = findViewById(R.id.clockViewGroup);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childWidth = getWidth() / 3;
        int childHeight = getHeight() / 3;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int row = i / 3;
            int col = i % 3;
            int leftPos = col * childWidth;
            int topPos = row * childHeight;
            child.layout(leftPos, topPos, leftPos + childWidth, topPos + childHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Vẽ mặt đồng hồ
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, getWidth() / 2f - 20f, paint);

        // Vẽ kim phút và kim giờ
        paint.setColor(Color.RED);
        canvas.drawLine(getWidth() / 2f, getHeight() / 2f, getWidth() / 2f, 100f, paint); // Kim phút
        paint.setColor(Color.BLUE);
        canvas.drawLine(getWidth() / 2f, getHeight() / 2f, 100f, getHeight() / 2f, paint); // Kim giờ
    }
}

