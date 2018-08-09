package com.example.administrator.glidetest.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.administrator.glidetest.bean.DatasBean
import io.reactivex.subjects.PublishSubject


/**
 * Created by moge on 2018/7/24.
 */
class ArticleDataSourceFactory : DataSource.Factory<Int,DatasBean>(){


    var observableEmitter : PublishSubject<ArticleDataSource> = PublishSubject.create()
    var postLiveData: MutableLiveData<ArticleDataSource>? = null


    override fun create(): DataSource<Int, DatasBean> {
         val dataSource = ArticleDataSource()

        observableEmitter.onNext(dataSource)
        postLiveData = MutableLiveData()
        postLiveData!!.postValue(dataSource)
        return dataSource
    }
}