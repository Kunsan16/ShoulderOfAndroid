package com.example.administrator.glidetest.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.blankj.utilcode.util.LogUtils

/**
 * Created by moge on 2018/6/20.
 */
class MyRelativeLayout(context: Context?) : RelativeLayout(context) {


    constructor(context: Context,attrs: AttributeSet): this(context) {


    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
               // parent.requestDisallowInterceptTouchEvent(true)
                LogUtils.i("事件分发测试2 按下 MyRelativeLayout 1")
            }
            MotionEvent.ACTION_MOVE ->{
               // parent.requestDisallowInterceptTouchEvent(false)
                LogUtils.i("事件分发测试2 移动 MyRelativeLayout 1")
            }

        }

        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {



        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event?.action){
            MotionEvent.ACTION_DOWN ->{

                LogUtils.i("事件分发测试2 按下 MyRelativeLayout")
            }
            MotionEvent.ACTION_MOVE ->{
                LogUtils.i("事件分发测试2 移动 MyRelativeLayout")
            }

        }
        return false
    }

}