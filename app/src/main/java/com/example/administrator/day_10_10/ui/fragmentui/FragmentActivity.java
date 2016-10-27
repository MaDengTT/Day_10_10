package com.example.administrator.day_10_10.ui.fragmentui;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.day_10_10.R;
import com.jaydenxiao.common.baserx.RxBus;

import okhttp3.Interceptor;

public class FragmentActivity extends AppCompatActivity {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, FragmentActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        BlankFragment blankFragment = (BlankFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (blankFragment == null) {
            Log.d("FragmentActivity", "blankFragment is null");
            blankFragment = BlankFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.contentFrame, blankFragment);
            fragmentTransaction.commit();
        }else {
            Log.d("FragmentActivity", "blankFragment not null");
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_edit_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().post("string","lol");
            }
        });
    }
}
