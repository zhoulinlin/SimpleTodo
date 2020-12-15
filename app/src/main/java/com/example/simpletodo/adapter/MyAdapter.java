package com.example.simpletodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.simpletodo.R;
import com.example.simpletodo.db.bean.Task;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<Task> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    Context mContext;


    public MyAdapter(List<Task> data,Context context) {
        mData = data;
        mContext = context;
    }

    public void setmData(List<Task> tasks){
        mData = tasks;

    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder holder = null;
//        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder.textView = convertView.findViewById(R.id.tvContent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }


        holder.textView.setText(mData.get(position).getmContent());
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }

}