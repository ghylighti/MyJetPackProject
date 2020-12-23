package com.ghy.common.base;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class BaseMVVMFragment<VM extends AndroidViewModel,V extends ViewDataBinding> extends BaseFragment {

    protected VM mViewModel;
    protected V mBinding;

    private int viewModelId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(isDetached())
        {
            return mBinding.getRoot();
        }
        initDataBind(inflater,container);
        return mBinding.getRoot();

    }



    public VM createViewModel(){
        return new ViewModelProvider(this,new SavedStateViewModelFactory(getActivity().getApplication(),this)).get(onBindViewModel());
    }
    private void initDataBind(LayoutInflater inflater,ViewGroup container)
    {

        mBinding= DataBindingUtil.inflate(inflater,onBindLayoutId(),container,false);
        mViewModel=createViewModel();
        if(mBinding != null){
            int[] vars=onBindVariableId();
            for (int i=0;i<vars.length;i++)
            {
                if(i==0)
                {
                    mBinding.setVariable(onBindVariableId()[i], mViewModel);
                }else if(i==1)
                {
                    mBinding.setVariable(onBindVariableId()[1], 0);
                }

            }


        }
        mBinding.setLifecycleOwner(this);
    }

    protected void startController(View v,int action)
    {
        NavController  navController= Navigation.findNavController(v);
        navController.navigate(action);
    }
 

    protected abstract Class<VM> onBindViewModel();
    protected abstract int onBindLayoutId();
    protected abstract int[] onBindVariableId();
}
