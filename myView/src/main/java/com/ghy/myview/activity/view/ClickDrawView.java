package com.ghy.myview.activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import com.ghy.myview.activity.utils.ViewUtils;

import java.util.Random;

public class ClickDrawView extends View {
    private int radius=200;
    private PointF clickPointF;
    private Paint mPatin=new Paint();
    public ClickDrawView(Context context) {
        this(context,null);
    }

    public ClickDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClickDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPatin.setAntiAlias(true);
        mPatin.setColor(Color.BLACK);
        mPatin.setStrokeWidth(10);
        mPatin.setStyle(Paint.Style.STROKE);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(clickPointF==null)
                {
                    clickPointF=new PointF();
                }
                clickPointF.y=event.getY();
                clickPointF.x=event.getX();
                Log.i("ClickDraw",clickPointF.x+"   "+clickPointF.y);
                invalidate();
                break;
        }
        return true;
    }
    private Random random=new Random();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(clickPointF!=null)
        {
            canvas.drawColor(Color.WHITE);
            RectF oval=new RectF();
            oval.bottom=(int)clickPointF.y+radius;
            oval.top=(int)clickPointF.y-radius;
            oval.left=(int)clickPointF.x-radius;
            oval.right=(int)clickPointF.x+radius;


            canvas.drawRect(oval,mPatin);
            canvas.save();


            int star=0;
            int sweep=60;
            int count=360/sweep;
            Color color;
            for (int i=0;i<count;i++)
            {
                color=  Color.valueOf(random.nextInt(255),random.nextInt(255),random.nextInt(255));
                mPatin.setColor(color.toArgb());

                star=i*60;

                canvas.drawArc(oval,star,sweep,false,mPatin);

                canvas.drawCircle(ViewUtils.getOnCircleX(clickPointF.x,radius,star), ViewUtils.getOnCircleY(clickPointF.y,radius,star),radius/3,mPatin);


            }






        }

    }
}
