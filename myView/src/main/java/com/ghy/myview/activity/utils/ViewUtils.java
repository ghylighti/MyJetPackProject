package com.ghy.myview.activity.utils;

import android.content.Context;
import android.graphics.Paint;

public class ViewUtils {

    public static int getBaseLine(Paint paint, int height)
    {
       if(paint!=null)
       {
           Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();

           int dy=(fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom;
           return height/2+dy;
       }
       return 0;
    }


    //求角A(正切)
    public static double getTanA(float dy,float dx)
    {

        return Math.atan(dy/dx);
    }

    //求圆形坐标参数X
    public static double getCirclePointOffSetX(int radius,float dy,float dx)
    {

      return   radius* Math.sin(getTanA( dy, dx));
    }

    //求圆形坐标参数Y
    public static double getCirclePointOffSetY(int radius,float dy,float dx)
    {
        return   radius* Math.cos(getTanA( dy, dx));
    }

    public static int getStateBar(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    //根据半径和圆心求圆上的点X

    /**
     *
     * @param originX 坐标原点X
     * @param radius 半径
     * @param angle 角度
     * @return
     */
    public static float getOnCircleX(float originX,float radius,float angle)
    {
        return (float) (originX+radius* Math.cos(angle* Math.PI/180));
    }
    //根据半径和圆心求圆上的点Y

    /**
     *
     * @param originY 坐标原点Y
     * @param radius 半径
     * @param angle 角度
     * @return
     */
    public static float getOnCircleY(float originY,float radius,float angle)
    {
        return (float) (originY+radius* Math.sin(angle* Math.PI/180));
    }

}
