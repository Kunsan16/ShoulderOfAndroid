package com.example.administrator.glidetest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.example.administrator.glidetest.bean.DataBean;

import java.util.ArrayList;

/**
 * Created by moge on 2018/2/5.
 */

public class MyPieView extends View {


    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 数据
    private ArrayList<DataBean> mData;
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 宽高
    private int mWidth, mHeight;
    private Paint mPaint=new Paint();

    public MyPieView(Context context) {
        this(context, null);
    }

    public MyPieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public MyPieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("自定义View",w+"  "+h);
         mHeight=h;
         mWidth=w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float currentStartAngle=mStartAngle;     //定义当前起始角度
        canvas.translate(mWidth/2,mHeight/2);
        float r= (float) (Math.min(mWidth,mHeight)/2*0.8);

        RectF rectF=new RectF(-r,-r,r,r);

        for (int i = 0; i <mData.size() ; i++) {
            DataBean dataBean=mData.get(i);
            mPaint.setColor(dataBean.getColor());
            canvas.drawArc(rectF,currentStartAngle,dataBean.getAngle(),true,mPaint);
            currentStartAngle+=dataBean.getAngle();
        }
    }

    //起始角度
    public void setStartAngle(int mStartAngle){
         this.mStartAngle=mStartAngle;
         invalidate();
    }

    //设置数据
    public void setData(ArrayList<DataBean> mData){
        this.mData=mData;
        initData(mData);
        invalidate();
    }


    //初始化数据
    private void initData(ArrayList<DataBean> mData) {
       if (null==mData||mData.size()==0){
           return;
       }
       float sumValue=0;
        for (int i = 0; i <mData.size() ; i++) {
            DataBean dataBean=mData.get(i);
            sumValue+=dataBean.getValue();

            int j=i%mColors.length;
            dataBean.setColor(mColors[j]);
        }

        float sumAngle=0;
        for (int i = 0; i <mData.size() ; i++) {
            DataBean dataBean=mData.get(i);

            float percentage=dataBean.getValue()/sumValue;
            float angle=percentage*360;

            dataBean.setPercentage(percentage);
            dataBean.setAngle(angle);
            sumAngle+=angle;

        }

    }
}
