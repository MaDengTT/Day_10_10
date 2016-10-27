package com.example.administrator.day_10_10.component;

import android.app.Application;

import com.example.administrator.day_10_10.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/10/18.
 */
@Singleton
@Component(modules = { AppModule.class})
public interface AppComponent {
    Application getApplication();
}
