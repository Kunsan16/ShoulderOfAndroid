package com.example.administrator.glidetest;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.glidetest.adapter.GamesAdapter;
import com.example.administrator.glidetest.adapter.MyPagerAdapter;
import com.example.administrator.glidetest.bean.GameBean;
import com.example.administrator.glidetest.view.DynamicLine;
import com.example.administrator.glidetest.view.MyCircleView;
import com.example.administrator.glidetest.view.ViewPagerTitle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


     GridLayoutManager mLayoutManager;
    private MyCircleView myCircleView;

    private static int number = 10;
    private float angle = 0;
    private float EachAngle = 0;
     private List<GameBean> list=new ArrayList<>();
    private NetworkConnectChangedReceiver networkBroadcast=new NetworkConnectChangedReceiver();

    private ViewPager pager;
    private MyPagerAdapter adapter;
    private ArrayList<View> views;
    private View view1;
    private View view2;
    private View view3;
    //    private MyOnPageChangeListener onPageChangeListener;
    private DynamicLine dynamicLine;
    private ViewPagerTitle viewPagerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custome_view_layout);
        //GlideTest();
        //NetWorkTest();
        //tablayoutTest();

        myCircleView();
    }

     boolean timeOver;
    private void myCircleView() {
        myCircleView=findViewById(R.id.btn_circle_gray);
        myCircleView.setText(number+"");

        //必须要将360*1.0 / number转为float或者double，这样可以避免因为取整的问题而导致没有完全平分360度
        EachAngle = Float.parseFloat(String.valueOf(360*1.0 / number));

        myCircleView.setClickListener(new MyCircleView.OnClickListener() {
            @Override
            public void onClick() {
                if ("时间到！".equals(myCircleView.getText())){
                    Toast.makeText(MainActivity.this,"时间到了",Toast.LENGTH_SHORT).show();
                }else {
                    timer.start();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        
    }

    private CountDownTimer timer = new CountDownTimer(number*1000+1000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

                if (number==1){
                    myCircleView.setText("时间到！");
                }else {
                    myCircleView.setText(--number + "");
                }

                angle += EachAngle;
                myCircleView.setAngle(angle);


        }

        @Override
        public void onFinish() {
           // myCircleView.setAngle(360);
        }
    };


    private void tablayoutTest() {
        views = new ArrayList<>();

        viewPagerTitle = findViewById(R.id.pager_title);

//        dynamicLine = (DynamicLine)findViewById(R.id.line);

        pager =findViewById(R.id.view_pager);
        viewPagerTitle.initData(new String[]{"首页", "热门", "推荐"}, pager, 0);

//        onPageChangeListener = new MyOnPageChangeListener(pager, dynamicLine);


        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);

        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);

//        pager.addOnPageChangeListener(onPageChangeListener);

        adapter = new MyPagerAdapter(views);
        pager.setAdapter(adapter);

    }

    private void NetWorkTest() {

        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        this.registerReceiver(networkBroadcast, filter);
    }

    private void GlideTest() {

        GameBean gameBean=new GameBean();
        gameBean.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517120387605&di=4988108e29167ca2441c101105dad3e6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D621b42a155df8db1a8237424605bb838%2F7e3e6709c93d70cf05bce934f3dcd100baa12b40.jpg");
        gameBean.setTv("不要提了");
        list.add(gameBean);

//        RecyclerView recyclerView=findViewById(R.id.rv_list);
//        mLayoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        GamesAdapter adapter=new GamesAdapter(R.layout.item_game,null);
//        adapter.addData(list);
//        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // this.unregisterReceiver(networkBroadcast);
        if (timer != null) {
            timer.cancel();
        }
    }
}
