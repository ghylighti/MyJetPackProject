package com.ghy.myview.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ghy.myview.R;
@Route(path = "/view/bubble")
public class ClickDrawActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickdraw);
    }
}
