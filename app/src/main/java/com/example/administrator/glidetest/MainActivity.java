package com.example.administrator.glidetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.example.administrator.glidetest.adapter.DownloadAdapter;
import com.example.administrator.glidetest.adapter.MyPagerAdapter;
import com.example.administrator.glidetest.bean.DownloadBean;
import com.example.administrator.glidetest.inter.Buy;
import com.example.administrator.glidetest.inter.BuyProxy;
import com.example.administrator.glidetest.inter.Consumer;
import com.example.administrator.glidetest.practice.AnimationTestActivity;
import com.example.administrator.glidetest.practice.HandlerTestActivity;
import com.example.administrator.glidetest.practice.TouchEventActivity;
import com.example.administrator.glidetest.service.MyService;
import com.example.administrator.glidetest.view.DynamicLine;
import com.example.administrator.glidetest.view.ViewPagerTitle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Runnable {


    LinearLayoutManager mLayoutManager;
    private List<DownloadBean> list = new ArrayList<>();
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

    private IRemoteService remoteService=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //GlideTest();
        //NetWorkTest();
        // tablayoutTest();
        //proxyTest();
        allTest();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this, MyService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }


    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService= IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void allTest() {

        Button button=findViewById(R.id.btn_handler);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerTestActivity.Companion.launch(MainActivity.this);
            }
        });

        Button button1=findViewById(R.id.btn_animation);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationTestActivity.Companion.launch(MainActivity.this);
            }
        });

        findViewById(R.id.btn_ipc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String msg=remoteService.sayHello().getMsg();
                    LogUtils.i("收到AIDL消息 "+msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_touch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TouchEventActivity.Companion.launch(MainActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                 data.get(0).setTv("改变了数据");
                gameAdapter.notifyDataSetChanged();
                return true;
            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void proxyTest() {

        Buy buy = new Consumer();

        BuyProxy proxy = new BuyProxy(buy);
        proxy.buyHouse(1000);

    }

    private void tablayoutTest() {
        views = new ArrayList<>();

        //viewPagerTitle = findViewById(R.id.pager_title);

//        dynamicLine = (DynamicLine)findViewById(R.id.line);

        //pager = findViewById(R.id.view_pager);
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

    DownloadAdapter gameAdapter;
    private void GlideTest() {

        DownloadBean gameBean = new DownloadBean();
      //  gameBean.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517120387605&di=4988108e29167ca2441c101105dad3e6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D621b42a155df8db1a8237424605bb838%2F7e3e6709c93d70cf05bce934f3dcd100baa12b40.jpg");
        gameBean.setTv("不要提了");
        list.add(gameBean);
//
        RecyclerView recyclerView = findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        gameAdapter = new DownloadAdapter(R.layout.item_game, getData());

        //   MyAdapter adapter=new MyAdapter(getData(),this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(gameAdapter);

    }

    ArrayList<DownloadBean> data;
    private ArrayList<DownloadBean> getData() {
        data= new ArrayList<>();
        DownloadBean bean;
        String temp = " item";
        for (int i = 0; i < 30; i++) {
            bean = new DownloadBean();
            bean.setTv(temp + i);
            data.add(bean);
        }
        return data;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // this.unregisterReceiver(networkBroadcast);
        gameAdapter.removeDisposable();
    }

    @Override
    public void run() {

    }
}
