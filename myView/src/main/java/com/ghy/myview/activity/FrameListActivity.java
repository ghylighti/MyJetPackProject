package com.ghy.myview.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.myview.R;
import com.ghy.myview.activity.listener.MyAdapter;
import com.ghy.myview.activity.view.FrameListGroup;


import java.util.Random;
@Route(path = "/view/stack")
public class FrameListActivity extends Activity {


    private int[] ivList=new int[]{R.mipmap.kn_1, R.mipmap.kn_2, R.mipmap.kn_3, R.mipmap.kn_4, R.mipmap.kn_5, R.mipmap.kn_6};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelist_group);
        ARouter.getInstance().inject(this);
        Random random=new Random();
        FrameListGroup frameListGroup=findViewById(R.id.frame_group);

            frameListGroup.setBackgroundColor(Color.WHITE);
            frameListGroup.setMyAdapter(new MyAdapter() {
                @Override
                public int getCount() {
                    return ivList.length;
                }

                @Override
                public Object getItem(int position) {
                    return position;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                   if(convertView==null)
                   {
                       convertView= View.inflate(FrameListActivity.this, R.layout.framelist_item,null);
                       parent.setTag(convertView);
                   }else
                   {
                       convertView= (View) parent.getTag();
                   }
                   ImageView iv=convertView.findViewById(R.id.iv);
                   iv.setBackground(getDrawable(ivList[position]));


                    return convertView;
                }

            });






    }
}
