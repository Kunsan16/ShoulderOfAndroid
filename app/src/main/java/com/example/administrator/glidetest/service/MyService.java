package com.example.administrator.glidetest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.administrator.glidetest.HelloMsg;
import com.example.administrator.glidetest.IRemoteService;

/**
 * Created by moge on 2018/6/14.
 */

public class MyService extends Service {


    private IBinder binder=new IRemoteService.Stub() {
        @Override
        public HelloMsg sayHello() throws RemoteException {
            return new HelloMsg("简单返回一个消息 ");
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
