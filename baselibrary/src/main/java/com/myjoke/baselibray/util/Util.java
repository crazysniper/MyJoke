package com.myjoke.baselibray.util;

import android.content.Context;

import application.baselibrary.R;


/**
 * Created by Gao on 2018/11/29.
 */

public class Util {

    public static String getData(Context context, String tag) {
        String packageName = context.getPackageName();

        int duration = context.getResources().getInteger(R.integer.slide_animation_duration);

        LogUtil.e("BaseActivity", "tag==" + tag + "    duration==" + duration + "  packageName=" + packageName);
        return packageName;
    }
}
