package com.example.administrator.glidetest.net

import com.example.administrator.glidetest.bean.ArticleListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by moge on 2018/7/24.
 */
interface ApiStore{



    @GET("/article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<ArticleListResponse>


}