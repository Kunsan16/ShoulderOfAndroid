package com.example.administrator.glidetest.practice

import android.content.Context
import android.content.Intent
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.administrator.glidetest.R
import kotlinx.android.synthetic.main.layout_handler.*

/**
 * Created by moge on 2018/6/4.
 */
class HandlerTestActivity : AppCompatActivity() {


    private val mainHandler = object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            text.text ="收到消息啦233333"
        }
    }

    companion object {
        fun launch(context:Context){
            val intent=Intent(context, HandlerTestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_handler)

      //  Handler().post({ text.text = "dd" })
       // handler.sendEmptyMessage(0)

       // text.post({text.text="通过View Post"})
        val handlerThread =HandlerThread("handler")
        handlerThread.start()
         val handler = object : Handler(handlerThread.looper){
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                mainHandler.post({
                    text.text ="收到消息啦"
                })
            }
        }


        btn_handler.setOnClickListener({
            val msg1=Message.obtain()
            msg1.obj="1"
            handler.sendMessage(msg1)
        })

        btn_async.setOnClickListener({
            val myTask=MyTask()
            myTask.execute("")
        })
    }


     class MyTask:AsyncTask<String,Int,String>(){

         override fun onPreExecute() {
             super.onPreExecute()

         }

         override fun doInBackground(vararg params: String?): String {
             TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
         }

         override fun onPostExecute(result: String?) {
             super.onPostExecute(result)
         }

         override fun onCancelled() {
             super.onCancelled()
         }
     }
    }
