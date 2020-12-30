package com.ghy.componentproject.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.ghy.common.base.BaseMVVMFragment;
import com.ghy.componentproject.AidlService;
import com.ghy.componentproject.BR;
import com.ghy.componentproject.R;
import com.ghy.componentproject.aidl.IMyAidlInterface;
import com.ghy.componentproject.databinding.FragmentMainBinding;
import com.ghy.componentproject.model.FMainModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMVVMFragment extends BaseMVVMFragment<FMainModel, FragmentMainBinding>implements ServiceConnection {

    private IMyAidlInterface iMyAidlInterface;
    @Override
    protected void initData() {
        bindService();
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
                        try {
                            iMyAidlInterface.postString("新项目");
                            Log.i("MyAidl","新项目");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        mBinding.titleText.setVisibility(View.GONE);
                        break;
                    case R.id.find_menu:
                        Navigation.findNavController(mBinding.mainFragment).navigate(R.id.findFragment);

                        mBinding.toolbar.setTitle("自定义");
                        try {
                            iMyAidlInterface.postString("自定义");
                            Log.i("MyAidl","自定义");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        mBinding.titleText.setVisibility(View.GONE);
                        break;
                    case R.id.public_menu:


                        Navigation.findNavController(mBinding.mainFragment).navigate(R.id.publicFragment);
                        mBinding.toolbar.setTitle("公众号");
                        try {
                            iMyAidlInterface.postString("公众号");
                            Log.i("MyAidl","公众号");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        mBinding.titleText.setVisibility(View.VISIBLE);

                        break;
                }
                return false;
            }
        });

        Log.i("MyAidl","bindService");
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

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i("MyAidl","onServiceConnected");
        iMyAidlInterface= IMyAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i("MyAidl","onServiceDisconnected");
    }


    private void bindService() {
        Intent intent = new Intent(getContext(), AidlService.class);
        requireActivity().bindService(intent,this, Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireActivity().unbindService(this);
    }


}
