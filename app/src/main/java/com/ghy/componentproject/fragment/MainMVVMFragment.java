package com.ghy.componentproject.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.common.base.BaseMVVMFragment;
import com.ghy.componentproject.BR;
import com.ghy.componentproject.R;
import com.ghy.componentproject.databinding.FragmentMainBinding;
import com.ghy.componentproject.model.FMainModel;

import com.ghy.detail.activity.DetailActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.http.Path;
public class MainMVVMFragment extends BaseMVVMFragment<FMainModel, FragmentMainBinding> {

    @Override
    protected void initData() {
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.isChecked())
                {
                    return false;
                }

                menuItem.setChecked(true);
                switch (menuItem.getItemId())
                {
                    case R.id.trip_menu:

                        Navigation.findNavController(mBinding.mainFragment).navigate(R.id.newProjectFragment);
                        mBinding.toolbar.setTitle("新项目");
                        mBinding.titleText.setVisibility(View.GONE);
                        break;
                    case R.id.find_menu:
                        Navigation.findNavController(mBinding.mainFragment).navigate(R.id.findFragment);

                        mBinding.toolbar.setTitle("自定义");
                        mBinding.titleText.setVisibility(View.GONE);
                        break;
                    case R.id.public_menu:


                        Navigation.findNavController(mBinding.mainFragment).navigate(R.id.publicFragment);
                        mBinding.toolbar.setTitle("公众号");
                        mBinding.titleText.setVisibility(View.VISIBLE);

                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected Class<FMainModel> onBindViewModel() {
        return FMainModel.class;
    }

    @Override
    protected int onBindLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected int[] onBindVariableId() {
        return new int[]{
            BR.mainVM};
    }
}
