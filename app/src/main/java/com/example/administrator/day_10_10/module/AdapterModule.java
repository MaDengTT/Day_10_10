package com.example.administrator.day_10_10.module;

import com.example.administrator.day_10_10.ui.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/10/19.
 */
@Module
public class AdapterModule {

    @Provides
    @ActivityScope
    @Named("one")
    List<String> provideDatas() {
        List<String> datas = new ArrayList<>();
        for(int i = 0;i<10;i++) {
            datas.add("item : " + i);
        }
        return datas;
    }
    @Provides @Named("two")
    @ActivityScope
    List<String> provideDatas1() {
        List<String> datas = new ArrayList<>();
        for(int i = 0;i<10;i++) {
            datas.add("item 2 : " + i);
        }
        return datas;
    }

}
