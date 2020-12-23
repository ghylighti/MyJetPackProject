package com.ghy.componentproject.View;

import android.content.Context;
import android.util.Log;

import com.ghy.api.dto.authorBean.AuthorData;
import com.ghy.api.dto.authorBookBean.AuthorBookData;
import com.ghy.api.dto.authorBookBean.AuthorBookDatas;
import com.ghy.api.dto.newProjectRootBean.NewProjectData;
import com.ghy.api.dto.newProjectRootBean.NewProjectDatas;
import com.ghy.common.base.BaseModel;



import MyAdapter.RecyclerAdapter;
import MyView.IView;


public class TestAdapter<E> extends RecyclerAdapter<E, TestHolder> {


    private String id;
    private final int AUTHOR_DATA_TYPE=0;
    private final int NEW_PROJECT_DATA_TYPE=1;
    public TestAdapter(Context mContext, BaseModel viewModel) {

        super(mContext, viewModel);

    }


    @Override
    public void loadData() {
        Log.i("MyTestView",page+"   "+id);
        viewModel.loadMore(++page,id);
    }
    @Override
    public void updateData() {
        Log.i("MyTestView",page+"   "+id);
        viewModel.update(refreshCallBack,id);
    }

    @Override
    protected int getCount() {
        if (mList!=null)
        {
            Log.i("MyTestView","size"+mList.size());
            return mList.size();
        }
        return 0;

    }

    @Override
    public int getItemViewType(int position) {
        E e=mList.get(position);
        if(e instanceof AuthorBookData)
        {
            return AUTHOR_DATA_TYPE;
        }else if(e instanceof NewProjectData)
        {
            return NEW_PROJECT_DATA_TYPE;
        }else {
            return super.getItemViewType(position);
        }
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    protected IView getIView(int viewType) {
        if(viewType==AUTHOR_DATA_TYPE)
        {
            return new AuthorBookView(mContext);
        }else
        {
            return new NewProcejtView(mContext);
        }

    }

    @Override
    public TestHolder onCreateVH(IView iView) {
        return new TestHolder(iView);
    }
}
