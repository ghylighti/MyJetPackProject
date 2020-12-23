package com.ghy.myview.activity.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import com.ghy.myview.activity.typeEvaluator.BezierPointFType;

import java.util.Random;

public class LoveView extends RelativeLayout {
    private Random random=new Random();
    private  int viewWidth,viewHeight;
    public LoveView(Context context) {
        this(context,null);
    }

    public LoveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addLoveView()
    {

        Color color=  Color.valueOf(random.nextInt(255),random.nextInt(255),random.nextInt(255));

        final ImageView imageView=new ImageView(getContext());
//        imageView.setImageResource(R.mipmap.ic_launcher_round);
        LayoutParams params=new LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        imageView.setLayoutParams(params);

        imageView.setBackgroundColor(color.toArgb());
        this.addView(imageView);


        StartAnimate(imageView);
    }

    private void StartAnimate(final ImageView imageView) {

        ObjectAnimator objectAnimatorX=new ObjectAnimator();
        objectAnimatorX.setPropertyName("scaleX");
        objectAnimatorX.setDuration(500);
        objectAnimatorX.setTarget(imageView);
        objectAnimatorX.setFloatValues(0.3f,1f);

        ObjectAnimator objectAnimatorY=new ObjectAnimator();
        objectAnimatorY.setPropertyName("scaleY");
        objectAnimatorY.setDuration(500);
        objectAnimatorY.setTarget(imageView);
        objectAnimatorY.setFloatValues(0.3f,1f);


        ObjectAnimator objectAnimatorA=new ObjectAnimator();
        objectAnimatorA.setPropertyName("alpha");
        objectAnimatorA.setDuration(500);
        objectAnimatorA.setTarget(imageView);
        objectAnimatorA.setFloatValues(0.3f,1f);
         AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(objectAnimatorX,objectAnimatorY,objectAnimatorA);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("BezierPointFType","----------start---------");

            }
        });
        AnimatorSet animatorSet1=new AnimatorSet();
        animatorSet1.playSequentially(animatorSet,MoveAnimate(imageView));
        animatorSet1.start();
    }

    private ValueAnimator MoveAnimate(final ImageView iv) {
        Random pRandom=new Random();
        PointF p0=new PointF(getWidth()/2-50,getHeight()-100);
        PointF p1=new PointF(pRandom.nextInt(getWidth()),pRandom.nextInt(getHeight()/2)+getHeight()/2);
        PointF p2=new PointF(pRandom.nextInt(getWidth()),pRandom.nextInt(getHeight()/2));
        PointF p3=new PointF(pRandom.nextInt(getWidth()),0);

        ValueAnimator objectAnimator= ObjectAnimator.ofObject(new BezierPointFType(p1,p2),p0,p3);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
              PointF pointF= (PointF) animation.getAnimatedValue();
                iv.setX(pointF.x);
                iv.setY(pointF.y);
                iv.setAlpha(pointF.y/1000);
            }
        });
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(iv);
            }
        });
        objectAnimator.setDuration(5000);
        return objectAnimator;

    }


}
