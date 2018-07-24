package com.example.administrator.glidetest.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by moge on 2018/7/24.
 */
class RetrofitHelper{

    private val BASE_URL = "http://www.wanandroid.com"

    private val DEFAULT_TIMEOUT :Long = 15

    private var mOkHttpClient:OkHttpClient? = null
    var mRetrofit: Retrofit? = null


    init {
        mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .build()

        mRetrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

    }

    companion object {
        @Volatile
        var mRetrofitHelper:RetrofitHelper? = null

        private fun initRetrofitHelper():RetrofitHelper{
            if (mRetrofitHelper == null){
                synchronized(RetrofitHelper::class.java){
                    if (mRetrofitHelper == null){
                        mRetrofitHelper = RetrofitHelper()
                    }
                }
            }
            return mRetrofitHelper!!
        }

        val instance:Retrofit
        get() = initRetrofitHelper().mRetrofit!!
    }

}