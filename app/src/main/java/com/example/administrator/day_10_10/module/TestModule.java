package com.example.administrator.day_10_10.module;

import com.example.administrator.day_10_10.App;
import com.example.administrator.day_10_10.bean.Test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/10/25.
 */
@Module
public class TestModule {

    @Provides
    Test provideTest() {
        return new Test(App.getAppContext());
    }

}
