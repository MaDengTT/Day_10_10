package com.example.administrator.day_10_10.module;

import android.app.Application;

import com.example.administrator.day_10_10.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/10/18.
 */
@Module
public class AppModule {

    @Provides
    Application provideApplication() {
        return App.getApp();
    }


}
