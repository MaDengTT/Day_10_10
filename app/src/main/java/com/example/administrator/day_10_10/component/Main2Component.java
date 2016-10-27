package com.example.administrator.day_10_10.component;

import android.content.Context;

import com.example.administrator.day_10_10.bean.Test;
import com.example.administrator.day_10_10.module.Main2Module;
import com.example.administrator.day_10_10.ui.ActivityScope;
import com.example.administrator.day_10_10.ui.Main2Activity;

import dagger.Component;
import dagger.Module;

/**
 * Created by Administrator on 2016/10/25.
 */

@Component(modules = Main2Module.class)
@ActivityScope
public interface Main2Component {
    void inject(Main2Activity main2Activity);

    Test getTest();

}
