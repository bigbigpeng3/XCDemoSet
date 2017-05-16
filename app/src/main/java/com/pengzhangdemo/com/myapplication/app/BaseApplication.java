package com.pengzhangdemo.com.myapplication.app;

import android.app.Application;
import android.content.Context;
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

}
