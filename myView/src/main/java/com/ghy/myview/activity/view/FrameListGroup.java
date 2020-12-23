package com.ghy.myview.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;


import com.ghy.myview.activity.listener.MoveDeleteListener;
import com.ghy.myview.activity.listener.MoveListener;
import com.ghy.myview.activity.listener.MyAdapter;
import com.ghy.myview.activity.listener.UpCallBack;
import com.ghy.myview.activity.listener.getBackView;

import java.util.ArrayList;

public class FrameListGroup extends ViewGroup implements getBackView {


    private ArrayList<View> arrayList=new ArrayList<>();
    private MyAdapter adapter;

    private UpCallBack upCallBack=new UpCallBack() {
        @Override
        public void call(View v) {

            addView(v,0);


        }
    };

    public MoveListener moveListener;
    public void setMoveListener(MoveListener moveListener)
    {
        this.moveListener=moveListener;
    }


    private ArrayList<View> mListView=new ArrayList<>();

    public FrameListGroup(Context context) {
        this(context,null);
    }

    public FrameListGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FrameListGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int width = 0;
        int height = 0;
        int count=getChildCount();
        for (int i=0;i<count;i++)
        {
            View v=getChildAt(i);
            width= Math.max(width,v.getMeasuredWidth());
            height= Math.max(width,v.getMeasuredHeight());
            Log.i(getClass().getName(),"onMeasure"+"-----------"+v.getTag()+"width"+(width-i*dx2dip(7)));
           measureChild(v,width,height);
        }

        setMeasuredDimension(width,height+count*dx2dip(2));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(getClass().getName(),"onLayout"+getChildCount());
        int count=getChildCount();
        for (int i=count-1;i>=0;i--)
        {
            View view=getChildAt(count-1-i);
            Log.i(getClass().getName(),"onLayout"+getMeasuredHeight()+"------------------"+(i));
            view.layout(0, 0+i*dx2dip(2), getMeasuredWidth(), getMeasuredHeight()+i*dx2dip(2));

        }
    }
    public void setMyAdapter(MyAdapter myAdapter)
    {
        removeAllViews();
        this.adapter=myAdapter;
        if(adapter!=null)
        {
          int count=adapter.getCount();
          for (int i=0;i<count;i++)
          {
              View v=adapter.getView(i,null,this);
              v.setTag(i);

              setMoveListener(new MoveDeleteListener(v,getContext(),this));
              if(moveListener!=null)
              {
                  attach(moveListener);
              }
              arrayList.add(v);
              addView(v,i);
          }
        }
    }
    private int dx2dip(int dx)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dx,getResources().getDisplayMetrics());
    }


    public  void attach(MoveListener moveListener) {
        if(moveListener instanceof MoveDeleteListener)
        {
            moveListener.setUpCallBack(upCallBack);
        }
        moveListener.getView().setOnTouchListener(moveListener);
    }

    @Override
    public View getBackView(int i) {

        Log.i("MoveDeleteListener",i +"  "+getChildCount());
        i--;
        return arrayList.get(i<0?arrayList.size()-1:i);
    }
}
