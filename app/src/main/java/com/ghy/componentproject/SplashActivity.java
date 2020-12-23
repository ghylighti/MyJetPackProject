package com.ghy.componentproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Utils.SchemeUtils;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        if(intent!=null&&! TextUtils.isEmpty(intent.getData().getScheme()))
        {
            SchemeUtils.handleScheme(this,intent.getData());
            return;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
