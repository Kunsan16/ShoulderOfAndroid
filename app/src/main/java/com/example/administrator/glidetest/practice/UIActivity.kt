package com.example.administrator.glidetest.practice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.example.administrator.glidetest.R
import com.example.administrator.glidetest.view.DownLoadButton
import com.example.administrator.glidetest.view.DownloadProgressButton
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_coordinator.*
import java.util.concurrent.TimeUnit

/**
 * Created by moge on 2018/6/20.
 */
class UIActivity : AppCompatActivity(){


    companion object {
        fun launch(context: Context){
            val intent= Intent(context, UIActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_coordinator)

        registerButton()
    }

    private fun registerButton() {
        btn_download.setStateChangeListener(object : DownLoadButton.StateChangeListener {
            override fun onPauseTask() {
                dispose.dispose()
                btn_download.setState(DownLoadButton.STATE_PROGRESS_PAUSE)
            }

            override fun onFinishTask() {

                btn_download.setState(DownLoadButton.STATE_PROGRESS_FINISH)
            }

            override fun onLoadingTask() {
                dispose= downloadTest()
            }

            override fun onOpenGame() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    lateinit var  dispose:Disposable
    override fun onResume() {
        super.onResume()


    }


    private var percent:Int =0
    private fun downloadTest() :Disposable{
        btn_download.max = 100
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .filter { t -> t < 10 }
                .map { t -> t.toInt() }
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    percent += 10

                    btn_download.progress = percent
                    LogUtils.i(btn_download.isIndeterminate)
                    val a =btn_download.progress
                    LogUtils.i("------进度--------"+a)
                    btn_download.setState(DownLoadButton.STATE_PROGRESS_DOWNLOADING)


                }

    }
}