package com.ghy.api;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.ghy.api.config.API;
import com.ghy.api.http.MyConverter;
import com.ghy.api.util.HttpUtils;
import com.ghy.api.util.SSLContextUtil;


import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: <RetrofitManager><br>
 * Author:      mxdl<br>
 * Date:        2019/6/22<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class RetrofitManager {
    public static RetrofitManager retrofitManager;
    public static Context mContext;
    private Retrofit mRetrofit;
    private OkHttpClient.Builder okHttpBuilder;
    private String mToken;

    private RetrofitManager() {

        okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(20 * 1000, TimeUnit.MILLISECONDS).
                readTimeout(20 * 1000, TimeUnit.MILLISECONDS).
                writeTimeout(20 * 1000, TimeUnit.MILLISECONDS);

        okHttpBuilder.addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY));
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //Token
                if (!TextUtils.isEmpty(mToken)) {
                    request = request.newBuilder().url(
                            request.url().newBuilder()
                                    .addQueryParameter("access_token", mToken)
                                    .build()
                    ).build();
                }
                //缓存
                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                cacheBuilder.maxStale(365,TimeUnit.DAYS);
                CacheControl cacheControl = cacheBuilder.build();
                if(!HttpUtils.isNetworkAvailable(mContext)){
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (HttpUtils.isNetworkAvailable(mContext)) {
                    int maxAge = 0; // read from cache
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        });

        //给client的builder添加了一个socketFactory
        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        if (sslContext != null) {
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();

            okHttpBuilder.sslSocketFactory(socketFactory,SSLContextUtil.trustManagers);
        }
        okHttpBuilder.hostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
        //创建client
        OkHttpClient okHttpClient = okHttpBuilder.build();

        mRetrofit = new Retrofit.Builder()

                .client(okHttpClient)
                .baseUrl(API.URL_HOST_USER)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .addConverterFactory(MyConverter.create())

                .build();

    }

    public static void init(Application application) {
        mContext = application;
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    /**
     * 根据类型创建一个服务
     *
     * @return
     */
    public  <T> T getService(final Class<T> service) {
        return mRetrofit.create(service);
    }



    public void addToken(String token) {
        mToken = token;
    }


}