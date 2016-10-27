package com.example.administrator.day_10_10.component;



import android.content.Context;

import com.example.administrator.day_10_10.api.Server;
import com.example.administrator.day_10_10.module.AdapterModule;
import com.example.administrator.day_10_10.module.ApiModule;
import com.example.administrator.day_10_10.ui.ActivityScope;
import com.example.administrator.day_10_10.ui.DaTestActivity;

import java.util.List;

import javax.inject.Named;

import dagger.Component;


/**
 * Created by Administrator on 2016/10/18.
 */
@ActivityScope
@Component(modules = {ApiModule.class, AdapterModule.class},dependencies = AppComponent.class)
public interface DaTestActivityCompoent {
    void inject(DaTestActivity activity);
    Server getServer();

    Context getContext();

    @Named("one")
    List<String> getDatas();

    @Named("two")
    List<String> getDatas2();
}
