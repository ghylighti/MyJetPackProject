package com.ghy.common.base;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.SavedStateHandle;
import androidx.room.util.StringUtil;

import com.ghy.dao.SingleDataBase;

import com.ghy.dao.dao.DataWithStepDao;
import com.tencent.mmkv.MMKV;

import MyView.RecyclerLoadMore;
import MyView.RefreshCallBack;


public class BaseModel extends AndroidViewModel implements RecyclerLoadMore {

    protected SavedStateHandle stateHandle;
    protected String key=getClass().getSimpleName();
    protected MMKV mmkv;
    protected DataWithStepDao dataWithStepDao;
    protected RefreshCallBack refreshCallBack;
    public BaseModel(@NonNull Application application,SavedStateHandle stateHandle) {
        super(application);
        this.stateHandle = stateHandle;
        mmkv=MMKV.defaultMMKV();
        this.dataWithStepDao=SingleDataBase.getInstance(application.getApplicationContext()).dataWithStepDao();


    }


    @Override
    public void loadMore(int page, String id) {
        if(TextUtils.isEmpty(id))
        {
            loadMore(page);
        }
    }

    @Override
    public void loadMore(int page) {

    }

    @Override
    public void update(RefreshCallBack callBack, String id) {
       update(callBack);
    }

    @Override
    public void update(RefreshCallBack callBack) {
        if(callBack!=null)
        {
            refreshCallBack=callBack;
        }
    }

    protected void Complete()
    {
        if(refreshCallBack!=null)
        {
            refreshCallBack.onComplete();
        }
    }
}
