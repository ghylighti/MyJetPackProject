package com.ghy.componentproject.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.ghy.api.CommonService;
import com.ghy.api.RetrofitManager;
import com.ghy.api.dto.authorBean.AuthorData;
import com.ghy.api.dto.authorBean.AuthorRootBean;
import com.ghy.api.dto.newProjectRootBean.RespDTO;
import com.ghy.api.http.RxAdapter;
import com.ghy.common.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class PublicFragmentModel extends BaseModel {
    public PublicFragmentModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application, stateHandle);

    }
    public MutableLiveData<List<AuthorData>> getAuthorData()
    {
        if(!stateHandle.contains(key))
        {
            stateHandle.set(key,new ArrayList<AuthorData>());
            loadAuthorList();
        }
        return stateHandle.getLiveData(key);
    }
    public void loadAuthorList()
    {
        RetrofitManager.getInstance().getService(CommonService.class).getAuthorList().compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer()).subscribe(new Observer<RespDTO<List<AuthorData>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RespDTO<List<AuthorData>> listRespDTO) {
                stateHandle.set(key,listRespDTO.getData());
            }



            @Override
            public void onError(Throwable e) {
                Log.i("PublicFragmentModel","onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("PublicFragmentModel","onComplete");
            }
        });
    }
}
