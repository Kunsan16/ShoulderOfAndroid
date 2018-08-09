package com.example.administrator.glidetest.view

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ProgressBar
import com.blankj.utilcode.util.LogUtils
import com.example.administrator.glidetest.R

/**
 * Created by moge on 2018/7/9.
 */
class DownLoadButton: ProgressBar {


    private var mPaint= Paint()

    private var mValidWidth:Int = 0
    private var mValidHeight:Int = 0

    private var mProgressPath = Path()

    private var state:Int = STATE_PROGRESS_DEFAILT
//    private val mDefaultBackgroundColor:Int
//    private val mDefaultTextColor:Int
   // private val mLoadingTextColor:Int
//    private int mLoadingBorderColor
//    private int mProgressBarColor
//    private int mInstallColor
//    private float mTextSize
    private var borderRadius : Float = 0f   //边框四个角的角度
    private var borderWidth : Float = 0f    //边框的粗细


   companion object {
       val STATE_PROGRESS_DEFAILT = 0
       val STATE_PROGRESS_DOWNLOADING = 1

   }


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):  super(context, attrs,defStyleAttr) {

        initAttrs(context,attrs)
        initView()
    }

    private fun initAttrs(context: Context,attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.DownloadProgressButton)
        borderRadius = ta.getDimension(R.styleable.DownloadProgressButton_border_radius,12f)
        borderWidth  = ta.getDimension(R.styleable.DownloadProgressButton_border_width,6f)
        ta.recycle()
    }

    private fun initView() {
        mPaint.isAntiAlias=true
        mPaint.textSize = 16f

    }

    init {





//        mDefaultBackgroundColor=ta.getColor(R.styleable.DownloadProgressButton_normal_background_color, ContextCompat.getColor(context,R.color.white))
//        mDefaultTextColor = ta.getColor(R.styleable.DownloadProgressButton_normal_text_color, ContextCompat.getColor(context,R.color.orange_progress_loading));


       // ta.recycle()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mValidWidth = width - paddingLeft - paddingRight
        mValidHeight = height - paddingTop - paddingBottom
    }

    override fun onDraw(canvas: Canvas?) {
      //  super.onDraw(canvas)

        when(state){
            STATE_PROGRESS_DEFAILT -> drawDefaultProgress(canvas)
            STATE_PROGRESS_DOWNLOADING -> drawDownLoadingProgress(canvas)


        }
    }

    private fun drawDownLoadingProgress(canvas: Canvas?) {

        drawRectFBackground(canvas,Color.BLUE)
        drawProgress(canvas)
    }



    var mXfermode =  PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private fun drawProgress(canvas: Canvas?) {

        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.RED
        mPaint.xfermode = mXfermode
        val progress = mValidWidth * (progress * 1.0f / max)
        val layer = canvas!!.saveLayer(0f,0f,progress,height.toFloat(),mPaint)
        canvas.translate(paddingLeft.toFloat(),paddingTop.toFloat())
        drawRectFBackground(canvas,Color.BLUE)


//        drawProgressPath(progress)
//        canvas.drawPath(mProgressPath,mPaint)


        canvas.restoreToCount(layer)
        mPaint.xfermode = null


    }

    private fun drawProgressPath(progress: Float) {

        mProgressPath.reset()
        val rectF = RectF(0f,0f,progress, mValidHeight.toFloat())
        mProgressPath.addRect(rectF,Path.Direction.CCW)

    }

    private fun drawDefaultProgress(canvas: Canvas?) {
        drawRectFBackground(canvas,Color.BLUE)
    }


    private fun drawRectFBackground(canvas: Canvas?,color:Int){

//        canvas!!.save()
//        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = borderWidth
        mPaint.color = color

       val rectf = RectF(borderWidth,borderWidth,mValidWidth.toFloat()-borderWidth,mValidHeight.toFloat()-borderWidth)

        canvas!!.drawRoundRect(rectf,borderRadius,borderRadius,mPaint)

//        canvas.restore()

    }

     fun setState(state:Int){

        this.state=state
        postInvalidateDelayed(10)

    }


}