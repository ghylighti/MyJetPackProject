package com.ghy.componentproject.View;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MyBottomBehavior<V extends View> extends HideBottomViewOnScrollBehavior<V> implements Runnable{
    private Handler handler=new Handler();
    public MyBottomBehavior() {
        Looper.prepare();
        Looper.loop();

    }

    public MyBottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    private V mChild;
    private boolean isEnd;
    private int mDyConsumed;

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
        mChild=child;
        isEnd=true;
        if(isEnd)
        {
            handler.postDelayed(this,mDyConsumed*7);
        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        isEnd=false;
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, @NonNull V child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        isEnd=false;
        mDyConsumed=dyConsumed;
    }

    @Override
    public void run() {
        slideUp(mChild);
    }
}
