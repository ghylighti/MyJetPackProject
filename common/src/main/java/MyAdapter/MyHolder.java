package MyAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import MyView.IView;

public class MyHolder<VH extends RecyclerView.ViewHolder> extends RecyclerView.ViewHolder {


    private IView iView;

    public MyHolder(IView iView) {
        super(iView.getView());
        this.iView=iView;

    }

    public void setData(ViewModel viewModel,int position)
    {
        iView.setData(viewModel,position);

    }

}
