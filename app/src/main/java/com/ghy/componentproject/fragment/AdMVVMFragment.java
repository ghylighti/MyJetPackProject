package com.ghy.componentproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.ghy.common.base.BaseMVVMFragment;

import com.ghy.componentproject.BR;
import com.ghy.componentproject.R;
import com.ghy.componentproject.databinding.FragmentAdBinding;

import com.ghy.componentproject.model.AdFragmentModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdMVVMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdMVVMFragment extends BaseMVVMFragment<AdFragmentModel, FragmentAdBinding> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdMVVMFragment() {

    }



    public static AdMVVMFragment newInstance(String param1, String param2) {
        Log.i("fragment1","newInstance");
        AdMVVMFragment fragment = new AdMVVMFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("fragment1","onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }


    @Override
    public void initData() {

        ImageView imageView=mBinding.getRoot().findViewById(R.id.iv);
        Glide.with(getContext()).load(mViewModel.getImgUrl().getValue()).into(imageView);
    }

    @Override
    protected Class<AdFragmentModel> onBindViewModel() {
        return AdFragmentModel.class;
    }

    @Override
    protected int onBindLayoutId() {
        return R.layout.fragment_ad;
    }

    @Override
    protected int[] onBindVariableId() {
        return new int[]{BR.adFragment,BR.position};
    }

    public void setTime(int time)
    {
        Log.i("setTime",mViewModel+"   ");
        mBinding.jump.setText(time+"秒后跳过");
    }
}
