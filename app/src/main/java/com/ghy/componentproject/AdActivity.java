package com.ghy.componentproject;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ghy.common.base.BaseMVVMActivity;
import com.ghy.componentproject.databinding.ActivityAdBinding;

import com.ghy.componentproject.fragment.AdMVVMFragment;
import com.ghy.componentproject.fragment.MainMVVMFragment;
import com.ghy.componentproject.model.AdActivityModel;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Route(path = "/activity/ad")
public class AdActivity extends BaseMVVMActivity<AdActivityModel, ActivityAdBinding> implements Handler.Callback,ThreadInterface {
    private ViewPager2 viewPager2;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int jump = 0;
    private int sum=4;
    private AdMVVMFragment adFragment;

    private FragmentStateAdapter fragmentStateAdapter;

    @Override
    protected void onResume() {
        if (thread.getState() == Thread.State.NEW)
            thread.start();
        Log.i("onResume",viewPager2.getWidth()+"");
        super.onResume();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @Override
    public void initView() {
        viewPager2 = mBinding.myPager2;
        mhadler = new Handler(getMainLooper(), this);
        fragmentList.add(adFragment = new AdMVVMFragment());
        fragmentList.add(new MainMVVMFragment());
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.setUserInputEnabled(false);
        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float position) {
                if (position != 0) {
                    view.setAlpha(0);
                } else if (position == 0) {
                    view.setAlpha(1);
                }
            }
        });

        fragmentStateAdapter = new FragmentStateAdapter(this) {


            @NonNull
            @Override
            public Fragment createFragment(int position) {

                return fragmentList.get(position);

            }

            @Override
            public long getItemId(int position) {
                return super.getItemId(position);
            }

            @Override
            public int getItemCount() {
                return fragmentList.size();
            }

        };


        viewPager2.setAdapter(fragmentStateAdapter);
    }

    @Override
    public boolean isInitDataBase() {
        return false;
    }

    @Override
    protected Class<AdActivityModel> onBindViewModel() {
        return AdActivityModel.class;
    }

    @Override
    protected int onBindLayoutId() {
        return R.layout.activity_ad;
    }

    @Override
    protected int onBindVariableId() {
        return BR.adModel;
    }

    private Handler mhadler;
    private Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                Message message = Message.obtain();
                message.what = 1;
                if (jump < sum) {
                    jump++;
                    try {
                        sleep(1000);
                        mhadler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        this.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                } else {
                    mhadler.removeMessages(message.what);
                    return;
                }
            }
        }
    };

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        try {
            adFragment.setTime(sum-jump);
            if (jump == sum) {
                viewPager2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewPager2.setCurrentItem(1);
                        Log.i("ARouterLog","执行");

                    }
                }, 1000);
                mhadler.removeMessages(msg.what);
            }
        }catch (Exception e)
        {

        }

        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        git();
    }

    @Override
    public void setI(int i) {
        Log.e("线程测试","最终结果"+m1.getI()+"");
    }


    class mThread implements Runnable {
        public int getI() {
            return i;
        }

        public void setThreadInterface(ThreadInterface threadInterface) {
            this.threadInterface = threadInterface;
        }
       
        private ThreadInterface threadInterface;
        private volatile int  i = 0;
        private int last;
        @Override
        public void run() {

            while (true) {
                    synchronized (this)
                    {
                        if (i < 500) {
                            i++;
                            last=i;
                            Log.e("线程测试",Thread.currentThread().getName() + "   " + i);
                        } else {

                            break;
                        }

                    }


            }
            threadInterface.setI(last);
        }

    }

    mThread m1 = new mThread();
    public void git() {
        m1.setThreadInterface(this);
        Thread t1 = new Thread(m1, "线程1");
        Thread t2 = new Thread(m1, "线程2");
        Thread t3 = new Thread(m1, "线程3");
        Thread t4 = new Thread(m1, "线程4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
