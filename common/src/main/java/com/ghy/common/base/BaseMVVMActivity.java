package com.ghy.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.ghy.dao.SingleDataBase;
import com.ghy.dao.dao.DataDao;
import com.ghy.dao.entity.DataEntity;



public abstract class BaseMVVMActivity<VM extends AndroidViewModel,V extends ViewDataBinding> extends BaseActivity {
    protected VM viewModel;
    protected V mBinding;
    private int viewModelId;
    private DataDao dataDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initDataBind();
        initView();
    }


    public abstract boolean isInitDataBase();
    public VM createViewModel(){
        return new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(),this)).get(onBindViewModel());
    }
    public void initDataBind()
    {
        viewModelId=onBindVariableId();
        mBinding= DataBindingUtil.setContentView(this,onBindLayoutId());
        viewModel=createViewModel();
        if(mBinding != null){
            mBinding.setVariable(viewModelId, viewModel);
        }
        mBinding.setLifecycleOwner(this);
    }
    protected abstract Class<VM> onBindViewModel();
    protected abstract int onBindLayoutId();
    protected abstract int onBindVariableId();

}
