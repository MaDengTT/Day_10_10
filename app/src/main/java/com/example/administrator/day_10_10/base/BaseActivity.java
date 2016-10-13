package com.example.administrator.day_10_10.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.day_10_10.utils.ClassUtil;

/**
 * Created by Administrator on 2016/10/13.
 */

public abstract class BaseActivity<T extends BasePresenter,E extends BaseModel> extends AppCompatActivity {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforesetContentView();
        setContentView(getLayoutId());
//        mPresenter = ClassUtil.getT(this, 0);
//        mModel = ClassUtil.getT(this, 1);

    }

    /**
     * 在Layout配置前配置，一般配置主题，状态栏等相关属性
     */
    private void doBeforesetContentView() {
    }

    /**
     * 获取 布局文件
     * @return
     */
    abstract @LayoutRes int getLayoutId();

    abstract void initPresenter();
}
