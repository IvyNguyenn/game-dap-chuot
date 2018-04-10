package com.example.ivyng.demoanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class GraphicView extends View {
    Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Random rand;
    int minX= -200,maxX=430;
    int minY= -200,maxY=650;


    public GraphicView(Context context) {
        this(context, null);
    }

    public GraphicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraphicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        rand=new Random();
        mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint1.setColor(Color.GREEN);
        mPaint1.setStrokeWidth(30);
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(Color.RED);
        mPaint2.setStrokeWidth(30);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Draw component in here

        canvas.drawPoint(100,200,mPaint1);
        canvas.drawCircle(rand.nextInt(maxX-minX+1)+minX,
                rand.nextInt(maxX-minX+1)+minX,100,mPaint1);
        canvas.drawCircle(rand.nextInt(maxX-minX+1)+minX,
                rand.nextInt(maxX-minX+1)+minX,100,mPaint2);

    }
}
