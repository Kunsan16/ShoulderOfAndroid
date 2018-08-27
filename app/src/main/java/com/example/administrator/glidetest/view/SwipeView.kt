package com.example.administrator.glidetest.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.*
import android.view.animation.OvershootInterpolator
import com.blankj.utilcode.util.LogUtils

/**
 * Created by Administrator on 2018/8/25.
 */
class SwipeView : ViewGroup{


    private var mLastP = PointF()
    private var mFirstP = PointF()

    private var mVelocityTracker: VelocityTracker? = null//滑动速度变量
    private var mMaxVelocity: Int = 0//计算滑动速度用
    private var mPointerId: Int = 0//多点触摸只算第一根手指的速度

    private var mContentView: View? = null
    private var mHeight: Int = 0//自己的高度
    //右侧菜单宽度总和(最大滑动距离)
    private var mRightMenuWidths: Int = 0

    //存储的是当前正在展开的View
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mViewCache: SwipeView? = null
    }


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):  super(context, attrs,defStyleAttr) {

        mMaxVelocity = ViewConfiguration.get(context).scaledMaximumFlingVelocity
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        isClickable = true
        mHeight = 0
        var contentWidth = 0    //第一个控件宽度作为整个item宽度
        mRightMenuWidths = 0   //viewHolder复用机制
        val childCount = childCount
        for (i in 0 until childCount){

            val childView =getChildAt(i)
            childView.isClickable = true
            if (childView.visibility != View.GONE){
                measureChild(childView,widthMeasureSpec,heightMeasureSpec)
                mHeight = Math.max(mHeight, childView.measuredHeight)
                if (i > 0){  //child第一个肯定不是删除键
                    mRightMenuWidths += childView.measuredWidth
                }else{
                    mContentView = childView
                    contentWidth = childView.measuredWidth
                }
            }
        }
        setMeasuredDimension(paddingLeft+paddingRight+contentWidth,mHeight+
                paddingTop+paddingBottom)

    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        LogUtils.i("侧滑删除 onLayout ")
        val childCount = childCount
        var left = paddingLeft
        for (i in 0 until childCount){
            val childView =getChildAt(i)
            if (childView.visibility != View.GONE){
                if (i ==0 ){
                    childView.layout(left,paddingTop,left+childView.measuredWidth,
                            paddingTop + childView.measuredHeight)
                    left += childView.measuredWidth
                }else{
                    childView.layout(left, paddingTop, left + childView.measuredWidth, paddingTop + childView.measuredHeight)
                    left += childView.measuredWidth
                }

            }
        }
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        LogUtils.i("侧滑删除 mViewCache "+mViewCache)
        //追踪手指滑动速度
        acquireVelocityTracker(ev)
        val verTracker = mVelocityTracker
        when(ev.action){
            MotionEvent.ACTION_DOWN ->{
                LogUtils.i("侧滑删除 ACTION_DOWN "+ev.rawX)
                mLastP.set(ev.rawX,ev.rawY)  //
                mPointerId = ev.getPointerId(0)

                if (mViewCache != null) {

                    if (mViewCache != this) {
                        mViewCache?.smoothClose()

                    }
                    //只要有一个侧滑菜单处于打开状态， 就不给外层布局上下滑动了
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }

            MotionEvent.ACTION_MOVE ->{

                val gap = mLastP.x - ev.rawX
                LogUtils.i("侧滑删除 ACTION_MOVE "+gap)
                if (Math.abs(gap) > 10 || Math.abs(scrollX) > 10){    //左滑了一些距离
                    parent.requestDisallowInterceptTouchEvent(true) //父布局不要管，我自己来
                }
                scrollBy(gap.toInt(),0)
                if (scrollX < 0) {
                    scrollTo(0, 0)
                }
                if (scrollX > mRightMenuWidths) {
                    scrollTo(mRightMenuWidths, 0)
                }
                mLastP.set(ev.rawX, ev.rawY)
            }

            MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_UP ->{
                LogUtils.i("侧滑删除 ACTION_CANCEL ")
                verTracker?.computeCurrentVelocity(1000,mMaxVelocity.toFloat())
                val velocityX = verTracker?.getXVelocity(mPointerId)
                if (velocityX!! < -1000){
                    smoothExpand()
                }else{
                    smoothClose()
                }
            }

        }

        return super.dispatchTouchEvent(ev)
    }

    private fun smoothClose() {
        LogUtils.i("侧滑删除 smoothClose ")

        mViewCache = null
        cancelAnim()
        mCloseAnim = ValueAnimator.ofInt(scrollX,0)
        mCloseAnim?.addUpdateListener{ animation -> scrollTo(animation.animatedValue as Int,0)  }
        mCloseAnim?.interpolator = OvershootInterpolator() as TimeInterpolator?
        mCloseAnim?.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {

            }
        })
        mCloseAnim?.duration = 300
        mCloseAnim?.start()
    }

    private var mExpandAnim:ValueAnimator? = null
    private var mCloseAnim: ValueAnimator? = null

    private fun smoothExpand() {

        mViewCache = this@SwipeView
        LogUtils.i("侧滑删除 smoothExpand $mViewCache")
        cancelAnim()
        mExpandAnim = ValueAnimator.ofInt(scrollX,mRightMenuWidths)
        mExpandAnim?.addUpdateListener { animation -> scrollTo(animation.animatedValue as Int,0) }
        mExpandAnim?.interpolator = OvershootInterpolator()
        mExpandAnim?.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {

            }
        })
        mExpandAnim?.duration = 300
        mExpandAnim?.start()
    }

    private fun cancelAnim() {

        if (mCloseAnim != null && mCloseAnim!!.isRunning) {
            mCloseAnim?.cancel()
        }
        if (mExpandAnim != null && mExpandAnim!!.isRunning) {
            mExpandAnim?.cancel()
        }
    }

    private fun acquireVelocityTracker(ev: MotionEvent) {


          mVelocityTracker = mVelocityTracker ?: VelocityTracker.obtain()
          mVelocityTracker?.addMovement(ev)
    }


}