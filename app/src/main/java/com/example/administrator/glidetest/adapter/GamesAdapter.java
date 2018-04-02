package com.example.administrator.glidetest.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.glidetest.Constant;
import com.example.administrator.glidetest.MyViewHolder;
import com.example.administrator.glidetest.R;
import com.example.administrator.glidetest.bean.GameBean;
import com.example.administrator.glidetest.glide.GlideApp;
import com.example.administrator.glidetest.glide.ImageLoader;
import com.example.administrator.glidetest.model.DataManager;
import com.example.administrator.glidetest.view.DownloadProgressButton;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huanxi on 2017/12/6.
 */

public class GamesAdapter extends BaseQuickAdapter<GameBean, GamesAdapter.ViewHolderfor> {


    private final static String TAG="Adapter";
    private CompositeDisposable compositeDisposable;

    private Disposable disposable;

    List<Disposable> list=new ArrayList<>();
    private Disposable[] disposables=new Disposable[50];
    private int[] progress=new int[50];
    private boolean[] flags=new boolean[50];

    private void addDisposable(Disposable disposable){
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    private void removeDisposable(Disposable disposable){
        if (disposable!=null)
            compositeDisposable.remove(disposable);


    }


    public GamesAdapter(int layoutResId, @Nullable List<GameBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final ViewHolderfor helper, final GameBean item) {
        helper.setText(R.id.tv,item.getTv());

        final DownloadProgressButton button=helper.getView(R.id.btn_download);

        if (item.getState()==0){
            Log.i(TAG,helper.getAdapterPosition()+"");
            button.setStartText("下载");
            button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
            button.setProgress(0);
        }else {
            if (item.getState()==Constant.DOWNLOAD_STATE_DEFAULT){
                button.setStartText("下载");
                button.setProgress(0);
                button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
            }
            if (item.getState()==Constant.DOWNLOAD_STATE_DOWDLOADING){
                button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                button.setProgress(item.getProgress());

            }if (item.getState()==Constant.DOWNLOAD_STATE_PAUSE){
                button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
                button.setProgress(item.getProgress());

            }
        }



//        if (helper.lastPosition != -1) {
//            flags[helper.lastPosition] = false;
//        }
//
//        flags[position] = true;
//        helper.lastPosition = position;

        button.setStateChangeListener(new DownloadProgressButton.StateChangeListener() {
            @Override
            public void onPauseTask() {
                isPaused=true;
                button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);

                item.setState(Constant.DOWNLOAD_STATE_PAUSE);
                item.setProgress(button.getProgress());
//                if (disposables[helper.getAdapterPosition()]!=null){
//                    progress[helper.getAdapterPosition()]=button.getProgress();
//                    disposables[helper.getAdapterPosition()].dispose();
//                }

            }

            @Override
            public void onFinishTask() {

            }

            @Override
            public void onLoadingTask() {

                    disposable= Observable.interval(0, 1, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.computation())
                            .filter(new Predicate<Long>() {
                                @Override
                                public boolean test(Long aLong) throws Exception {
                                    return aLong < 100;
                                }
                            })
                            .map(new Function<Long, Object>() {
                                @Override
                                public Object apply(Long aLong) throws Exception {
                                    return aLong.intValue();
                                }
                            })
                            .doOnNext(new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {

                                }
                            })
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {
                                    button.setProgress(button.getProgress() + 2);
//                                    button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                                    item.setState(Constant.DOWNLOAD_STATE_DOWDLOADING);
                                    item.setProgress(button.getProgress());
                                }
                            });
                    addDisposable(disposable);
                    disposables[helper.getAdapterPosition()]=disposable;

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                        while (button.getProgress()!=button.getMax()&&!isPaused){
//                            try {
//                                button.setProgress(button.getProgress()+2);
//                                button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }).start();


            }
        });


    }

    boolean isPaused;

    private void checkItemState(){

    }


     public static class ViewHolderfor extends MyViewHolder{

        // CheckBox checkBox;
        int lastPosition;
         DownloadProgressButton button;
         public ViewHolderfor(View view) {
             super(view);
            // checkBox=getView(R.id.checkbox);
             button=getView(R.id.btn_download);

         }
     }
}
