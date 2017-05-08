package com.pengzhangdemo.com.myapplication.numpic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pengzhangdemo.com.myapplication.utils.GiftDrawableUtils;

/**
 * Created by ying on 2016/11/16.
 */

public class NumToPicView extends LinearLayout {

    private int num = 1;
    private Context mContext;

    public NumToPicView(Context context) {
        this(context, null);
    }

    public NumToPicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumToPicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setNum(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("num 不能小于1");
        }
        this.num = num;

        init();
    }

    public int getNum(){
        return num;
    }

    // 在LinearLayout中添加View。需要修改一些view之间的间距
    private void addNumView() {

        String numStr = String.valueOf(num);

        if (this.getChildCount() > 0) {
            this.removeAllViews();
        }

        for (int i = 0; i < numStr.length(); i++) {

            Drawable numDrawable = GiftDrawableUtils.getGiftDrawale(mContext, numStr.charAt(i));

            ImageView image = new ImageView(getContext());

            image.setImageDrawable(numDrawable);

            addView(image);
        }
    }

    public void init() {
        setOrientation(HORIZONTAL);
        addNumView();
    }

}
