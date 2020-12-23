package com.ghy.common.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.i("ARouterLog","注入");
        initARouter();

    }
    private void initARouter() {
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    public abstract void initView();
}
