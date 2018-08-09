package com.example.administrator.glidetest.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.paging.PagedList
import com.example.administrator.glidetest.bean.DatasBean

import com.example.administrator.glidetest.repository.ArticleRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by moge on 2018/7/24.
 */
class ArticleViewModel constructor(private var articleRepository: ArticleRepository):BaseViewModel(articleRepository){



    fun getArticleList():LiveData<PagedList<DatasBean>>{
        val result = articleRepository
                .getArticleList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable(BackpressureStrategy.BUFFER)

        return LiveDataReactiveStreams.fromPublisher(result)
    }


    fun fetchArticleListByRxJava(): Flowable<PagedList<DatasBean>> {
        val result = articleRepository
                .getArticleList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable(BackpressureStrategy.BUFFER)

        return result
    }
}