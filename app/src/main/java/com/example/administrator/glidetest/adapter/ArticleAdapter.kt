package com.example.administrator.glidetest.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administrator.glidetest.R


import com.example.administrator.glidetest.bean.DatasBean
import com.example.administrator.glidetest.glide.ImageLoader
import kotlinx.android.synthetic.main.item_article.*
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * Created by moge on 2018/7/24.
 */
class ArticleAdapter:PagedListAdapter<DatasBean, ArticleAdapter.MyViewHolder>(ArticleDiffCallback()){

    var itemClick: (View, DatasBean) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val entity:DatasBean? = getItem(position)
        if (entity != null){
            holder.bindView(position,entity,itemClick)
        }
    }

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){



          fun bindView(position: Int,item:DatasBean,itemClick: (View, DatasBean) -> Unit){

              with(item){
                 with(itemView){
                     art_title?.text=title
                 }
              }
          }



    }
}