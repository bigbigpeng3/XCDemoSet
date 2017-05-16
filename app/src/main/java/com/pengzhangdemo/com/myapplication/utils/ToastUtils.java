package com.pengzhangdemo.com.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Description :
 * Email  : bigbigpeng3@gmail.com
 * Author : peng zhang
 * Date   : 2016-10-21
 */

public class ToastUtils {

    private static Toast toast;

    public static void makeText(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}

