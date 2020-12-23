package com.ghy.api.dto.newProjectRootBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Description: <RespDTO><br>
 * Author:    mxdl<br>
 * Date:      2019/2/19<br>
 * Version:    V1.0.0<br>
 * Update:     <br>
 */
public class RespDTO<T> implements Parcelable {

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String errorMsg ;
    public int errorCode;
    public T data;


    @Override
    public String toString() {
        return "RespDTO{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
