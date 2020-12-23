package MyAdapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghy.common.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import MyView.IView;
import MyView.RefreshCallBack;


public abstract  class RecyclerAdapter<E,VH extends MyHolder> extends RecyclerView.Adapter<VH>  {
    protected List<E> mList;
    protected Context mContext;
    protected BaseModel viewModel;
    protected int page;

    public void setRefreshCallBack(RefreshCallBack refreshCallBack) {
        this.refreshCallBack = refreshCallBack;
    }

    protected RefreshCallBack refreshCallBack;

    public RecyclerAdapter(Context mContext, BaseModel viewModel) {
        this.mContext=mContext;
        this.viewModel=viewModel;
        mList=new ArrayList<E>();

        Log.i("MyTestView","RecyclerAdapter");

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IView iView=getIView(viewType);

        Log.i("MyTestView","onCreateViewHolder");
        return onCreateVH(iView);
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        Log.i("MyTestView","onBindViewHolder");
        holder.setData(viewModel,position);
    }

    public void setmList(List<E> list)
    {
        mList=list;
    }

    @Override
    public int getItemCount() {

        return getCount();
    }


    public abstract void loadData();
    public abstract void updateData();
    protected abstract int getCount();
    protected abstract IView getIView(int viewType);
    public abstract VH onCreateVH(IView iView);

}
