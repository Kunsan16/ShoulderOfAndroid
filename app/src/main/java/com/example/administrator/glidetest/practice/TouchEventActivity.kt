package com.example.administrator.glidetest.practice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.administrator.glidetest.R

/**
 * Created by moge on 2018/6/20.
 */
class TouchEventActivity:AppCompatActivity(){


    companion object {
        fun launch(context: Context){
            val intent= Intent(context, TouchEventActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)
    }


}