package com.example.administrator.glidetest.repository

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.example.administrator.glidetest.base.BaseRepository
import com.example.administrator.glidetest.bean.ArticleListResponse
import com.example.administrator.glidetest.bean.DatasBean
import com.example.administrator.glidetest.bean.WanandroidBean
import com.example.administrator.glidetest.repository.ArticleDataSource.Companion.DEF_PAGE_SIZE
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by moge on 2018/7/24.
 */
class ArticleRepository constructor(var articleFactory:ArticleDataSourceFactory):BaseRepository(){



    fun getArticleList(): Observable<PagedList<DatasBean>> {

        var config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(DEF_PAGE_SIZE)
                .setPageSize(DEF_PAGE_SIZE)
                .build()

        val articleObservable: Observable<PagedList<DatasBean>> = RxPagedListBuilder(articleFactory,config)
                .setInitialLoadKey(1)
                .setFetchScheduler(Schedulers.io())
                .setNotifyScheduler(AndroidSchedulers.mainThread())
                .buildObservable()
        return articleObservable
    }



}