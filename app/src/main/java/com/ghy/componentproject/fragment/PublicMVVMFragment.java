package com.ghy.componentproject.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ghy.api.dto.authorBean.AuthorData;
import com.ghy.common.base.BaseMVVMFragment;
import com.ghy.componentproject.BR;
import com.ghy.componentproject.R;
import com.ghy.componentproject.databinding.FragmentPublicBinding;

import com.ghy.componentproject.model.PublicFragmentModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class PublicMVVMFragment extends BaseMVVMFragment<PublicFragmentModel, FragmentPublicBinding> {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private TowMVVMFragment towFragment;
    @Override
    protected void initData() {

        final FragmentStateAdapter fragmentStateAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Log.i("FragmentStateAdapter","createFragment:"+position+"");

                return fragmentList.get(position);
            }

            @Override
            public long getItemId(int position) {
                Log.i("FragmentStateAdapter","getItemId:"+position+"");
                return super.getItemId(position);
            }

            @Override
            public int getItemCount() {

                return fragmentList.size();
            }

        };

        ((RecyclerView)mBinding.pager2.getChildAt(0)).setItemViewCacheSize(2);
        ((RecyclerView)mBinding.pager2.getChildAt(0)).getLayoutManager().setItemPrefetchEnabled(false);
        mViewModel.getAuthorData().observe(this, new Observer<List<AuthorData>>() {
            @Override
            public void onChanged(final List<AuthorData> authorData) {
                for (int i=0;i<authorData.size();i++)
                {
                    int id = authorData.get(i).getId();
                    String  name= authorData.get(i).getName();
                    towFragment = TowMVVMFragment.newInstance(id+"");
                    title.add(name);
                    fragmentList.add(towFragment);
                    Log.i("getAuthorData",name);
                }

                fragmentStateAdapter.notifyDataSetChanged();
                mBinding.pager2.setAdapter(fragmentStateAdapter);

            }
        });

        new TabLayoutMediator(mBinding.tab, mBinding.pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                Log.i("getAuthorData","onConfigureTab"+title.get(position));
                tab.setText(title.get(position).toString());
            }
        }).attach();

    }

    @Override
    protected Class<PublicFragmentModel> onBindViewModel() {
        return PublicFragmentModel.class;
    }

    @Override
    protected int onBindLayoutId() {
        return R.layout.fragment_public;
    }

    @Override
    protected int[] onBindVariableId() {
        return new int[]{BR.publicModel};
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("PublicMVVMFragment","onCreate");
    }




    @Override
    public void onStart() {
        super.onStart();
        Log.i("PublicMVVMFragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("PublicMVVMFragment","onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("PublicMVVMFragment","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("PublicMVVMFragment","onDestroy");
    }
}
