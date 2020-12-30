package com.ghy.myview.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ghy.common.base.BaseActivity;
import com.ghy.myview.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING;

@Route(path = "/view/vpager")
public class VPagerActivity extends FragmentActivity {


    ViewPager2 viewPager2;
    MyAdapter adapter;
    int setting,Select;
    boolean isDRAGGING,isIDLE,isSETTLING;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpager);

         viewPager2=findViewById(R.id.vpager);
        final RecyclerView childAt = (RecyclerView) viewPager2.getChildAt(0);
        childAt.setItemViewCacheSize(0);

        viewPager2.setOffscreenPageLimit(1);

        childAt.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(isDRAGGING)
                {

                    Log.d("viewPager2", "isDRAGGING      "+position);
                }
                if(isIDLE)
                {

                    Log.d("viewPager2", "isIDLE      "+position);
                }
                if(isSETTLING)
                {
                    setting=position;
                    Log.d("viewPager2", "isSETTLING      "+position);
                }

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Select=position;
                adapter.star((Holder) childAt.getChildViewHolder(childAt.getChildAt(Select)),Select);
                adapter.pause((Holder) childAt.getChildViewHolder(childAt.getChildAt(setting)),setting);
                Log.d("viewPager2", "onPageSelected      "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                isSETTLING=false;
                isIDLE=false;
                isDRAGGING=false;
                if(state==SCROLL_STATE_DRAGGING)
                {
                    isDRAGGING=true;
                }
                if(state==SCROLL_STATE_IDLE)
                {
                    isIDLE=true;
                }
                if(state==SCROLL_STATE_SETTLING)
                {
                    isSETTLING=true;
                }

            }
        });
         adapter=new MyAdapter();

         viewPager2.setAdapter(adapter);

    }

    class MyAdapter extends RecyclerView.Adapter<Holder>
    {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(VPagerActivity.this).inflate(R.layout.vpager_item,parent,false);
            return new Holder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull final Holder holder, int position) {
            Uri uri=copyFile(position+1+".3gp");
            holder.videoView.setVideoURI(uri);
            holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.i("holder.videoView","onCompletion");
                   holder.videoView.resume();


                }
            });
            holder.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    holder.videoView.resume();
                    return true;
                }
            });
            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return 3;
        }

       public void star(Holder holder,int position)
       {

          if(!holder.videoView.isPlaying())
          {
              Log.i("holder.videoView","star"+position);
              holder.videoView.start();
          }
       }

        public void pause(Holder childViewHolder, int position) {
            if(childViewHolder.videoView.isPlaying())
            {
                Log.i("holder.videoView","pause"+position);
                childViewHolder.videoView.pause();
            }
        }
    }
    class Holder extends RecyclerView.ViewHolder
    {
        VideoView videoView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("holder.videoView","onClick");
                    videoView.start();
                }
            });
            videoView=itemView.findViewById(R.id.video);
        }

    }

    public Uri copyFile(String name) {
        try {
            File dir = this.getFilesDir();
            File file = new File(dir, name);

            if (file.exists()) {

                return Uri.fromFile(file);

            } else {
                file.createNewFile();
                OutputStream os = new FileOutputStream(file);
                InputStream is = getAssets().open(name);
                byte[] buffer = new byte[1024];
                int bufferRead = 0;
                while((bufferRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bufferRead);
                }
                os.flush();
                is.close();
                os.close();

                return Uri.fromFile(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
