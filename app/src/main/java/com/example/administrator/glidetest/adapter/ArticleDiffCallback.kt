package com.example.administrator.glidetest.adapter

import android.support.v7.util.DiffUtil
import com.example.administrator.glidetest.bean.DatasBean

/**
 * Created by moge on 2018/7/24.
 */
class ArticleDiffCallback:DiffUtil.ItemCallback<DatasBean>(){


    override fun areContentsTheSame(oldItem: DatasBean?, newItem: DatasBean?): Boolean {
        return oldItem!!.id == newItem!!.id
    }

    override fun areItemsTheSame(oldItem: DatasBean?, newItem: DatasBean?): Boolean {
        return oldItem == newItem
    }


}