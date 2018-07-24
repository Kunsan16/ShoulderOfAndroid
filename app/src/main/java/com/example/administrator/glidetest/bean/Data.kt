package com.example.administrator.glidetest.bean

/**
 * Created by moge on 2018/7/24.
 */
data class Data(

         var curPage: Int,

        var offset: Int ,

        var over: Boolean,

        var pageCount: Int ,

        var size: Int ,

        var total: Int ,

        var datas: List<DatasBean>? = null


)





