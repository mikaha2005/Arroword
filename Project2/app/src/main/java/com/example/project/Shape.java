
package com.example.project;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Shape {
    protected int x;
    protected int y;
    protected int color;

    public Shape(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }


    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(this.color);
    }
}