package com.ghy.myview.activity.listener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ghy.myview.activity.view.MessageBubbleView;


public abstract class MoveListener implements View.OnTouchListener {
    protected View hideView;
    protected MessageBubbleView selectView;

    protected Context mContext;
    protected WindowManager windowManager;
    protected WindowManager.LayoutParams params;
    protected UpCallBack callBack;
    public MoveListener(View view, Context context) {
        this.hideView=view;
        this.mContext=context;
        selectView=new MessageBubbleView(mContext);
        windowManager= (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        params=new WindowManager.LayoutParams();
        //设置为透明
        params.format= PixelFormat.TRANSPARENT;
        //设置状态栏颜色
        params.flags= WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
    }

    public void setUpCallBack(UpCallBack upCallBack) {
       this.callBack=upCallBack;
    }

    public View getView() {
        return null;
    }

}
