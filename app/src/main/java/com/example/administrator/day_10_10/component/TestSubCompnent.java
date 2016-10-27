package com.example.administrator.day_10_10.component;

import com.example.administrator.day_10_10.bean.Test;
import com.example.administrator.day_10_10.module.Main2Module;
import com.example.administrator.day_10_10.module.TestModule;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2016/10/25.
 */
@Subcomponent(modules = TestModule.class)
public interface TestSubCompnent {
    Test getTest();
}
