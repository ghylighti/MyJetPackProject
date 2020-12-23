package com.ghy.componentproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghy.api.dto.authorBookBean.AuthorBookData;
import com.ghy.common.base.BaseMVVMFragment;

import com.ghy.componentproject.BR;
import com.ghy.componentproject.R;
import com.ghy.componentproject.View.TestAdapter;
import com.ghy.componentproject.databinding.FragmentTowBinding;
import com.ghy.componentproject.model.AuthorBookModel;


import java.util.List;

import MyView.RefreshCallBack;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TowMVVMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TowMVVMFragment extends BaseMVVMFragment<AuthorBookModel, FragmentTowBinding> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String id;
    private String mParam2;
    private TestAdapter adapter;
    public TowMVVMFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment TowMVVMFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TowMVVMFragment newInstance(String id) {
        Log.i("fragment2","newInstance");
        TowMVVMFragment fragment = new TowMVVMFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("fragment2","onCreate"+id);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_PARAM1);

        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("fragment2","onCreateView"+id);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {

        Log.i("initData","onResume"+id);
        adapter=new TestAdapter<AuthorBookModel>(getContext(),mViewModel);
        adapter.setId(id);
        adapter.setRefreshCallBack(new RefreshCallBack() {
            @Override
            public void onComplete() {
                mBinding.swipe.setRefreshing(false);
            }
        });
        final StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mBinding.title.setAdapter(adapter);
        adapter.loadData();
        mBinding.title.setItemViewCacheSize(1);
        mBinding.title.setLayoutManager(staggeredGridLayoutManager);

        mBinding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.updateData();
            }
        });
        mBinding.title.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(!recyclerView.canScrollVertically(recyclerView.getChildCount()))
                {
                    Log.i("TestVm111","最后了");
                    adapter.loadData();
                }

                if(!recyclerView.canScrollVertically(-1))
                {
                    Log.i("TestVm111","顶部");
                    staggeredGridLayoutManager.invalidateSpanAssignments();
                }
            }
        });

        mViewModel.getData().observe(requireActivity(), new Observer<List<AuthorBookData>>() {
            @Override
            public void onChanged(List<AuthorBookData> data) {
                adapter.setmList(data);
                adapter.notifyDataSetChanged();
            }
        });


    }


    @Override
    public void onPause() {
        Log.i("fragment2","onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("fragment2","onStop"+id);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i("fragment2","onDestroy");
        super.onDestroy();
    }

    @Override
    protected Class<AuthorBookModel> onBindViewModel() {
        return AuthorBookModel.class;
    }

    @Override
    protected int onBindLayoutId() {
        return R.layout.fragment_tow;
    }

    @Override
    protected int[] onBindVariableId() {
        return new int[]{BR.towVm};
    }


}
