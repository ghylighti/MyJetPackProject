package com.ghy.componentproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ghy.componentproject.aidl.IMyAidlInterface;
import com.ghy.componentproject.aidl.MyClick;


public class AidlService extends Service {
    private String str="默认";
    private MyClick myClick;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyAidl","onBind");
        return iBinder;
    }

    private IBinder iBinder=new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getString() throws RemoteException {
            return str;
        }

        @Override
        public void postString(String s) throws RemoteException {
            str=s;
        }

        @Override
        public MyClick getMyClick() throws RemoteException {
            myClick=new MyClick(str);
            return myClick;
        }

        @Override
        public void postMyClick(MyClick s) throws RemoteException {

        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyAidl","onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyAidl","onDestroy");
    }
}
