package com.example.administrator.day_10_10;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.day_10_10.api.Server;
import com.example.administrator.day_10_10.utils.AdViewPagerUril;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.internal.operators.OperatorGroupBy;
import rx.observers.Observers;

public class TestActivity extends AppCompatActivity {

    ViewPager vpLoop;

    public static void startAction(Activity mActivity, View view) {
        Intent intent = new Intent(mActivity, TestActivity.class);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, "transition_animation_news_photos");
            mActivity.startActivity(intent, options.toBundle());
        } else {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity(mActivity,intent,options.toBundle());
        }
    }

    String[] urls = {
            "http://img1.imgtn.bdimg.com/it/u=1912948118,3818660301&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2135651304,163786869&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2738116507,3030577930&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2770303260,842388089&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=321385007,474854029&fm=21&gp=0.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        vpLoop = (ViewPager) findViewById(R.id.vp_loop);
        AdViewPagerUril.AdViewPagerListener listener = new AdViewPagerUril.AdViewPagerListener() {
            @Override
            public void displayImgView(ImageView iv, final String url,int position) {
                Glide.with(TestActivity.this).load(url).into(iv);
                Toast.makeText(TestActivity.this, url, Toast.LENGTH_SHORT).show();
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TestActivity.this, url, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public int getDotSize() {
                return 0;
            }

            @Override
            public int getDotoffset() {
                return 0;
            }

            @Override
            public int displayDotImage(ImageView iv, int position) {
                iv.setImageResource(R.drawable.dot_selector);
                return 0;
            }
        };
        AdViewPagerUril viewPagerUril = new AdViewPagerUril(this, vpLoop, (LinearLayout) findViewById(R.id.ll_dot),urls,listener);
        textRx();

    }

    public void textRx() {

        Server server = new Server();
        Observable<String> datas = server.getDatas();
        datas.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("TestActivity", e.toString());
            }

            @Override
            public void onNext(String s) {
                Log.d("TestActivity", s);
            }
        });



        Observable.zip(getDatas1(), getDatas2(), new Func2<String, Integer, String>() {

            @Override
            public String call(String s, Integer integer) {
                return s+":"+integer;
            }
        })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("TestActivity", s.toString());
                    }
                });
    }

    public Observable<String> getDatas1() {
        return Observable.just("a","b","c","d","e","f","g");
    }
    public Observable<Integer> getDatas2() {
        return Observable.just(1,2,3,4,5);
    }
}
