package com.example.administrator.glidetest.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.glidetest.MyOnPageChangeListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/4.
 * 创建tab的view，主要是textview跟底下游标线
 */

public class ViewPagerTitle extends LinearLayout {

    private String[] titles;
    private MyOnPageChangeListener onPageChangeListener;
    private ViewPager viewPager;
    private OnTextViewClick onTextViewClick;
    private DynamicLine dynamicLine;

    private ArrayList<TextView> textViews = new ArrayList<>();    //保存每个tab，也就是每个textview

    public ViewPagerTitle(Context context) {
        this(context, null);
    }

    public ViewPagerTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);
    }

    public void initData(String[] titles, ViewPager viewPager,int defaultIndex){
        this.titles=titles;
        this.viewPager=viewPager;
        createDynamicLine();
        createTextViews(titles);
        setCurrentItem(defaultIndex);


        onPageChangeListener = new MyOnPageChangeListener(getContext(), viewPager, dynamicLine, this);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    private void createTextViews(String[] titles) {
       LinearLayout textViewLinear=new LinearLayout(getContext());   //创建承载textview的父布局
       LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        textViewLinear.setLayoutParams(layoutParams);
        textViewLinear.setOrientation(HORIZONTAL);

        //每个TextView的布局设置
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(0,LayoutParams.WRAP_CONTENT,1);
        for (int i = 0; i <titles.length ; i++) {
            TextView textView=new TextView(getContext());
            textView.setText(titles[i]);
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(18);
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setOnClickListener(onClickListener);
            textViews.add(textView);
            textView.setTag(i);
            textViewLinear.addView(textView);   //把TextView加入到父布局中
        }

        addView(textViewLinear);   //把上面绘制好的控件加到当前整个布局中
        addView(dynamicLine);
    }

    private void createDynamicLine() {

        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dynamicLine = new DynamicLine(getContext());
        dynamicLine.setLayoutParams(params);
    }

    public ArrayList<TextView> getTextView(){
        return textViews;
    }


    private OnClickListener onClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i <textViews.size() ; i++) {
                if (i == (int) v.getTag()) {
                    ((TextView) v).setTextColor(Color.BLACK);
                    ((TextView) v).setTextSize(22);
                } else {
                    textViews.get(i).setTextColor(Color.GRAY);
                    textViews.get(i).setTextSize(18);
                }
            }
            viewPager.setCurrentItem((int) v.getTag());   //相应地切换viewpager页面
            if (onTextViewClick != null) {
                onTextViewClick.textViewClick((TextView) v, (int) v.getTag());
            }

        }
    };
    public void setCurrentItem(int index) {
        for (int i = 0; i < textViews.size(); i++) {
            if (i == index) {
                textViews.get(i).setTextColor(Color.BLACK);
                textViews.get(i).setTextSize(20);
            } else {
                textViews.get(i).setTextColor(Color.GRAY);
                textViews.get(i).setTextSize(18);
            }
        }
    }

    public interface OnTextViewClick {
        void textViewClick(TextView textView, int index);
    }

    public void setOnTextViewClickListener(OnTextViewClick onTextViewClick) {
        this.onTextViewClick = onTextViewClick;
    }
}
