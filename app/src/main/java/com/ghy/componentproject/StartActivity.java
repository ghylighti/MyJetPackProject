package com.ghy.componentproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mmkv.MMKV;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        if(MMKV.defaultMMKV().getBoolean("first",true))
//        {
//            goSearchActivity(GuideActivity.class);
//        }else
//        {
            goSearchActivity(AdActivity.class);
//        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void goSearchActivity(Class clazz) {
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
    }

}
