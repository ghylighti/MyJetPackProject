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
import com.ghy.api.dto.authorBookBean.AuthorBookData;
import com.ghy.api.dto.newProjectRootBean.NewProjectData;
import com.ghy.api.dto.newProjectRootBean.NewProjectDatas;
import com.ghy.api.dto.newProjectRootBean.RespDTO;
import com.ghy.api.dto.newProjectRootBean.newProjectJsonRootBean;
import com.ghy.api.http.RxAdapter;
import com.ghy.common.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import MyView.RefreshCallBack;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProjectModel extends BaseModel {

    public ProjectModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application, stateHandle);
    }

    public MutableLiveData<List<NewProjectData>> getProjectData()
    {
        if(!stateHandle.contains(key))
        {
            stateHandle.set(key,new ArrayList<>());
            loadProjectLIst(0);

        }
        return stateHandle.getLiveData(key);
    }
    public void loadProjectLIst(final int page)
    {
        RetrofitManager.getInstance().getService(CommonService.class).getListProject(page).compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer()).subscribe(new Observer<RespDTO<NewProjectDatas>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RespDTO<NewProjectDatas> newProjectDatasRespDTO) {
                if(page>0)
                {
                    List<NewProjectData> value = getProjectData().getValue();
                    value.addAll(newProjectDatasRespDTO.getData().getData());
                    stateHandle.set(key,value);


                }else
                {
                    stateHandle.set(key,newProjectDatasRespDTO.getData().getData());
                }
            }



            @Override
            public void onError(Throwable e) {
                Log.i("NewProjectFragmentModel","onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("NewProjectFragmentModel","onComplete");
            }
        });
    }


    @Override
    public void loadMore(int page) {
       loadProjectLIst(page);
    }

    @Override
    public void update(RefreshCallBack callBack) {
        super.update(callBack);
        loadProjectLIst(0);
    }
}
