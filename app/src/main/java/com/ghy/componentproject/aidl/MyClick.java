package com.ghy.componentproject.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyClick implements Parcelable {
    protected MyClick(Parcel in) {
        data = in.readString();
    }

    public static final Creator<MyClick> CREATOR = new Creator<MyClick>() {
        @Override
        public MyClick createFromParcel(Parcel in) {
            return new MyClick(in);
        }

        @Override
        public MyClick[] newArray(int size) {
            return new MyClick[size];
        }
    };

    public MyClick(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
    }


}
