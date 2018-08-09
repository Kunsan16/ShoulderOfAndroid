package com.example.administrator.glidetest.practice

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.administrator.glidetest.R
import com.example.administrator.glidetest.adapter.ArticleAdapter
import com.example.administrator.glidetest.repository.ArticleDataSourceFactory
import com.example.administrator.glidetest.repository.ArticleRepository
import com.example.administrator.glidetest.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.layout_jetpack.*

/**
 * Created by moge on 2018/7/24.
 */
class JetPackActivity:AppCompatActivity(){

    companion object {
        fun launch(context:Context){
            val intent= Intent(context, JetPackActivity::class.java)
            context.startActivity(intent)
        }
    }


    private lateinit var mAdapter: ArticleAdapter

    private lateinit var viewModel:ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_jetpack)

        initRecyclerView()

        requestData()
    }

    private fun requestData() {
//        viewModel.getArticleList().observe(this, Observer { result ->
//            result?.let {
//                mAdapter.submitList(it)
//            }
//        })

        viewModel.fetchArticleListByRxJava().subscribe({
            result ->
            result?.let {
                mAdapter.submitList(it)
            }
        })
    }


    private fun initRecyclerView() {

        viewModel = getViewModel()

        val mLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = mLayoutManager
        mAdapter= ArticleAdapter()
        recyclerview.adapter=mAdapter
    }

    private fun getViewModel(): ArticleViewModel {

        return ViewModelProviders.of(this,object :ViewModelProvider.Factory{

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val respository = ArticleRepository(ArticleDataSourceFactory())
                return ArticleViewModel(respository) as T
            }
        })[ArticleViewModel::class.java]

    }


}