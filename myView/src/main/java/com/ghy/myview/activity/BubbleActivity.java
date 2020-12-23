package com.ghy.myview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.myview.R;

import com.ghy.myview.activity.listener.MoveBubbleListener;
import com.ghy.myview.activity.view.MessageBubbleView;

@Route(path = "/view/bubble")
public class BubbleActivity extends Activity implements MessageBubbleView.BubbleDisperListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_bubble);
        View view=findViewById(R.id.test_tv);
        MessageBubbleView.attach(new MoveBubbleListener(view,this));
    }

    @Override
    public void dismiss(View view) {

    }
}
