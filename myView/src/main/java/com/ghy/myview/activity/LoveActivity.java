package com.ghy.myview.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.myview.R;
import com.ghy.myview.activity.view.LoveView;

@Route(path = "/view/good")
public class LoveActivity extends Activity {
    private Button button;
    private LoveView loveView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_love);
        ARouter.getInstance().inject(this);
        button=findViewById(R.id.btn);
        loveView=findViewById(R.id.loveView);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                loveView.addLoveView();
            }
        });
    }
}
