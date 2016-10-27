package com.example.administrator.day_10_10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.day_10_10.R;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/10/21.
 */

public class listAdapter extends BaseAdapter{

    Context context;
    List<String> datas;

    @Inject
    listAdapter(Context context, @Named("one") List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void addItem(List<String> newDatas) {
        datas.addAll(newDatas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rvlist, parent, false);
            tv = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(tv);
        }else {
            tv = (TextView) convertView.getTag();
        }
        tv.setText(datas.get(position));
        return convertView;
    }
}
