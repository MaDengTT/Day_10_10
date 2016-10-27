package com.example.administrator.day_10_10.ui;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.day_10_10.R;
import com.example.administrator.day_10_10.bean.Test;
import com.example.administrator.day_10_10.component.DaggerMain2Component;
import com.example.administrator.day_10_10.component.Main2Component;
import com.example.administrator.day_10_10.module.Main2Module;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {


    List<String> group;
    List<List<String>> child;

//    @Inject
//    Lazy<Test> test;
    @BindView(R.id.elv)
    ExpandableListView elv;
    @BindView(R.id.activity_main2)
    RelativeLayout activityMain2;


    void initData() {
        group = new ArrayList<>();
        child = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            group.add("Group: " + i);
            List<String> item = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                item.add("Item: " + i);
            }
            child.add(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initData();
        elv.setAdapter(new Adapter());

    }

    public class Adapter implements ExpandableListAdapter {

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return group.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String str = group.get(groupPosition);
            return getGenericView(str);
        }

        @Override
        public Object getGroup(int groupPosition) {
            return group.get(groupPosition);
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child.get(groupPosition).size();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child.get(groupPosition).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String str = child.get(groupPosition).get(childPosition);
            // TODO: 2016/10/26
            return getGenericView(str);
        }

        //创建组/子视图
        public TextView getGenericView(String s) {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, 80);

            TextView text = new TextView(Main2Activity.this);
            text.setLayoutParams(lp);
            // Center the text vertically
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            text.setPadding(36, 0, 0, 0);

            text.setText(s);
            return text;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }
    }


}
