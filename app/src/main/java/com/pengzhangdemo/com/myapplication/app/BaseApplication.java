package com.pengzhangdemo.com.myapplication.app;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Description : baseApplication for other class to get the App Context and initView config
 * Email  : bigbigpeng3@gmail.com
 * Author : peng zhang
 * Date   : 2017-3-29
 */
public class BaseApplication extends Application {

    public static Context mAppContext;
    private static BaseApplication baseApplication;

    public static int screenWidth;
    public static int screenHeight;

    public static BaseApplication getBaseApplication() {
        if (baseApplication == null) {
            baseApplication = new BaseApplication();
        }
        return baseApplication;
    }

//    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        baseApplication = this;

        initConfig();
    }

    /**
     * 完成所有的初始化
     */
    private void initConfig() {


    }


    /**
     * 获取屏幕的宽度（单位：px）
     *
     * @return 屏幕宽px
     */
    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) BaseApplication.mAppContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return 屏幕高px
     */
    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) BaseApplication.mAppContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.heightPixels;
    }

}
