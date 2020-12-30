package MyAdapter;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.ghy.common.R;

public class MyBindingAdapter {

    @BindingAdapter("image")
    public static void setImage(ImageView view, String url)
    {
        Log.i("BindingAdapter",url+"");
        Glide.with(view).load(url).centerCrop().into(view);

    }
    @BindingAdapter("imageR")
    public static void setImageR(ImageView view, int Rurl)
    {
        Log.i("BindingAdapter",Rurl+"");
        Glide.with(view).load(Rurl).centerCrop().into(view);

    }
}
