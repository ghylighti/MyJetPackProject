package com.ghy.myview.activity.listener;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;

import com.ghy.myview.activity.utils.ViewUtils;


public class MoveDeleteListener extends MoveListener  {
    private int selectWidth;
    private int selectHeight;
    private getBackView backView;

    private View  backgroundView;
    public MoveDeleteListener(View view, Context context,getBackView backView) {

        super(view, context);
        this.backView=backView;

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
                selectView.setBitmap(hideView.getDrawingCache(false));
                selectView.initPoint(ints[0] + hideView.getWidth() / 2, ints[1] + hideView.getHeight() / 2 );
                Log.i("MoveDelete",ViewUtils.getStateBar(mContext)+"");
                selectHeight=selectView.getHeight();
                selectWidth=selectView.getWidth();

                backgroundView=backView.getBackView((Integer) hideView.getTag());
                Log.i("MoveDeleteListener",backgroundView.getTag()+"");
                break;
            case MotionEvent.ACTION_UP:
                if(selectView.isOutMaxRadius())
                {
                    ((ViewGroup)hideView.getParent()).removeView(hideView);
                    callBack.call(hideView);
                }
                windowManager.removeViewImmediate(selectView);
                hideView.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_MOVE:
                hideView.setVisibility(View.INVISIBLE);
                selectView.movePoint(event.getRawX(), event.getRawY() - ViewUtils.getStateBar(mContext));
                break;
        }
        return true;
    }


}
