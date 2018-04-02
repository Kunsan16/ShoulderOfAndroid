package com.example.administrator.glidetest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.glidetest.R;


/**
 * Created by moge on 2018/2/6.
 */

public class MyCircleView extends View {

    private Paint mPaint;
    private float mAngle;
    private Rect mRect;
    private OnClickListener listener;
    private int backgroundColor;
    private float textSize;
    private String text;
    private int textColor;

    public void setClickListener(OnClickListener listener){
        this.listener=listener;
    }

    public interface OnClickListener{
        void onClick();
    }

    public MyCircleView(Context context) {
        this(context,null);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {

        mRect =new Rect();
        mPaint=new Paint();
        mPaint.setAntiAlias(true);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();      //view的点击交给调用者去处理，因为你不知道人家要处理什么事情
            }
        });


        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CircleButton);
        backgroundColor = typedArray.getColor(R.styleable.CircleButton_backgroundColor, Color.RED);
        textColor = typedArray.getColor(R.styleable.CircleButton_textColor, Color.WHITE);
        textSize = typedArray.getDimension(R.styleable.CircleButton_textSize, 80);
        text = typedArray.getString(R.styleable.CircleButton_text);
        if (text == null){
            text = "";
        }
        //回收，避免浪费
        typedArray.recycle();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画弧
        mPaint.setColor(backgroundColor);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.FILL);
        Log.i("MyCircleView",getWidth()+"  "+getHeight());
        RectF rectF=new RectF(10,10,getWidth()-10,getHeight()-10);

        canvas.drawArc(rectF,-90,-360+mAngle,true,mPaint);
        Log.i("MyCircleView w h 1",mRect.width()+"  "+mRect.height());


          //drawTextView(canvas);
        drawText(canvas);
    }


    private void drawTextView(Canvas canvas){
        //画文字
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0.1f);
        mPaint.getTextBounds(text,0,text.length(),mRect);

        int textWidth = mRect.width();
        int textHeight = mRect.height();
        Log.i("MyCircleView w h",textWidth+"  "+textHeight);

        canvas.drawText(text, (getWidth()-textWidth) / 2, (getHeight()+textHeight) / 2, mPaint);
    }


    private void drawText(Canvas canvas){
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0.1f);
        canvas.translate(getWidth()/2,getHeight()/2);
        //画X轴
        canvas.drawLine(-getWidth()/2,0,getWidth()/2,0,mPaint);
        //画y轴
        canvas.drawLine(0,-getHeight()/2,0,getHeight()/2,mPaint);

        //x：绘制文本的起始x坐标
       //y：绘制文本的baseline在y轴方向的位置
        canvas.drawText("Google",0,0,mPaint);

    }

    public void setText(String text) {
        invalidate();
        this.text = text;
    }

    public void setAngle(float angle) {
        mAngle = angle;
        invalidate();
    }



    public String getText(){
        return text;
    }
}
