package com.example.administrator.glidetest;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.glidetest.glide.ImageLoader;

/**
 * Created by Administrator on 2018/1/28.
 */

public class MyViewHolder extends BaseViewHolder {


    public MyViewHolder(View view) {
        super(view);
    }

    public BaseViewHolder setImageUrl(Context context,int viewId, String url) {
        ImageView view = this.getView(viewId);
        ImageLoader.getInstance().displayImage(context,url,view);
        return this;
    }
}
