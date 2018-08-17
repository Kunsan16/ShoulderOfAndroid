package com.example.administrator.glidetest.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.example.administrator.glidetest.R

/**
 * Created by moge on 2018/8/15.
 */
class CouponView2 : View{


    private var mPaint = Paint()
    private var mPaint1 = Paint()
    private var textPaint = Paint()

    private var mPath  = Path()
    private var mPath2  = Path()
    private var mPath3  = Path()


    private val radius : Int = 20
    private var mValidWidth:Int = 0
    private var mValidHeight:Int = 0
    private var oval:RectF? = null
    private var rect:RectF? = null
    private var mBitmap : Bitmap? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):  super(context, attrs,defStyleAttr) {

        mPaint.isAntiAlias = true
        mPaint.color = Color.parseColor("#f28723")
        mPaint.strokeWidth=4f
        mPaint.style = Paint.Style.FILL_AND_STROKE

        mPaint1.color = Color.parseColor("#ffffff")
        mPaint1.strokeWidth=4f
        mPaint1.style = Paint.Style.FILL_AND_STROKE

        textPaint.color = Color.WHITE
        textPaint.textSize =66f

        mBitmap= BitmapFactory.decodeResource(resources, R.mipmap.ic_ticket_employ)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mValidWidth = width - paddingLeft - paddingRight
        mValidHeight = height - paddingTop - paddingBottom
        oval = RectF(0f, 0f, mValidWidth/3.toFloat(), mValidHeight.toFloat())
        rect = RectF(0f, 300f, mValidWidth.toFloat(), 400f)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        drawRect(canvas)
        drawText(canvas)

    }



    private fun drawRect(canvas: Canvas?) {
        mPath.addRect(oval, Path.Direction.CW)

        mPath2.addCircle(0f,mValidHeight.toFloat()/2,28f, Path.Direction.CCW)
        mPath.op(mPath2, Path.Op.DIFFERENCE)

        mPath3.moveTo(mValidWidth.toFloat()/3,0f)
        mPath3.lineTo(mValidWidth.toFloat(),0f)
        mPath3.lineTo(mValidWidth.toFloat(),mValidHeight.toFloat())
        mPath3.lineTo(mValidWidth.toFloat()/3,mValidHeight.toFloat())


        mPath2.addCircle(mValidWidth.toFloat(),mValidHeight.toFloat()/2,28f, Path.Direction.CCW)
        mPath3.op(mPath2, Path.Op.DIFFERENCE)

        canvas!!.drawPath(mPath,mPaint)
        canvas.drawPath(mPath3,mPaint1)
        //canvas.drawRect(rect,mPaint)
    }

    private fun drawText(canvas: Canvas?) {


        val textHeight = textPaint.descent() + textPaint.ascent()

        val text  ="¥ 16"

        val textWidth = textPaint.measureText(text)
        textPaint.textSize = 42f

        canvas!!.drawText(text,0,1,(mValidWidth.toFloat() / 3)/2-textWidth/2,mValidHeight.toFloat() / 2,textPaint)
        textPaint.textSize = 108f
        canvas.drawText(text,2,4,(mValidWidth.toFloat() / 3)/2-textWidth/2+20,mValidHeight.toFloat() / 2,textPaint)
        textPaint.textSize = 42f

        canvas.drawText("满60元使用",(mValidWidth.toFloat() / 3)/2-textPaint.measureText("满60元使用")/2,mValidHeight.toFloat()-40,textPaint)
        textPaint.color = Color.BLACK
        textPaint.textSize = SizeUtils.sp2px(16f).toFloat()
        canvas.drawText("肯德基优惠券",mValidWidth.toFloat() / 3,mValidHeight.toFloat()/2,textPaint)
        textPaint.textSize = SizeUtils.sp2px(12f).toFloat()
        val textHeight1 = textPaint.descent() + textPaint.ascent()
        canvas.drawText("2018.08.08到期",mValidWidth.toFloat() / 3,mValidHeight.toFloat()/2-textHeight1+30,textPaint)
        canvas.drawBitmap(mBitmap,mValidWidth.toFloat()-mValidWidth.toFloat() / 4,20f,null)

    }
}