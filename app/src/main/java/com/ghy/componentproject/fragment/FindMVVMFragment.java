package com.ghy.componentproject.fragment;

import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.common.base.BaseMVVMFragment;
import com.ghy.componentproject.BR;
import com.ghy.componentproject.R;
import com.ghy.componentproject.databinding.FragmentViewBinding;
import com.ghy.componentproject.model.FindModel;
public class FindMVVMFragment extends BaseMVVMFragment<FindModel, FragmentViewBinding>{
    @Override
    protected void initData() {
        onClick(mBinding.moveView);
        onClick(mBinding.flowWord);
        onClick(mBinding.clickGood);
        onClick(mBinding.stackGroup);
        onClick(mBinding.viewVpager);
    }

    @Override
    protected Class<FindModel> onBindViewModel() {
        return FindModel.class;
    }

    @Override
    protected int onBindLayoutId() {
        return R.layout.fragment_view;
    }

    @Override
    protected int[] onBindVariableId() {
        return new int[]{BR.myView};
    }


    public void onClick(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.flow_word:
                        Log.i("tsttttt","/view/flow");
                        ARouter.getInstance().build("/view/flow").navigation(getContext());
                        break;
                    case R.id.move_view:
                        Log.i("tsttttt","/view/bubble");
                        ARouter.getInstance().build("/view/bubble").navigation(getContext());

                        break;
                    case R.id.click_good:
                        Log.i("tsttttt","/view/good");
                        ARouter.getInstance().build("/view/good").navigation(getContext());
                        break;
                    case R.id.stack_group:
                        Log.i("tsttttt","/view/stack");
                        ARouter.getInstance().build("/view/stack").navigation(getContext());
                        break;
                    case R.id.view_vpager:
                        Log.i("tsttttt","/view/vpager");
                        ARouter.getInstance().build("/view/vpager").navigation(getContext());
                        break;
                }
            }
        });

    }
}
