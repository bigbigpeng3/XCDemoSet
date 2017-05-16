package com.pengzhangdemo.com.myapplication.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.app.BaseApplication;


/**
 * Created by zp on 4/5/17.
 *
 * 用做所有的Tag颜色选择器
 */

public class TagColorUtils {


    public static Drawable switchColor(int index){
        return  switchColor(index,false);
    }


    public static Drawable switchColor(int index,boolean alpha) {

        Drawable drawable;
        if (index % 8 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.drawable.shape_home_tag_bg8);
        } else if (index % 7 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.drawable.shape_home_tag_bg7);
        } else if (index % 6 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.drawable.shape_home_tag_bg6);
        } else if (index % 5 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.drawable.shape_home_tag_bg5);
        } else if (index % 4 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.drawable.shape_home_tag_bg4);
        } else if (index % 3 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.drawable.shape_home_tag_bg3);
        } else if (index % 2 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.drawable.shape_home_tag_bg2);
        } else {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.drawable.shape_home_tag_bg1);
        }

        if (alpha){// 针对准备直播界面中的tag alpha
            drawable.setAlpha(128);
        }

        return drawable;
    }

    public static Drawable switchTextColor(int index) {

        Drawable drawable;
        if (index % 8 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.color.tag_color_8);
        } else if (index % 7 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.color.tag_color_7);
        } else if (index % 6 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.color.tag_color_6);
        } else if (index % 5 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.color.tag_color_5);
        } else if (index % 4 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.color.tag_color_4);
        } else if (index % 3 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.color.tag_color_3);
        } else if (index % 2 == 0) {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.color.tag_color_2);
        } else {
            drawable = BaseApplication.mAppContext.getResources().getDrawable(R.color.tag_color_1);
        }

        return drawable;
    }

    public static int switchTextColorStr(int index) {

        int drawable;
        if (index % 8 == 0) {
            drawable = Color.parseColor("#3bdd6d");
        } else if (index % 7 == 0) {
            drawable = Color.parseColor("#00d3c4");
        } else if (index % 6 == 0) {
            drawable = Color.parseColor("#0099fb");
        } else if (index % 5 == 0) {
            drawable = Color.parseColor("#9669f4");
        } else if (index % 4 == 0) {
            drawable = Color.parseColor("#f264c0");
        } else if (index % 3 == 0) {
            drawable = Color.parseColor("#ff6d5f");
        } else if (index % 2 == 0) {
            drawable = Color.parseColor("#ff9439");
        } else {
            drawable = Color.parseColor("#ffdd3b");
        }

        return drawable;
    }

}
