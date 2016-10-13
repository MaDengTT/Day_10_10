package com.example.administrator.day_10_10.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/11.
 */

public interface Api {

    @GET("/s")
    Observable<String> getDatas(@Query("wd")String s);


}
