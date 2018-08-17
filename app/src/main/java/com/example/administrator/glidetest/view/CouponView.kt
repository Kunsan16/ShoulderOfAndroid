package com.example.administrator.glidetest.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.R.attr.path
import android.graphics.*
import android.support.constraint.ConstraintLayout
import android.widget.RelativeLayout
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.example.administrator.glidetest.R
import android.graphics.BlurMaskFilter




/**
 * Created by moge on 2018/8/14.
 */
class CouponView : ConstraintLayout{


    private var mPaint = Paint()
    private var mPaint1 = Paint()

    private var mPath  = Path()
    private var mPath2  = Path()
    private var mPath3  = Path()


    private val radius : Int = 20
    private var mValidWidth:Int = 0
    private var mValidHeight:Int = 0
    private var oval:RectF? = null
    private var rect:RectF? = null
    private var rect2:RectF? = null
    private var mBitmap : Bitmap? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):  super(context, attrs,defStyleAttr) {
        setWillNotDraw(false)
        initView()
    }

    private fun initView() {


        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
         mPaint.isAntiAlias = true
         mPaint.color = Color.parseColor("#f28723")
         mPaint.strokeWidth=4f
         mPaint.style = Paint.Style.FILL_AND_STROKE

        mPaint1.color = Color.parseColor("#ffffff")
        mPaint1.strokeWidth=4f
        mPaint1.style = Paint.Style.FILL_AND_STROKE


        mBitmap= BitmapFactory.decodeResource(resources, R.mipmap.ic_ticket_employ)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mValidWidth = width - paddingLeft - paddingRight
        mValidHeight = height - paddingTop - paddingBottom
        oval = RectF(0f, 0f, mValidWidth/3.toFloat(), mValidHeight.toFloat())
        rect = RectF(0f, 300f, mValidWidth.toFloat(), 400f)
        LogUtils.i("宽高度 $mValidHeight")

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


       mPath.addRect(oval,Path.Direction.CW)

//
        mPath2.addCircle(0f,mValidHeight.toFloat()/2,20f,Path.Direction.CCW)
        mPath.op(mPath2,Path.Op.DIFFERENCE)

        mPath3.moveTo(mValidWidth.toFloat()/3,0f)
        mPath3.lineTo(mValidWidth.toFloat(),0f)
        mPath3.lineTo(mValidWidth.toFloat(),mValidHeight.toFloat())

        mPath3.lineTo(mValidWidth.toFloat()/3,mValidHeight.toFloat())

        mPath2.addCircle(mValidWidth.toFloat(),mValidHeight.toFloat()/2,20f,Path.Direction.CCW)
        mPath3.op(mPath2,Path.Op.DIFFERENCE)


        canvas!!.drawPath(mPath,mPaint)
        canvas.drawPath(mPath3,mPaint1)
        //canvas.drawRect(rect,mPaint)
        canvas.drawBitmap(mBitmap,mValidWidth.toFloat()-mValidWidth.toFloat() / 4,20f,null)

    }
}