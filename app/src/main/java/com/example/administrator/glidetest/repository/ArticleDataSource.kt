package com.example.administrator.glidetest.repository

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.example.administrator.glidetest.bean.DatasBean
import com.example.administrator.glidetest.net.ApiStore
import com.example.administrator.glidetest.net.RetrofitHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by moge on 2018/7/24.
 */
class ArticleDataSource :PageKeyedDataSource<Int,DatasBean>(){


    private var mDisposables:CompositeDisposable = CompositeDisposable()

    companion object {
        const val DEF_PAGE_SIZE = 20
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DatasBean>) {
        Log.d("PageKeyedDataSource","loadBefore")
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DatasBean>) {

          val disposable = RetrofitHelper.instance
                  .create(ApiStore::class.java)
                  .getArticleList(1)
                  .subscribeOn(Schedulers.io())
                  .subscribe({
                      callback.onResult(it.data.datas!!,1,2)
                  })
          mDisposables.add(disposable)


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DatasBean>) {

        val disposable = RetrofitHelper.instance
                .create(ApiStore::class.java)
                .getArticleList(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(it.data.datas!!,params.key+1)
                })
        mDisposables.add(disposable)
    }


    override fun invalidate() {
        super.invalidate()
        mDisposables.dispose()
    }

}