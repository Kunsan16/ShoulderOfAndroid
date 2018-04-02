package com.example.administrator.glidetest.model;

import android.util.Log;

import com.example.administrator.glidetest.adapter.GamesAdapter;
import com.example.administrator.glidetest.bean.GameBean;
import com.example.administrator.glidetest.view.DownloadProgressButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/4.
 */

public class DataManager {

    boolean isPaused;
    private CompositeDisposable compositeDisposable;

    private Disposable disposable;

    List<Disposable> list=new ArrayList<>(50);

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

    public DataManager(final DownloadProgressButton button, final GameBean bean,final GamesAdapter.ViewHolderfor helper){


        button.setStateChangeListener(new DownloadProgressButton.StateChangeListener() {
            @Override
            public void onPauseTask() {
                isPaused=true;
                button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
                bean.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
            }

            @Override
            public void onFinishTask() {

            }

            @Override
            public void onLoadingTask() {
                if (button.getState()==DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING) {
                    isPaused=false;
                }
                bean.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);

                if (list.get(helper.getAdapterPosition())==null) {
                    disposable=Observable.interval(0, 1, TimeUnit.SECONDS)
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
                                    button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                                }
                            });
                    addDisposable(disposable);
                    list.set(helper.getAdapterPosition(),disposable);
                }
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
}
