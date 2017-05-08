package com.pengzhangdemo.com.myapplication.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by zp on 4/8/17.
 *
 * 获取 到对应的文字字体
 */

public class TextTypeUtils {

    public static Typeface fontFace;

    /**
     * 个人界面的 等级 字体
     * @return
     */
    public static Typeface getLevelTypeFace(Context context){

        try{
            fontFace = Typeface.createFromAsset(context.getApplicationContext().getResources().getAssets(), "fonts/scroll.ttf");
        }catch (RuntimeException e){
            e.printStackTrace();
        }

        return fontFace;
    }


}
