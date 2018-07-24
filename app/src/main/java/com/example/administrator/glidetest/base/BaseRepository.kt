package com.example.administrator.glidetest.base

import io.reactivex.disposables.Disposable

/**
 * Created by moge on 2018/7/24.
 */
open class BaseRepository{


   internal var mDisposable: Disposable? = null

    fun destory(): Unit {
        if (mDisposable !=null && mDisposable!!.isDisposed){
            mDisposable!!.dispose()        }
    }
}