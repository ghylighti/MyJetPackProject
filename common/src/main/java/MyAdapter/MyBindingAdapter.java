package MyAdapter;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class MyBindingAdapter {
    @BindingAdapter("image")
    public static void setImage(ImageView view, String url)
    {
        Log.i("BindingAdapter",url+"");
        Glide.with(view).load(url).centerCrop().into(view);
    }
}
