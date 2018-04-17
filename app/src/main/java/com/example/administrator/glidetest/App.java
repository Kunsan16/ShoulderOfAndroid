package com.example.administrator.glidetest;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by moge on 2018/4/3.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
