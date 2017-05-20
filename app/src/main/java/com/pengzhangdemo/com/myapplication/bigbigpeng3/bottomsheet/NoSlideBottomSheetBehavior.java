package com.pengzhangdemo.com.myapplication.bigbigpeng3.bottomsheet;

/**
 * Created by zp on 4/26/17.
 */

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class NoSlideBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
    private boolean mAllowUserDragging = true;

    /**
     * Default constructor for instantiating BottomSheetBehaviors.
     */
    public NoSlideBottomSheetBehavior() {
        super();
    }

    /**
     * Default constructor for inflating BottomSheetBehaviors from layout.
     *
     * @param context The {@link Context}.
     * @param attrs   The {@link AttributeSet}.
     */
    public NoSlideBottomSheetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAllowUserDragging(boolean allowUserDragging) {
        mAllowUserDragging = allowUserDragging;
    }

//    @Override
//    public boolean onInterceptTouchEvent(CoordinatorLayout parent, V child, MotionEvent event) {
//        if (!mAllowUserDragging) {
//            return false;
//        }
//        return super.onInterceptTouchEvent(parent, child, event);
//    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, V child, MotionEvent event) {
        return false;
    }


}