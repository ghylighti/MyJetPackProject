package com.ghy.myview.activity.typeEvaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.util.Log;

public class BezierPointFType implements TypeEvaluator<PointF> {
    private PointF p1,p2;
    public BezierPointFType(PointF p1, PointF p2) {
        this.p1=p1;
        this.p2=p2;
    }

    @Override
    public PointF evaluate(float t, PointF p0, PointF p3) {

        float px= (float) (p0.x* Math.pow((1-t),3)+
                        3*p1.x*t* Math.pow((1-t),2)+
                        3*p2.x* Math.pow(t,2)*(1-t)+
                        p3.x* Math.pow(t,3));

        float py= (float) (p0.y* Math.pow((1-t),3)+
                        3*p1.y*t* Math.pow((1-t),2)+
                        3*p2.y* Math.pow(t,2)*(1-t)+
                        p3.y* Math.pow(t,3));
        Log.i("BezierPointFType",px+"-------------------"+py);
        return new PointF(px,py);
    }
}
