package com.ghy.componentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ghy.componentproject.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(iMyAidlInterface!=null)
                    {
                        Log.i("MyAidl", iMyAidlInterface.getString()+iMyAidlInterface.getMyClick().getData());
                        button.setText(iMyAidlInterface.getString());
                    }else
                    {
                        Toast.makeText(MainActivity.this,R.string.tips,Toast.LENGTH_LONG).show();
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
       bindService();
    }
    private void bindService() {
        Intent intent=new Intent();
        intent.setComponent(new ComponentName("com.ghy.componentproject","com.ghy.componentproject.AidlService"));
        intent.setPackage("com.ghy.componentproject");
       bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("MyAidl","onServiceConnected");
            iMyAidlInterface= IMyAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MyAidl","onServiceDisconnected");
        }
    };


}