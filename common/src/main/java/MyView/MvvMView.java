package MyView;


import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.ghy.common.base.BaseModel;


public abstract class MvvMView<D extends ViewDataBinding,V extends BaseModel> extends RelativeLayout implements IView<V>  {
    protected V viewModel;
    protected D viewDataBind;
    protected Context mContext;
    private int postion;
    public MvvMView(Context context) {

        this(context,null);
        init(mContext=context);
    }

    public MvvMView(Context context, AttributeSet attrs) {
        this(context,null,0);
        init(mContext=context);
    }

    public MvvMView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(mContext=context);
    }

    void init(Context context)
    {

        viewDataBind = DataBindingUtil.inflate(LayoutInflater.from(getContext()),getLayoutId(),this,false);
        if(getLayoutId()!=0)
        {
            getRoot().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setOnclickListener(postion);
                }
            });
        }
        this.addView(getRoot());
    }
    public View getRoot()
    {
        return viewDataBind.getRoot();
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setData(V data,int postion) {
        if(viewModel==null)
        {
            viewModel=data;
            viewDataBind.setVariable(getVariableId(),viewModel);
        }
        this.postion=postion;
        viewDataBind.setVariable(getPositionId(),postion);
        viewDataBind.executePendingBindings();
    }

    protected abstract int getPositionId();

    protected abstract int getVariableId();
    protected abstract int getLayoutId();

    @BindingAdapter("imagea")
    public static void setImage(ImageView view,String url)
    {

        Glide.with(view).load(url).centerInside().into(view);
    }

    protected abstract void setOnclickListener(int postion);
}
