package com.ghy.componentproject.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.ghy.api.CommonService;
import com.ghy.api.RetrofitManager;
import com.ghy.api.dto.newProjectRootBean.NewProjectDatas;
import com.ghy.api.dto.newProjectRootBean.RespDTO;
import com.ghy.api.dto.newProjectRootBean.newProjectJsonRootBean;
import com.ghy.api.http.RxAdapter;
import com.ghy.common.base.BaseModel;

import java.util.Random;

import MyView.RecyclerLoadMore;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AdFragmentModel extends BaseModel implements RecyclerLoadMore {

    private String timeKey=key+"time";

    public AdFragmentModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application, stateHandle);

    }

    public MutableLiveData<String> getImgUrl() {

        if(!stateHandle.contains(key))
        {
            stateHandle.set(key,mmkv.getString(key,""));
            loadUsers(0);
        }

        return stateHandle.getLiveData(key);
    }

    public MutableLiveData<String> getTime() {

        if(!stateHandle.contains(timeKey))
        {
            stateHandle.set(timeKey,3);
        }

        return stateHandle.getLiveData(timeKey);
    }

    public void loadUsers(int page) {

        RetrofitManager.getInstance().getService(CommonService.class).getListProject(page).compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer()).subscribe(new Observer<RespDTO<NewProjectDatas>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("TestVm","onSubscribe"+d.toString());
            }

            @Override
            public void onNext(RespDTO<NewProjectDatas> newProjectDatasRespDTO) {
                String url;
                url= newProjectDatasRespDTO.getData().getData().get(new Random().nextInt(newProjectDatasRespDTO.getData().getData().size())).getEnvelopePic();
                stateHandle.set(key,url);
            }


            @Override
            public void onError(Throwable e) {
                Log.i("TestVm","onError"+e.toString());
            }

            @Override
            public void onComplete() {
                Log.i("TestVm","onComplete");
            }
        });
    }

    @Override
    public void loadMore(int page) {
        loadUsers(page);
    }
}
