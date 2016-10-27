package com.example.administrator.day_10_10.module;

import android.content.Context;

import com.example.administrator.day_10_10.App;
import com.example.administrator.day_10_10.bean.Test;
import com.example.administrator.day_10_10.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/10/25.
 */
@Module
public class Main2Module {

    Context context;
    public Main2Module(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    public Test provides() {
        return new Test(App.getAppContext());
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return context;
    }
}
