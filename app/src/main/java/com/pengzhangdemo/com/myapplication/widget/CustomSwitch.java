package com.pengzhangdemo.com.myapplication.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Switch;

import com.pengzhangdemo.com.myapplication.R;

import java.lang.reflect.Field;

/**
 * Created by zp on 4/22/17.
 */

public class CustomSwitch extends Switch {

    Context context;

    public CustomSwitch(Context context) {
        super(context);
    }

    public CustomSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public CustomSwitch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        Drawable drawable = context.getApplicationContext().getResources().getDrawable(R.drawable.button_barrage_off_bg);
//        int height = drawable.getIntrinsicHeight();

//        Log.e("CustomSwitch onMeasure", "height = " + height + ",drawable.getIntrinsicHeight() = " + drawable.getIntrinsicHeight());

//        final int switchWidth = Math.max(mSwitchMinWidth,
//                2 * mThumbWidth + paddingLeft + paddingRight);

        try {

            Field switchWidth = Switch.class.getDeclaredField("mSwitchWidth");
            switchWidth.setAccessible(true);

//            Field switchHeight = Switch.class.getDeclaredField("mSwitchHeight");
//            switchHeight.setAccessible(true);

            Field myThumbWidth = Switch.class.getDeclaredField("mThumbWidth");
            myThumbWidth.setAccessible(true);

            int mImage = myThumbWidth.getInt(this);

            // Using 120 below as example width to set
            // We could use attr to pass in the desire width

            switchWidth.setInt(this, mImage + mImage / 3);

//            switchHeight.setInt(this, height);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}

