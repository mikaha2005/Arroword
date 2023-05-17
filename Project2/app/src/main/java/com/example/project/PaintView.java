/*package com.example.project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

public class PaintView extends View {

    private Paint[][] boxes;
    private Paint fgPaint;
    private Square s;
    private String myBoard[][];
    private TextPaint textPaint;

    public PaintView(Context context) {
        super(context);
        boxes = new Paint[18][8];
        myBoard = new String[18][8];
        textPaint=new TextPaint();
        textPaint.setColor(Color.rgb(0,0,0));
        textPaint.setTextSize(60);
        textPaint.setAntiAlias(true);
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j]=new Paint();
                boxes[i][j].setStyle(Paint.Style.STROKE);
                boxes[i][j].setColor(Color.rgb(0, 0, 0));
            }
        }
        fgPaint = new Paint();

        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setColor(Color.rgb(255,255,255));
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(fgPaint);
        myBoard=MainActivity.getBoard();
        int width = 100;
//        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
//        float spacingMultiplier = 5;
//        float spacingAddition = 100;
//        boolean includePadding = true;


        //float height = myStaticLayout.getHeight();
        for(int i=0; i<18; i++)
            for(int j=0; j<8; j++) {
                s= new Square(100+(j*140),100+(i*140),boxes[i][j].getColor());
                s.draw(canvas, fgPaint);
                if(myBoard[i][j]!=null) {
                   // StaticLayout staticLayout = StaticLayout.Builder.obtain(myBoard[i][j], 100 + (j * 100), 124 + (i * 100), textPaint, 100).build();
                    canvas.drawText(myBoard[i][j], 100 + (j * 100), 124 + (i * 100), textPaint);
                   // StaticLayout staticLayout = StaticLayout.Builder.obtain(myBoard[i][j], 100 , 130, textPaint, 100).build();
                   // StaticLayout staticLayout = new StaticLayout(myBoard[i][j], textPaint, width, alignment, spacingMultiplier, spacingAddition, includePadding);
                   // staticLayout.draw(canvas);

                }

            }
    }

}*/
