package com.example.administrator.day_10_10.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.administrator.day_10_10.App;
import com.example.administrator.day_10_10.R;
import com.example.administrator.day_10_10.adapter.RvListAdapter;
import com.example.administrator.day_10_10.api.Server;

import com.example.administrator.day_10_10.component.DaggerDaTestActivityCompoent;
import com.example.administrator.day_10_10.module.AdapterModule;
import com.example.administrator.day_10_10.module.ApiModule;


import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DaTestActivity extends AppCompatActivity {

    public static void startDaTestActivity(Activity activity) {
        Intent intent = new Intent(activity, DaTestActivity.class);
        activity.startActivity(intent);



    }

    RecyclerView recyclerView;
    @Inject
    Server server;

    @Inject
    RvListAdapter rvListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_test);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);



        DaggerDaTestActivityCompoent.builder()
                .appComponent(App.getAppComponent())
                .apiModule(new ApiModule())
                .adapterModule(new AdapterModule())
                .build()
                .inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvListAdapter);
        Log.d("DaTestActivity", "rvListAdapter.getItemCount():" + rvListAdapter.getItemCount());
        Observable<String> datas = server.getDatas();
        datas.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("DaTestActivityCompoent", s);
                    }
                });
    }
}
