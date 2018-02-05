package com.example.administrator.glidetest.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.glidetest.R;

import java.util.LinkedList;

/**
 * Created by Administrator on 2018/1/27.
 */

public class ImageLoader {


    private static volatile ImageLoader mInstance;

    private ImageLoader() {
    }


    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }
    /**
     * 在一个imageView里面异步展示一个图片
     *
     * @param uri
     * @param imageView
     */
    public void displayImage(Context context,String uri, ImageView imageView) {
        GlideApp.with(context)
                .load(uri)
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 设置缓存的策略
                .into(imageView);
    }

    /**
     * 异步加载一个图片，监听加载过程，指定大小，在回调中取得位图
     * 可以用来加载大图。
     *
     * @param context
     * @param uri
     * @param listener
     */
    public void loadImage(Context context, String uri, final getImageListener listener) {

        GlideApp.with(context)
                .asBitmap()
                .load(uri)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        Log.e("加载图片成功","d");
                        listener.onSuccess(bitmap);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Log.e("加载图片失败","d");
                        listener.onError(errorDrawable);
                    }
                });

    }

    /**
     * 异步加载一个图片，监听加载过程，指定大小，在回调中取得位图
     * 可以用来加载大图。
     *
     * @param context
     * @param uri
     * @param viewHolder
     * @param resId
     */
    public void displayImage(Context context, String uri, final BaseViewHolder viewHolder, final int resId) {

        GlideApp.with(context)
                .asBitmap()
                .load(uri)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                          viewHolder.setImageBitmap(resId,bitmap);
                    }
                });

    }

    /**
     * 用于以及加载图片获取 Bitmap
     */
    public interface getImageListener {
        void onSuccess(Bitmap bitmap);
        void onError(Drawable drawable);
    }

}
