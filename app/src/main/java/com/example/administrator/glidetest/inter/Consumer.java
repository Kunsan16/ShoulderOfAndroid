package com.example.administrator.glidetest.inter;

import android.util.Log;

/**
 * Created by Administrator on 2018/3/19.
 */

public class Consumer implements Buy {

    @Override
    public void buyHouse(long money) {
        Log.i("输出信息","buy it"+money);
    }
}
