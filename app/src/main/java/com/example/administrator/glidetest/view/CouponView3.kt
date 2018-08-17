package com.example.administrator.glidetest.view

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.example.administrator.glidetest.R

/**
 * Created by moge on 2018/8/17.
 */
class CouponView3 : ConstraintLayout{


    private var mPaint = Paint()
    private var mPaint1 = Paint()
    private var mPaintCircle = Paint(ANTI_ALIAS_FLAG)
    private var mPaintText = Paint(ANTI_ALIAS_FLAG)

    /**
     * 阴影的颜色, 需要带透明
     */
    private val mShadowColor = Color.argb(128, 249, 94, 94)

    /**
     * 阴影的大小范围 radius越大越模糊，越小越清晰
     */
    private val mShadowRadius = 10f

    /**
     * 阴影的宽度，比如底部的阴影，那就是底部阴影的高度
     */
    private val mShadowWidth = 15f

    /**
     * 阴影 x 轴的偏移量, 计算padding时需要计算在内
     */
    private val mShadowDx = 0f

    /**
     * 阴影 y 轴的偏移量，计算padding时需要计算在内，比如想底部的阴影多一些，这个设置值就可以了
     */
    private val mShadowDy = 5f

    /**
     * 底部文本高度
     */
    private val mBottomHeight = SizeUtils.sp2px(28f)

    private var mValidWidth:Int = 0
    private var mValidHeight:Int = 0

    private var oval:RectF? = null
    private var rightRect:RectF? = null
    private var bottomRect:RectF =RectF()
    private var mBitmap : Bitmap? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):  super(context, attrs,defStyleAttr) {

        setWillNotDraw(false)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        mPaint.isAntiAlias = true
        mPaint.color = Color.parseColor("#f28723")
        mPaint.strokeWidth=4f
        mPaint.style = Paint.Style.FILL_AND_STROKE

        mPaint1.color = Color.parseColor("#ffffff")
        mPaint1.strokeWidth=4f
        mPaint1.style = Paint.Style.FILL_AND_STROKE


        mPaintCircle.color = Color.TRANSPARENT
        mPaintCircle.style = Paint.Style.FILL_AND_STROKE
        mPaintCircle.xfermode = mXfermode

        mBitmap= BitmapFactory.decodeResource(resources, R.mipmap.ic_ticket_employ)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mValidWidth = width
        mValidHeight = height
        oval = RectF(0f, 0f, w/3.toFloat(), h.toFloat()-mBottomHeight)
        rightRect = RectF(w/3.toFloat(), 0f, w.toFloat(), h.toFloat()-mBottomHeight)
     //   bottomRect = RectF(0f, h.toFloat()-60, w.toFloat(), h.toFloat()-10)
    }

    private var mXfermode =  PorterDuffXfermode(PorterDuff.Mode.SRC_IN)


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        paddingBottomlayout()
        layoutBottomText()

        LogUtils.i("onLayout "+getChildAt(childCount-1))


    }

    private fun paddingBottomlayout() {
        val rectLeft = 0f
        var rectBottom = 0f
        val paddingLeft = 0
        val paddingTop = 0
        var paddingRight = 0
        var paddingBottom = 0


        paddingRight = (mShadowWidth  + mShadowDx).toInt()

        rectBottom = this.height - mShadowWidth - mShadowDy
        paddingBottom = (mShadowWidth  + mShadowDy).toInt()

        bottomRect.left = rectLeft
        bottomRect.top = mValidHeight.toFloat()-mBottomHeight
        bottomRect.right = mValidWidth.toFloat()
        bottomRect.bottom = rectBottom
        this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
    }

    private fun layoutBottomText() {
        val view = getChildAt(childCount-1) as TextView
        view.height = mBottomHeight
        view.setPadding(0,0,0,mShadowDy.toInt())
        view.gravity = Gravity.BOTTOM
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        LogUtils.i("onDraw  调用")

        canvas!!.drawRect(oval,mPaint)
        canvas.drawRect(rightRect,mPaint1)

        canvas.drawCircle(0f,(mValidHeight-mBottomHeight).toFloat()/2,20f,mPaintCircle)
        canvas.drawCircle(mValidWidth.toFloat(),(mValidHeight-mBottomHeight).toFloat()/2,20f,mPaintCircle)

        mPaint1.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, Color.argb(128, 80, 80, 80) )
        canvas.drawRect(bottomRect,mPaint1)
        mPaint1.clearShadowLayer()
        canvas.drawBitmap(mBitmap,mValidWidth.toFloat()-mValidWidth.toFloat() / 4,20f,null)
    }
}