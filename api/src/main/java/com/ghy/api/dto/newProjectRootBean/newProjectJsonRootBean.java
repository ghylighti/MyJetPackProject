/**
  * Copyright 2020 json.cn 
  */
package com.ghy.api.dto.newProjectRootBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-21 22:46:11
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class newProjectJsonRootBean implements Parcelable {



    private NewProjectDatas data;
    private int errorCode;
    private String errorMsg;

    protected newProjectJsonRootBean(Parcel in) {
        data = in.readParcelable(NewProjectDatas.class.getClassLoader());
        errorCode = in.readInt();
        errorMsg = in.readString();
    }

    public static final Creator<newProjectJsonRootBean> CREATOR = new Creator<newProjectJsonRootBean>() {
        @Override
        public newProjectJsonRootBean createFromParcel(Parcel in) {
            return new newProjectJsonRootBean(in);
        }

        @Override
        public newProjectJsonRootBean[] newArray(int size) {
            return new newProjectJsonRootBean[size];
        }
    };

    public NewProjectDatas getData() {
        return data;
    }

    public void setData(NewProjectDatas data) {
        this.data = data;
    }
    public void setErrorCode(int errorCode) {
         this.errorCode = errorCode;
     }
     public int getErrorCode() {
         return errorCode;
     }

    public void setErrorMsg(String errorMsg) {
         this.errorMsg = errorMsg;
     }
     public String getErrorMsg() {
         return errorMsg;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
        dest.writeInt(errorCode);
        dest.writeString(errorMsg);
    }
}