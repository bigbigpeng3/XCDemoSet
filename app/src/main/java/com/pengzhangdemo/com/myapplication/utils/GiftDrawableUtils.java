package com.pengzhangdemo.com.myapplication.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.pengzhangdemo.com.myapplication.R;

/**
 * Created by zp on 5/6/17.
 */

public class GiftDrawableUtils {

    public static Drawable getGiftDrawale(Context context, char strNum) {

        Drawable drawable;

        int num = Character.getNumericValue(strNum);

        if (num == 9) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_9);
        } else if (num == 8) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_8);
        } else if (num == 7) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_7);
        } else if (num == 6) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_6);
        } else if (num == 5) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_5);
        } else if (num == 4) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_4);
        } else if (num == 3) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_3);
        } else if (num == 2) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_2);
        } else if (num == 1) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_1);
        } else if (num == 0) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_0);
        } else {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_0);
        }

        return drawable;

    }

    public static Drawable getGiftDrawale(Context context, int strNum) {

        Drawable drawable;

        int num = strNum;

        if (num == 9) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_9);
        } else if (num == 8) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_8);
        } else if (num == 7) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_7);
        } else if (num == 6) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_6);
        } else if (num == 5) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_5);
        } else if (num == 4) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_4);
        } else if (num == 3) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_3);
        } else if (num == 2) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_2);
        } else if (num == 1) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_1);
        } else if (num == 0) {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_0);
        } else {
            drawable = context.getResources().getDrawable(R.drawable.gno_ya_0);
        }

        return drawable;

    }

}
