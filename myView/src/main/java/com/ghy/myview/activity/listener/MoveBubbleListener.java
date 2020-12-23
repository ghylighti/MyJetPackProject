package com.ghy.myview.activity.listener;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ghy.myview.activity.utils.ViewUtils;


public class MoveBubbleListener extends MoveListener {

    public MoveBubbleListener(View view, Context context) {
        super(view, context);
    }

    @Override
    public View getView() {
        return hideView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                windowManager.addView(selectView, params);
                hideView.buildDrawingCache();
                int[] ints = new int[2];
                hideView.getLocationOnScreen(ints);
                selectView.setBitmap(hideView.getDrawingCache());
                selectView.initPoint(ints[0] + hideView.getWidth() / 2, ints[1] + hideView.getHeight() / 2 - ViewUtils.getStateBar(mContext));

                break;
            case MotionEvent.ACTION_UP:
                windowManager.removeViewImmediate(selectView);
                hideView.setVisibility(View.VISIBLE);
                ((ViewGroup)hideView.getParent()).removeView(hideView);
                break;
            case MotionEvent.ACTION_MOVE:
                hideView.setVisibility(View.INVISIBLE);
                selectView.movePoint(event.getRawX(), event.getRawY() - ViewUtils.getStateBar(mContext));
                break;
        }
        return true;
    }
}
