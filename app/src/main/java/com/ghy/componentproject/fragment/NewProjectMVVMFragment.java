package com.ghy.componentproject.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghy.api.dto.newProjectRootBean.NewProjectData;
import com.ghy.common.base.BaseMVVMFragment;
import com.ghy.componentproject.BR;
import com.ghy.componentproject.R;
import com.ghy.componentproject.View.TestAdapter;


import com.ghy.componentproject.databinding.FragmentNewProjectBinding;
import com.ghy.componentproject.model.ProjectModel;

import java.util.List;

public class NewProjectMVVMFragment extends BaseMVVMFragment<ProjectModel, FragmentNewProjectBinding> {
    private View currentView;
    public static NewProjectMVVMFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title",title);
        NewProjectMVVMFragment fragment = new NewProjectMVVMFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initData() {
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        final TestAdapter testAdapter=new TestAdapter<ProjectModel>(getContext(), mViewModel);
        mBinding.rv.setItemViewCacheSize(1);
        mBinding.rv.setLayoutManager(linearLayoutManager);
        testAdapter.updateData();
        mBinding.rv.setAdapter(testAdapter);
        mBinding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(!recyclerView.canScrollVertically(recyclerView.getChildCount()))
                {
                    Log.i("MyTestView","  最后了 ");
                    currentView=recyclerView;
                    testAdapter.loadData();
                }
            }
        });
        mViewModel.getProjectData().observe(this, new Observer<List<NewProjectData>>() {
            @Override
            public void onChanged(List<NewProjectData> newProjectData) {

                testAdapter.setmList(newProjectData);

               testAdapter.notifyDataSetChanged();



            }
        });

//        mViewModel.getTitle().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                mBinding.authorName.setText(s);
//            }
//        });
    }

    @Override
    protected Class<ProjectModel> onBindViewModel() {
        return ProjectModel.class;
    }

    @Override
    protected int onBindLayoutId() {
        return R.layout.fragment_new_project;
    }

    @Override
    protected int[] onBindVariableId() {
        return new int[]{BR.projectModel};
    }
}
