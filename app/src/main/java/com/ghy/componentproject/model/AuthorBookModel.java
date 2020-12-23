package com.ghy.componentproject.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.ghy.api.CommonService;
import com.ghy.api.RetrofitManager;
import com.ghy.api.dto.authorBookBean.AuthorBookData;
import com.ghy.api.dto.authorBookBean.AuthorBookDatas;
import com.ghy.api.dto.authorBookBean.AuthorBookRootBean;

import com.ghy.api.dto.newProjectRootBean.RespDTO;
import com.ghy.api.http.RxAdapter;
import com.ghy.common.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import MyView.RefreshCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AuthorBookModel extends BaseModel  {




    public AuthorBookModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application, stateHandle);

    }


    public MutableLiveData<List<AuthorBookData>> getData() {

        if(!stateHandle.contains("AuthorBookDatas"))
        {

            stateHandle.set("AuthorBookDatas",new ArrayList<>());

        }
        return stateHandle.getLiveData("AuthorBookDatas");
    }

    public void loadUsers(final int page,String id) {

        RetrofitManager.getInstance().getService(CommonService.class).getAuthorBook(id,page).compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer()).subscribe(new Observer<RespDTO<AuthorBookDatas>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("TestVm111","onSubscribe");
            }

            @Override
            public void onNext(RespDTO<AuthorBookDatas> authorBookDatasRespDTO) {
                if(page>0)
                {
                    List<AuthorBookData> value = getData().getValue();
                    value.addAll(authorBookDatasRespDTO.getData().getData());
                    stateHandle.set("AuthorBookDatas",value);

                }else
                {
                    stateHandle.set("AuthorBookDatas",authorBookDatasRespDTO.getData().getData());
                }
            }


            @Override
            public void onError(Throwable e) {
                Complete();
            }

            @Override
            public void onComplete() {
              Complete();

            }
        });;
    }

    @Override
    public void loadMore(int page, String id) {
        Log.i("TestVm111","loadMore"+page+"   "+id);
        loadUsers(page,id);
    }

    @Override
    public void update(RefreshCallBack callBack, String id) {
        Log.i("TestVm111","update"+"   "+id);
        super.update(callBack);
        loadUsers(0,id);
    }
}
