package com.example.administrator.day_10_10;

import android.app.Application;

import com.example.administrator.day_10_10.component.AppComponent;
import com.example.administrator.day_10_10.component.DaggerAppComponent;
import com.example.administrator.day_10_10.module.AppModule;
import com.jaydenxiao.common.baseapp.BaseApplication;

/**
 * Created by Administrator on 2016/10/18.
 */

public class App extends BaseApplication {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public static Application getApp() {
        return application;
    }
}
