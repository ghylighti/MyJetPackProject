package com.ghy.detail.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.ghy.common.base.BaseMVVMActivity;
import com.ghy.detail.BR;
import com.ghy.detail.model.MyDetailModel;
import com.ghy.detail.R;
import com.ghy.detail.databinding.ActivityDetailBinding;

@Route(path = "/detail/detail")
public class DetailActivity extends BaseMVVMActivity<MyDetailModel, ActivityDetailBinding> {
    @Autowired
    String url;
    @Autowired
    String image;
    private View v;
    @Override
    public boolean isInitDataBase() {
        return false;
    }

    @Override
    protected Class<MyDetailModel> onBindViewModel() {
        return MyDetailModel.class;
    }

    @Override
    protected int onBindLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected int onBindVariableId() {
        return BR.test;
    }

    @Override
    public void initView() {

        v=mBinding.topIv;
        Glide.with(this).load(image).into(mBinding.topIv);
        mBinding.webView.loadUrl(url);
        Log.i("DetailActivity",url+"     "+image);
    }
}
