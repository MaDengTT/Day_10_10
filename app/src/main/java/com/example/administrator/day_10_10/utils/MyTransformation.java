package com.example.administrator.day_10_10.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Administrator on 2016/10/14.
 */

public class MyTransformation extends BitmapTransformation {
    public MyTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return BitmapUtils.createBlurBitmap(toTransform,50);
    }

    @Override
    public String getId() {
        return "my";
    }
}
