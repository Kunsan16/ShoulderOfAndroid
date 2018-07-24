package com.example.administrator.glidetest.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import com.blankj.utilcode.util.LogUtils

/**
 * Created by moge on 2018/6/20.
 */
class MyLinearlayout(context: Context?) : LinearLayout(context) {



    constructor(context: Context,attrs: AttributeSet): this(context) {


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        LogUtils.i("事件分发测试1 分发事件")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                LogUtils.i("事件分发测试1 拦截按下")
               // return false

            }
            MotionEvent.ACTION_MOVE ->{
                LogUtils.i("事件分发测试1 拦截移动")
               // return true
            }
            MotionEvent.ACTION_UP ->{
                LogUtils.i("事件分发测试1 拦截移开")
              //  return true
            }
        }

        return super.onInterceptTouchEvent(ev)       //返回true   后续move、up等事件不再传递给onInterceptTouchEvent方法，直接交由onTouchEvent处理
    }                      //返回false  后续down，move，up都会经过这里，并分发给下级

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                LogUtils.i("事件分发测试1 onTouchEvent按下")

            }
            MotionEvent.ACTION_MOVE ->{
                LogUtils.i("事件分发测试1 onTouchEvent移动")
            }
            MotionEvent.ACTION_CANCEL ->{
                LogUtils.i("事件分发测试1 onTouchEvent移开")
            }
        }
        return false  /*
                      onTouchEvent返回true，父ViewGroup派发过来的touch事件已被该View消费，
                      不会再向上传递给父ViewGroup；后续的touch事件都将继续传递给View

                      onTouchEvent返回false，表明View并不消费父ViewGroup传递来的down事件，
                      而是向上传递给父ViewGroup来处理；后续的move、up等事件将不再传递给View，
                      直接由父ViewGroup处理掉
                     */
    }
}