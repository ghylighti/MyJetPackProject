package com.ghy.myview.activity.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.ghy.myview.activity.listener.MoveListener;
import com.ghy.myview.activity.utils.ViewUtils;


public class MessageBubbleView extends View {
    private PointF dragPoint,fixPoint;
    private int dragRadius=20;
    private int fixMaxRadius=20;
    private int fixMinRadius=7;
    private Paint dragPointPaint;
    private Path bserPath=new Path();
    private Bitmap showViewBitmap;

    public boolean isOutMaxRadius() {
        return outMaxRadius;
    }

    private boolean outMaxRadius;
    public MessageBubbleView(Context context) {
        this(context,null);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    public void setBitmap(Bitmap drawingCache) {
        showViewBitmap=drawingCache;
    }
    public interface BubbleDisperListener
    {
        void dismiss(View view);
    }
    public static void attach(MoveListener moveListener) {
        moveListener.getView().setOnTouchListener(moveListener);
    }

    private void initData() {
        dragRadius= (int) dip2px(dragRadius);
        dragPointPaint=new Paint();
        dragPointPaint.setColor(Color.RED);
        dragPointPaint.setAntiAlias(true);
        dragPointPaint.setDither(true);
    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                float downX=event.getX();
//                float downY=event.getY();
//                initPoint(downX,downY);
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float moveX=event.getX();
//                float moveY=event.getY();
//                movePoint(moveX,moveY);
//                break;
//        }
//        invalidate();
//        return true;
//    }

    public void movePoint(float moveX, float moveY) {
        dragPoint.set(moveX,moveY);
        invalidate();
    }


    public void initPoint(float downX, float downY)
    {
        dragPoint=new PointF(downX,downY);
        fixPoint=new PointF(downX,downY);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(dragPoint==null||fixPoint==null)
        {
            return;
        }
        canvas.drawCircle(dragPoint.x,dragPoint.y,dragRadius,dragPointPaint);
        int distance=getPointDistance(dragPoint,fixPoint);
        int fixRadius= (int) dip2px(fixMaxRadius-distance/20);
        if(fixRadius>fixMinRadius)
        {
            canvas.drawCircle(fixPoint.x,fixPoint.y,fixRadius,dragPointPaint);
            //画路径
            canvas.drawPath(createBezier(fixRadius),dragPointPaint);
            outMaxRadius=false;
        }else
        {
            outMaxRadius=true;

        }
        if(showViewBitmap!=null)
        {
            Log.i("test","bitmap"+showViewBitmap);
            canvas.drawBitmap(showViewBitmap,dragPoint.x-showViewBitmap.getWidth()/2,dragPoint.y-showViewBitmap.getHeight()/2,null);
        }

    }

    private Path createBezier(int fixRadius) {
        bserPath.reset();

        if(fixRadius>0)
        {

            float dy=dragPoint.y-fixPoint.y;
            float dx=dragPoint.x-fixPoint.x;
            int p1x= (int) (dragPoint.x- ViewUtils.getCirclePointOffSetX(dragRadius,dy,dx));
            int p1y= (int) (dragPoint.y+ ViewUtils.getCirclePointOffSetY(dragRadius,dy,dx));
            int p2x= (int) (dragPoint.x+ ViewUtils.getCirclePointOffSetX(dragRadius,dy,dx));
            int p2y= (int) (dragPoint.y- ViewUtils.getCirclePointOffSetY(dragRadius,dy,dx));
            int p3x= (int) (fixPoint.x+ ViewUtils.getCirclePointOffSetX(fixRadius,dy,dx));
            int p3y= (int) (fixPoint.y- ViewUtils.getCirclePointOffSetY(fixRadius,dy,dx));
            int p4x= (int) (fixPoint.x- ViewUtils.getCirclePointOffSetX(fixRadius,dy,dx));
            int p4y= (int) (fixPoint.y+ ViewUtils.getCirclePointOffSetY(fixRadius,dy,dx));

            bserPath.moveTo(p1x,p1y);

            bserPath.quadTo((dragPoint.x+fixPoint.x)/2,(dragPoint.y+fixPoint.y)/2,p4x,p4y);

            bserPath.lineTo(p3x,p3y);

            bserPath.quadTo((dragPoint.x+fixPoint.x)/2,(dragPoint.y+fixPoint.y)/2,p2x,p2y);
            bserPath.close();
        }
        return bserPath;
    }

    private int getPointDistance(PointF A, PointF B)
    {

        return (int) Math.sqrt((A.x-B.x)*(A.x-B.x)+(A.y-B.y)*(A.y-B.y));
    }

    private float dip2px(int dip) {
      return   TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }
}
