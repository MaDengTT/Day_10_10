package com.example.administrator.day_10_10.api;

import com.example.administrator.day_10_10.utils.HttpUtils;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/11.
 */

public class Server {

    Api api;

    public Server() {
        Retrofit retrofit = HttpUtils.getRetrofit();

        api = retrofit.create(Api.class);
    }

    public Observable<String> getDatas() {
          return api.getDatas("1&rsv_spt=1&rsv_iqid=0x9e1c171b00007a4f&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=0&rsv_sug3=2&rsv_sug1=2&rsv_sug7=101&inputT=1109&rsv_sug4=1714").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
