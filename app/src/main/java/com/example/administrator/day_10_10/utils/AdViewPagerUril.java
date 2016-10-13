package com.example.administrator.day_10_10.utils;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/10/13.
 */

public class AdViewPagerUril {

    private Context context;
    private ViewPager viewPager;
    
    private ImageView[] mImageViews;
    private ImageView[] mDotViews;
    private String[] urls;
    
    private LinearLayout mDotLayout;

    private boolean isLoop =false;

    private int currentIndex = 0;   //当前item
    private int imageSize = 0;  //图片数量
    private int dotSize = 15;   //点的大小
    private int dotoffset = 10; //点的数量
    private AdPagerAdapter adPagerAdapter;
    private long delayTime = 2000;  //轮播时间
    private int autoIndex=0;    //轮训的自增坐标
    private int lastPosition = 1;//上一个坐标区分滑动方向

    boolean isRight;

    public AdViewPagerUril(Context context, ViewPager viewPager, LinearLayout mDotLayout,String[] urls,AdViewPagerListener listener) {
        this.context = context;
        this.viewPager = viewPager;
        this.urls = urls;
        this.mDotLayout = mDotLayout;
        setListener(listener);
        initView();
    }

    public void setListener(AdViewPagerListener listener) {
        this.listener = listener;
    }

    private void initView() {
        initImageViews();
        initDots(urls.length);
        adPagerAdapter = new AdPagerAdapter(mImageViews);
        viewPager.setAdapter(adPagerAdapter);
        viewPager.setOffscreenPageLimit(mImageViews.length);
        startLoopViewPager();

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isLoop = true;
                        stopLoopViewPager();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        isLoop = false;
                        startLoopViewPager();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (!isRight) {
                    if (positionOffset < 0.01) {
                        if (position == 0) {
                            viewPager.setCurrentItem(imageSize - 2, false);
                        } else if (position == imageSize - 1) {
                            viewPager.setCurrentItem(1,false);
                        }
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                autoIndex = position;
                if (lastPosition < position && lastPosition != 0) {
                    isRight = true;
                } else if (lastPosition == imageSize - 1) {
                    isRight = true;
                }
                if (lastPosition > position && lastPosition != imageSize - 1) {
                    isRight = false;
                } else if (lastPosition == 0) {
                    isRight = false;
                }
                lastPosition = position;

                if (position == 0) {
                    currentIndex = imageSize - 2;
                } else if (position == imageSize - 1) {
                    currentIndex = 1;
                }else {
                    currentIndex = position;
                }
                if (mDotLayout != null) {
                    for(int i = 0;i<mDotViews.length;i++) {
                        if (i == currentIndex - 1) {
                            mDotViews[i].setSelected(true);
                        } else {
                            mDotViews[i].setSelected(false);
                        }
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (isRight) {
                    if (state != 1) {
                        if (lastPosition == 0) {
                            viewPager.setCurrentItem(imageSize-2,false);
                        } else if (lastPosition == imageSize - 1) {
                            viewPager.setCurrentItem(1,false);
                        }
                    }
                }
            }
        });

        viewPager.setCurrentItem(1);
    }

    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getChildCount() > 0) {
                mHandler.postDelayed(this, delayTime);
                autoIndex++;
                viewPager.setCurrentItem(autoIndex % imageSize, true);
            }
        }
    };

    /**
     * 开启轮播
     */
    private void startLoopViewPager() {
        if (!isLoop && viewPager != null && mHandler != null) {
            mHandler.postDelayed(runnable, delayTime);
            isLoop = true;
        }
    }

    /**
     * 停止轮播
     */
    private void stopLoopViewPager(){
        if (isLoop && viewPager != null && mHandler != null) {
            mHandler.removeCallbacks(runnable);
            isLoop = false;
        }
    }

    private void initDots(int length) {
        if (mDotLayout == null) {
            return;
        }
        if (listener != null&&listener.getDotSize() != 0) {
            dotSize = listener.getDotSize();
        }
        if (listener != null && listener.getDotoffset() != 0) {
            dotoffset = listener.getDotoffset();
        }
        mDotLayout.removeAllViews();
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(dip2px(context, dotSize), dip2px(context, dotSize));
        mParams.setMargins(dip2px(context,dotoffset),0,dip2px(context,dotoffset),0);
        mDotViews = new ImageView[length];

        for (int i = 0;i<length;i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(mParams);
            if (listener != null) {
                listener.displayDotImage(imageView, i);
            }
            if (i == 0) {
                imageView.setSelected(true);
            }else {
                imageView.setSelected(false);
            }
            mDotViews[i] = imageView;
            mDotLayout.addView(imageView);
        }

    }

    private void initImageViews() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int length = urls.length+2;
//        int length = urls.length;
        mImageViews = new ImageView[length];
        for(int i = 0;i<length;i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageViews[i] = imageView;
        }
        setImg(length, urls);
    }

    /**
     * 相关监听
     */
    AdViewPagerListener listener;
    public interface AdViewPagerListener {
        void displayImgView(ImageView iv, String url,int position);

        int getDotSize();

        int getDotoffset();

        int displayDotImage(ImageView iv, int position);
    }




    /**
     * 获取图片
     * @param length
     * @param urls
     */
    private void setImg(int length, String[] urls) {
        if (urls.length > 0) {
            imageSize = length;
            for (int i = 0;i<length;i++) {
                if (i < length - 2) {
                    int index = i;
                    String url = urls[index];
                    if (listener != null) {
                        listener.displayImgView(mImageViews[index+1], url,index);
                    }
                }
            }
            if (listener != null) {
                listener.displayImgView(mImageViews[0], urls[urls.length - 1],urls.length-1);
                listener.displayImgView(mImageViews[length-1],urls[0],0);
            }
        }
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private static class AdPagerAdapter extends PagerAdapter {
        ImageView[] imageViews;
        public AdPagerAdapter(ImageView[] imageViews) {
            this.imageViews = imageViews;
        }

        @Override
        public int getCount() {
            return imageViews.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews[position]);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews[position],0);
            return imageViews[position];
        }
    }

}
