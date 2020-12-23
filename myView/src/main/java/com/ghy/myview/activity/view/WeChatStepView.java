package com.ghy.myview.activity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.ghy.myview.R;
import com.ghy.myview.activity.utils.ViewUtils;


public class WeChatStepView extends View {
    private int mInnerColor;
    private int mOuterColor;
    private String mStep;
    private float mTextSize;
    private int mTextColor;
    private int mPaintSiz;
    private float mOuterSiz;
    private Paint paintText,paintOut,paintIn;
    private Rect rect=new Rect();
    private int stepInt=0;
    public WeChatStepView(Context context) {
        this(context,null);
    }

    public WeChatStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeChatStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }


    private void initView(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeChatStepView);
        mStep=typedArray.getString(R.styleable.WeChatStepView_mText);
        mInnerColor=typedArray.getInt(R.styleable.WeChatStepView_innerColor,0);
        mOuterColor=typedArray.getInt(R.styleable.WeChatStepView_outerColor,0);
        mTextSize=typedArray.getDimensionPixelSize(R.styleable.WeChatStepView_mTextSize,0);
        mTextColor=typedArray.getInt(R.styleable.WeChatStepView_mTextColor,0);
        mPaintSiz=typedArray.getInt(R.styleable.WeChatStepView_paintSize,0);
        mOuterSiz=typedArray.getInt(R.styleable.WeChatStepView_paintOuterSize,0);
        typedArray.recycle();
        Log.i("WechateView",mStep+"  "+"  "+mPaintSiz);
        //初始化画笔 text
        paintText=new Paint();
        paintText.setTextSize(mTextSize);
        paintText.setColor(mTextColor);

        paintText.setAntiAlias(true);
        //outer画笔
        paintOut=new Paint();
        paintOut.setAntiAlias(true);
        paintOut.setStrokeCap(Paint.Cap.ROUND);
        paintOut.setColor(mOuterColor);
        paintOut.setStrokeWidth(mOuterSiz);
        paintOut.setStyle(Paint.Style.STROKE);
        //inner画笔
        paintIn=new Paint();
        paintIn.setAntiAlias(true);
        paintIn.setStrokeCap(Paint.Cap.ROUND);
        paintIn.setStyle(Paint.Style.STROKE);
        paintIn.setStrokeWidth(mOuterSiz);
        paintIn.setColor(mInnerColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       super.onMeasure(widthMeasureSpec,heightMeasureSpec);
       int widthMode= MeasureSpec.getMode(widthMeasureSpec);
       int heightMode= MeasureSpec.getMode(heightMeasureSpec);
       int width= MeasureSpec.getSize(widthMeasureSpec);
       int height= MeasureSpec.getSize(heightMeasureSpec);
        paintText.getTextBounds(mStep,0,mStep.length(),rect);
       if(widthMode== MeasureSpec.AT_MOST)
       {
           width=rect.width();
       }
        if(heightMode== MeasureSpec.AT_MOST)
        {
            height=rect.height();
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(mStep+"",getWidth()/2-rect.width()/2, ViewUtils.getBaseLine(paintText,getHeight()),paintText);
        int off= (int) (paintOut.getStrokeWidth());
        RectF rectF= new RectF(0+off,0+off,getWidth()-off,getHeight()-off);
        canvas.drawArc(rectF,135,270,false,paintOut);
        canvas.drawArc(rectF,135,270*stepInt/ Integer.parseInt(mStep),false,paintIn);
    }

    public void setmStep(int step)
    {
        stepInt=step;
    }

    private void drawInner()
    {
        invalidate();
    }
}
