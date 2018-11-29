package com.myjoke.baselibray.util;

import android.content.Context;

/**
 * Created by Gao on 2018/11/28.
 */

public class ScreenUtil {

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static float getScaleDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int dip2px(Context context, float dp) {
        return (int) (dp * getDensity(context) + 0.5f);
    }

    public static int px2dip(Context context, float px) {
        return (int) (px / getDensity(context) + 0.5f);
    }

    public static int sp2px(Context context, float sp) {
        return (int) (sp * getScaleDensity(context) + 0.5f);
    }
}
