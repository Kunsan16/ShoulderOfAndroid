package com.example.administrator.glidetest;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.glidetest.adapter.GamesAdapter;
import com.example.administrator.glidetest.adapter.MyAdapter;
import com.example.administrator.glidetest.adapter.MyPagerAdapter;
import com.example.administrator.glidetest.bean.GameBean;
import com.example.administrator.glidetest.inter.Buy;
import com.example.administrator.glidetest.inter.BuyProxy;
import com.example.administrator.glidetest.inter.Consumer;
import com.example.administrator.glidetest.view.DynamicLine;
import com.example.administrator.glidetest.view.ViewPagerTitle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Runnable {


    LinearLayoutManager mLayoutManager;
    private List<GameBean> list = new ArrayList<>();
    private NetworkConnectChangedReceiver networkBroadcast = new NetworkConnectChangedReceiver();

    private ViewPager pager;
    private MyPagerAdapter adapter;
    private ArrayList<View> views;
    private View view1;
    private View view2;
    private View view3;
    //    private MyOnPageChangeListener onPageChangeListener;
    private DynamicLine dynamicLine;
    private ViewPagerTitle viewPagerTitle;

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        GlideTest();
        //NetWorkTest();
        // tablayoutTest();
        //proxyTest();
    }

    private void proxyTest() {

        Buy buy = new Consumer();

        BuyProxy proxy = new BuyProxy(buy);
        proxy.buyHouse(1000);

    }

    private void tablayoutTest() {
        views = new ArrayList<>();

        viewPagerTitle = findViewById(R.id.pager_title);

//        dynamicLine = (DynamicLine)findViewById(R.id.line);

        pager = (ViewPager) findViewById(R.id.view_pager);
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

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        this.registerReceiver(networkBroadcast, filter);
    }

    private void GlideTest() {

        GameBean gameBean = new GameBean();
        gameBean.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517120387605&di=4988108e29167ca2441c101105dad3e6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D621b42a155df8db1a8237424605bb838%2F7e3e6709c93d70cf05bce934f3dcd100baa12b40.jpg");
        gameBean.setTv("不要提了");
        list.add(gameBean);
//
        RecyclerView recyclerView = findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        GamesAdapter adapter = new GamesAdapter(R.layout.item_game, null);

        adapter.setNewData(getData());
        //   MyAdapter adapter=new MyAdapter(getData(),this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<GameBean> getData() {
        ArrayList<GameBean> data = new ArrayList<>();
        GameBean bean;
        String temp = " item";
        for (int i = 0; i < 30; i++) {
            bean = new GameBean();
            bean.setTv(temp + i);
            data.add(bean);
        }

        return data;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // this.unregisterReceiver(networkBroadcast);
    }

    @Override
    public void run() {

    }
}
