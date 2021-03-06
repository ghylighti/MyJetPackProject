package com.ghy.componentproject;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.api.RetrofitManager;
import com.ghy.common.BuildConfig;
import com.ghy.dao.SingleDataBase;
import com.ghy.dao.entity.DataEntity;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.mmkv.MMKV;

import java.util.List;

public class App extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
         initSupport();

    }

    void initSupport()
    {
        RetrofitManager.init(this);
        Log.i("MMKV1",MMKV.initialize(this));
        if(!BuildConfig.isRelease)
        {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    @Override
    public void onTerminate() {
        ARouter.getInstance().destroy();
        super.onTerminate();

    }

    public static Application getApp()
    {
        return getApp();
    }
}
