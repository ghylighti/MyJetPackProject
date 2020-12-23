package com.ghy.myview.activity.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.ghy.myview.activity.utils.ViewUtils;


public class ClipTextView extends androidx.appcompat.widget.AppCompatTextView {
    private String mText;


    private float progress=0f;
    private Paint leftPaint,rightPaint;
    private Rect rect=new Rect();



    private boolean isLeft; //true表示从左往右
    public ClipTextView(Context context) {
        this(context,null);
    }

    public ClipTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClipTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }


    @SuppressLint("ResourceAsColor")
    void  initView(Context context, AttributeSet attrs)
    {
        mText=getText().toString();
        leftPaint=new Paint();
        leftPaint.setAntiAlias(true);
        leftPaint.setTextSize(getTextSize());
        leftPaint.setColor(Color.BLUE);
        rightPaint=new Paint();
        rightPaint.setAntiAlias(true);
        rightPaint.setTextSize(getTextSize());
        rightPaint.setColor(Color.RED);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public boolean isLeft() {
        return isLeft;
    }
    public void setLeft(boolean left) {
        isLeft = left;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width=getWidth();
        if(isLeft)
        {

            leftPaint.getTextBounds(mText,0,mText.length(),rect);
            drawText(canvas,0, (int) (width*progress),leftPaint);
            rightPaint.getTextBounds(mText,0,mText.length(),rect);
            drawText(canvas, (int) (width*progress),width,rightPaint);
        }else
        {
            rightPaint.getTextBounds(mText,0,mText.length(),rect);
            drawText(canvas,width, (int) (width*(1-progress)),rightPaint);
            leftPaint.getTextBounds(mText,0,mText.length(),rect);
            drawText(canvas,(int) (width*(1-progress)), 0,leftPaint);

        }

    }

    private void drawText(Canvas canvas, int start, int end, Paint paint)
    {

        canvas.save();
        canvas.clipRect(start,0,end,getHeight());
        canvas.drawText(mText,0, ViewUtils.getBaseLine(leftPaint,getHeight()),paint);
        canvas.restore();
    }
}
