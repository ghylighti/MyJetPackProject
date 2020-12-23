package com.ghy.detail.debug;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.common.BuildConfig;

public class  DetailApp  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(!BuildConfig.isRelease)
        {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
