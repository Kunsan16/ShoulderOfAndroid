package com.example.administrator.glidetest.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ProgressBar
import com.example.administrator.glidetest.R

/**
 * Created by moge on 2018/7/9.
 */
class DownLoadButton(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : ProgressBar(context, attrs, defStyleAttr) {


    private var mPaint= Paint()

//    private val mDefaultBackgroundColor:Int
//    private val mDefaultTextColor:Int
   // private val mLoadingTextColor:Int
//    private int mLoadingBorderColor
//    private int mProgressBarColor
//    private int mInstallColor
//    private float mTextSize
//    private int radius
   companion object {
       val STATE_PROGRESS_DEFAILT = 0
       val STATE_PROGRESS_DOWNLOADING = 1
   }

    init {

        mPaint.isAntiAlias=true


        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.DownloadProgressButton)
//        mDefaultBackgroundColor=ta.getColor(R.styleable.DownloadProgressButton_normal_background_color, ContextCompat.getColor(context,R.color.white))
//        mDefaultTextColor = ta.getColor(R.styleable.DownloadProgressButton_normal_text_color, ContextCompat.getColor(context,R.color.orange_progress_loading));


        ta.recycle()
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}