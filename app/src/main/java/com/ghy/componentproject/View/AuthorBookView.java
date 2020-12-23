package com.ghy.componentproject.View;

import android.content.Context;


import com.ghy.componentproject.BR;
import com.ghy.componentproject.R;
import com.ghy.componentproject.databinding.RecyclerItemBinding;
import com.ghy.componentproject.model.AuthorBookModel;


import MyView.MvvMView;

public class AuthorBookView extends MvvMView<RecyclerItemBinding, AuthorBookModel>  {

    public AuthorBookView(Context context) {
        super(context);
    }

    @Override
    protected int getPositionId() {
        return BR.position;
    }

    @Override
    protected int getVariableId() {
        return BR.test;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recycler_item;
    }

    @Override
    protected void setOnclickListener(int postion) {

    }
}
