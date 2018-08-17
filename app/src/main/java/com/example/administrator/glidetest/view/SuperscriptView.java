package com.example.administrator.glidetest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.administrator.glidetest.R;


/**
 * 倾斜的TextView
 * use 应用角标
 */

public class SuperscriptView extends android.support.v7.widget.AppCompatTextView {

    private Paint mPaint;

    private RectF rectF;

    private Path mPath;

    private int width;
    private int height;

    public SuperscriptView(Context context) {
        super(context);
    }

    public SuperscriptView(Context context, AttributeSet attrs) {
        super(context, attrs);

         mPaint = new Paint();
         mPaint.setStyle(Paint.Style.FILL);
         mPath  = new Path();
         mPaint.setColor(context.getResources().getColor(R.color.orange));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF = new RectF(0,0,w,h);

        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //倾斜度45,上下左右居中
       // canvas.drawArc(rectF,270,90,false,mPaint);

        mPath.moveTo(width/3,0);
        mPath.lineTo(width-50,0);
      //  mPath.arcTo (rectF,270,45,true);
        mPath.quadTo(width,0,width,50);
      //  mPath.moveTo(width,20);
        mPath.lineTo(width,height-height/3);
        mPath.close();
      //  canvas.rotate(45, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        canvas.drawPath(mPath,mPaint);

        canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);


        //canvas.translate(0 , height/2);

        canvas.rotate(45, width/2 , height/2 );

//
//        canvas.rotate(45, width , 0 );


        super.onDraw(canvas);
    }
}