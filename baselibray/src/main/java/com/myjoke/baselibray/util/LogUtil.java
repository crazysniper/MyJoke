package com.myjoke.baselibray.util;

import android.util.Log;

/**
 * Created by Gao on 2018/10/5.
 */

public class LogUtil {

    public static final String TAG = "LogUtil";

    public static final int logLevel = 2;
    public static final int DEBUG = 0;

    public static final int ERROR = 5;

    public static void d(String msg) {
        if (logLevel < DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (logLevel < ERROR) {
            Log.e(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (logLevel < DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (logLevel < ERROR) {
            Log.e(tag, msg);
        }
    }

}
