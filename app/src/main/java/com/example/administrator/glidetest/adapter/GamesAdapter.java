package com.example.administrator.glidetest.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.glidetest.MyViewHolder;
import com.example.administrator.glidetest.R;
import com.example.administrator.glidetest.bean.GameBean;
import com.example.administrator.glidetest.glide.GlideApp;
import com.example.administrator.glidetest.glide.ImageLoader;


import java.util.List;

/**
 * Created by huanxi on 2017/12/6.
 */

public class GamesAdapter extends BaseQuickAdapter<GameBean, MyViewHolder> {



    public GamesAdapter(int layoutResId, @Nullable List<GameBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(MyViewHolder helper, GameBean item) {
        helper.setImageUrl(mContext,R.id.iv,item.getImg());
    }

}
