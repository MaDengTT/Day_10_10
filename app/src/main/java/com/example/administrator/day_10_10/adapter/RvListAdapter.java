package com.example.administrator.day_10_10.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.day_10_10.R;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/10/19.
 */

public class RvListAdapter extends RecyclerView.Adapter<RvListAdapter.ViewHolder> {

    Context context;
    List<String> datas;
    @Inject
    public RvListAdapter(Context context,@Named("two") List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_rvlist, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindStr(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item);
        }

        public void bindStr(String string) {
            tv.setText(string);
        }
    }


}
