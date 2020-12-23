package com.ghy.api.http;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson,Type type){
        this.gson = gson;
        this.type = type;
    }
    @Override
    public T convert(ResponseBody value)  {
        String response = null;
        try {
            Log.i("GsonResponse",response=value.string());


            //先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
            Response httpResult = gson.fromJson(response, Response.class);
            Log.i("GsonResponseBody1",response);
            return gson.fromJson(response,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("GsonResponseBody1",e.getMessage());
            return gson.fromJson(response,type);
        }


    }


}
