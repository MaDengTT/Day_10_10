package com.example.administrator.day_10_10.module;

import android.content.Context;

import com.example.administrator.day_10_10.App;
import com.example.administrator.day_10_10.api.Server;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/10/18.
 */

@Module
public class ApiModule {

    @Provides
    Server provideServer() {
        return new Server();
    }

    @Provides
    Context provideContext() {
        return App.getAppContext();
    }
}
