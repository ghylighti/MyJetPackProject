package com.ghy.myview.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.myview.R;
import com.ghy.myview.activity.view.ClipTextView;

@Route(path = "/view/flow")
public class ClipTextActivity extends AppCompatActivity {
    private ClipTextView view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_clip_linaer);
        view1=findViewById(R.id.view);
        final ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view1.setProgress((float)animation.getCurrentPlayTime()/3000);
                view1.invalidate();
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setLeft(!view1.isLeft());
                valueAnimator.start();
            }
        });

    }
}
