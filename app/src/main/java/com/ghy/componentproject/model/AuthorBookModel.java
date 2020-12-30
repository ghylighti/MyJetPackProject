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
import com.ghy.componentproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import MyView.RefreshCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AuthorBookModel extends BaseModel  {

    private Random random=new Random();

    private int[] pics=new int[]{R.mipmap.kn_1,R.mipmap.kn_2,R.mipmap.kn_3,R.mipmap.kn_4,R.mipmap.kn_5};

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
                initPics(authorBookDatasRespDTO.data.getData());
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
                Log.i("TestVm111","onError"+e.getLocalizedMessage());
                Complete();
            }

            @Override
            public void onComplete() {
                Log.i("TestVm111","onComplete");
              Complete();

            }
        });;
    }

    private void initPics(List<AuthorBookData> data) {
        Log.i("TestVm111","initPics");
        for (int i=0;i<data.size();i++)
        {
            Log.i("TestVm111","initPics"+"   "+pics[random.nextInt(5)]);
            data.get(i).setLurl(pics[random.nextInt(5)]);
        }
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
