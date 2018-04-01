package com.example.administrator.glidetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.glidetest.MyViewHolder;
import com.example.administrator.glidetest.R;
import com.example.administrator.glidetest.bean.GameBean;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2018/3/4.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyBaseViewHolder> {


    private List<GameBean> mList=new ArrayList<>();
    Context context;
    private final static String TAG="MyAdapter";

    public MyAdapter(List<GameBean> mData, Context context){
        this.mList=mData;
        this.context=context;
    }


    @Override
    public void onViewRecycled(MyBaseViewHolder holder) {
        super.onViewRecycled(holder);
        Log.i(TAG,"onViewRecycled"+holder.tv.getTag());
    }

    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_game,parent,false);
        Log.i(TAG,"onCreateViewHolder");
        return new MyBaseViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MyBaseViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: 重用了"+holder.tv.getTag()+" position"+position);

        holder.tv.setText(mList.get(position).getTv());

        holder.tv.setTag(mList.get(position).getTv());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class MyBaseViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public MyBaseViewHolder(View itemView) {
            super(itemView);
        tv=itemView.findViewById(R.id.tv);
        }
    }
}
