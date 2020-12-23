package com.ghy.common.base;

import android.util.Log;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public boolean isFirstLoad=true;
    @Override
    public void onResume() {

        testHook();
        super.onResume();
    }
    private void testHook()
    {
        if(isFirstLoad)
        {

            initData();
            isFirstLoad=false;
        }
    }
    @Override
    public void onDestroy() {
        isFirstLoad=true;
        super.onDestroy();
    }

    protected abstract void initData();
}
