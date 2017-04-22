package com.pengzhangdemo.com.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by zp on 4/17/17.
 *
 * 为了让多个TextView能够同时展示跑马灯的效果
 *
 */

public class MarqueeText extends android.support.v7.widget.AppCompatTextView {

    public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MarqueeText(Context context) {
        super(context);

    }

    @Override
    public boolean isFocused() {
        return true;
    }

}