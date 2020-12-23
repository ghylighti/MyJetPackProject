package com.ghy.api;

import com.ghy.api.dto.authorBean.AuthorData;
import com.ghy.api.dto.authorBookBean.AuthorBookDatas;
import com.ghy.api.dto.newProjectRootBean.NewProjectDatas;
import com.ghy.api.dto.newProjectRootBean.RespDTO;
import com.ghy.api.dto.newProjectRootBean.newProjectJsonRootBean;
import com.ghy.api.dto.authorBean.AuthorRootBean;
import com.ghy.api.dto.authorBookBean.AuthorBookRootBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommonService{

    @GET("article/listproject/{page}/json")
    Observable<RespDTO<NewProjectDatas>> getListProject(@Path("page") int page);

    @GET("wxarticle/chapters/json")
    Observable<RespDTO<List<AuthorData>>> getAuthorList();
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<RespDTO<AuthorBookDatas>> getAuthorBook(@Path("id") String id, @Path("page") int page);

//    @POST("article/query/0/json")
//    Observable<RespDTO<List<>>> queryList();


}
