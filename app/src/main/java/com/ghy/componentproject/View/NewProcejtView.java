package com.ghy.componentproject.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.library.baseAdapters.BR;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.componentproject.R;
import com.ghy.componentproject.databinding.NewprojectRecylerItemBinding;

import com.ghy.componentproject.model.ProjectModel;
import com.ghy.detail.activity.DetailActivity;

import MyView.MvvMView;

public class NewProcejtView extends MvvMView<NewprojectRecylerItemBinding, ProjectModel> {
    public NewProcejtView(Context context) {
        super(context);
    }

    public NewProcejtView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getPositionId() {
        return BR.position;
    }

    @Override
    protected int getVariableId() {
        return BR.projectVm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.newproject_recyler_item;
    }

    @Override
    protected void setOnclickListener(int postion) {

        Activity activity= (Activity) mContext;
        ActivityOptionsCompat compat=ActivityOptionsCompat.makeSceneTransitionAnimation(activity,viewDataBind.detailIv,"test");

        ARouter.getInstance().build("/detail/detail").
                withString("url",viewModel.getProjectData().getValue().get(postion).getLink())
                .withString("image",viewModel.getProjectData().getValue().get(postion).getEnvelopePic())
                .withOptionsCompat(compat)
                .navigation(mContext);

    }
}
