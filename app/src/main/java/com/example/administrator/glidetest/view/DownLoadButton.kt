package com.example.administrator.glidetest.view

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.blankj.utilcode.util.LogUtils
import com.example.administrator.glidetest.R

/**
 * Created by moge on 2018/7/9.
 */
class DownLoadButton: ProgressBar,View.OnClickListener {


    private var mPaint= Paint()
    private var textPaint = Paint()//文字画笔

    private var mValidWidth:Int = 0
    private var mValidHeight:Int = 0

    private var mProgressPath = Path()

    private var mCurrentState:Int = STATE_PROGRESS_DEFAULT


    private var mBorderColor : Int = Color.BLUE         //边框颜色
    private var mBorderRadius : Float = 0f              //边框四个角的角度
    private var mBorderWidth : Float = 0f               //边框的粗细
    private var mTextSize : Float = 0f                  //文字大小
    private var mProgressBarColor : Int = Color.RED     //进度条颜色
    private var showPercent : Boolean = true            //是否显示百分比（默认显示百分比）

   companion object {
       val STATE_PROGRESS_DEFAULT = 0
       val STATE_PROGRESS_DOWNLOADING = 1
       val STATE_PROGRESS_PAUSE = 2
       val STATE_PROGRESS_FINISH = 3

   }


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):  super(context, attrs,defStyleAttr) {

        initAttrs(context,attrs)
        initView()

        setOnClickListener(this)
    }

    private fun initAttrs(context: Context,attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.DownloadProgressButton)
        mBorderRadius     = ta.getDimension(R.styleable.DownloadProgressButton_border_radius,12f)
        mBorderWidth      = ta.getDimension(R.styleable.DownloadProgressButton_border_width,6f)
        mBorderColor      = ta.getColor(R.styleable.DownloadProgressButton_border_color,Color.BLUE)
        mTextSize         = ta.getDimension(R.styleable.DownloadProgressButton_progress_textSize, 46f)
        mProgressBarColor = ta.getColor(R.styleable.DownloadProgressButton_loading_progress_color,Color.RED)
        showPercent       = ta.getBoolean(R.styleable.DownloadProgressButton_percent_show,true)
        ta.recycle()
    }

    private fun initView() {
        mPaint.isAntiAlias=true
        mPaint.textSize = 16f


        textPaint.textSize = mTextSize

    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mValidWidth = width - paddingLeft - paddingRight
        mValidHeight = height - paddingTop - paddingBottom
    }

    override fun onDraw(canvas: Canvas) = when(mCurrentState){

        STATE_PROGRESS_DEFAULT       ->     drawDefaultProgress(canvas)
        STATE_PROGRESS_DOWNLOADING   ->     drawDownLoadingProgress(canvas)
        STATE_PROGRESS_PAUSE         ->     drawPause(canvas)
        STATE_PROGRESS_FINISH        ->     drawFinish(canvas)

        else -> {
        }
    }

    override fun onClick(v: View?) {


            if (progress == 0 && mCurrentState == STATE_PROGRESS_DEFAULT) {
                //默认状态到开始下载
                mCurrentState = STATE_PROGRESS_DOWNLOADING
                mStateChangeListener.onLoadingTask()
            }else if (progress in 0..max && mCurrentState == STATE_PROGRESS_DOWNLOADING){
                //暂停
                mCurrentState = STATE_PROGRESS_PAUSE
                mStateChangeListener.onPauseTask()
            }else if (progress in 0..max && mCurrentState == STATE_PROGRESS_PAUSE){
                //继续下载
                mCurrentState = STATE_PROGRESS_DOWNLOADING
                mStateChangeListener.onLoadingTask()
            }else if (progress == max && mCurrentState == STATE_PROGRESS_FINISH){
                //下载完成
                mCurrentState = STATE_PROGRESS_FINISH
                mStateChangeListener.onFinishTask()
            }




    }

    /**
     * 绘制下载完成
     */
    private fun drawFinish(canvas: Canvas) {
        drawRectFBackground(canvas,mBorderColor)
        drawProgressText(canvas,"安装")
    }

    /**
     * 绘制下载暂停
     */
    private fun drawPause(canvas: Canvas) {
        drawRectFBackground(canvas,mBorderColor)
        drawProgress(canvas)
        drawProgressText(canvas,"继续")
        drawGradientText(canvas)
    }

    /**
     * 下载中的进度显示
     */
    private fun drawDownLoadingProgress(canvas: Canvas) {

        drawRectFBackground(canvas,mBorderColor)
        drawProgress(canvas)
        drawProgressText(canvas,if (showPercent)"" else "暂停")
        drawGradientText(canvas)
        if (progress == max){
            mCurrentState = STATE_PROGRESS_FINISH
            postInvalidateDelayed(20)
        }
    }


    /**
     * PorterDuffXfermode绘制进度条
     */
    private fun drawProgress(canvas: Canvas?) {

        mPaint.style = Paint.Style.FILL
        val progress = mValidWidth * (progress * 1.0f / max)
        val layer = canvas!!.saveLayer(0f,0f,progress,height.toFloat(),mPaint)
        //  canvas.translate(paddingLeft.toFloat(),paddingTop.toFloat())
        drawRoundRectPath(canvas)
        mPaint.color = Color.RED

        drawProgressPath(progress)  //绘制src层
        mPaint.xfermode = mXfermode
        canvas.drawPath(mProgressPath,mPaint)   //绘制与dst层的重叠区域
        canvas.restoreToCount(layer)
        mPaint.xfermode = null


    }

    /**
     * 绘制矩形新图层
     */
    private fun drawRoundRectPath(canvas: Canvas) {

        val mRectF = RectF(mBorderWidth+2,mBorderWidth+2,mValidWidth.toFloat()-mBorderWidth-2,mValidHeight.toFloat()-mBorderWidth-2)
        canvas.drawRoundRect(mRectF, mBorderRadius, mBorderRadius, mPaint)

    }

    /**
     * 绘制src图层，也就是进度
     */
    private fun drawProgressPath(progress: Float) {

        mProgressPath.reset()
        val rectF = RectF(0f,0f,progress, mValidHeight.toFloat())
        mProgressPath.addRect(rectF,Path.Direction.CCW)

    }


    private fun drawDefaultProgress(canvas: Canvas?) {
        drawRectFBackground(canvas,mBorderColor)
        drawProgressText(canvas,"下载")
    }


    private fun drawRectFBackground(canvas: Canvas?,color:Int){

//        canvas!!.save()
//        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mBorderWidth
        mPaint.color = color

        val rectf = RectF(mBorderWidth,mBorderWidth,mValidWidth.toFloat()-mBorderWidth,mValidHeight.toFloat()-mBorderWidth)

        canvas!!.drawRoundRect(rectf,mBorderRadius,mBorderRadius,mPaint)

//        canvas.restore()

    }

    /**
     * 绘制进度文本
     */
    private fun drawProgressText(canvas: Canvas?,text:String) {

        textPaint.color = mProgressBarColor

        val progressText = if (showPercent && TextUtils.isEmpty(text)) getPercent() else text
        val textWidth = textPaint.measureText(progressText)
        val textHeight = textPaint.descent() + textPaint.ascent()
        canvas!!.drawText(progressText,mValidWidth / 2 - textWidth / 2,mValidHeight / 2 - textHeight / 2,textPaint)


    }

    /**
     * 绘制变色文本
     */
    private fun drawGradientText(canvas: Canvas){
        textPaint.color = Color.WHITE
        val progressText = if (showPercent) getPercent() else "暂停"
        val textWidth = textPaint.measureText(progressText)
        val textHeight = textPaint.descent() + textPaint.ascent()
        val xCoordinate = (measuredWidth - textWidth) / 2
        val yCoordinate = mValidHeight / 2 - textHeight / 2
        val progressWidth = (progress - 100 / max) * measuredWidth

        if (progressWidth / 100 > xCoordinate)
            //canvas.save()
            canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
            canvas.clipRect(xCoordinate,0f,(progressWidth / 100).toFloat(),measuredHeight.toFloat())
            canvas.drawText(progressText,xCoordinate,yCoordinate,textPaint)
            //canvas.restore()
    }





    /**
     * 获取当前进度百分比
     */
    private fun getPercent():String{
        return TextUtils.concat(calculatePercent().toString(),"%").toString()
    }
    /**
     * 计算进度百分比
     */
    private fun calculatePercent():Int{
        return (100 * (progress * 1.0f / max)).toInt()
    }

    private var mXfermode =  PorterDuffXfermode(PorterDuff.Mode.SRC_IN)


    /**
     * 更新下载按钮当前下载状态
     */
    fun setState(state:Int){

        this.mCurrentState=state
        postInvalidateDelayed(10)

    }



     private lateinit var mStateChangeListener : StateChangeListener

     fun setStateChangeListener(mStateChangeListener:StateChangeListener){

           this.mStateChangeListener = mStateChangeListener
     }

     interface StateChangeListener  {

          fun onPauseTask()        //暂停下载

          fun onFinishTask()       //下载完成

          fun onLoadingTask()      //开始下载

          fun onOpenGame()         //启动游戏

          fun onError()            //下载出错
     }



}