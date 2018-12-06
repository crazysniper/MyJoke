package com.myjoke.baselibray.util;

import android.view.View;

/**
 * Created by Gao on 2018/11/28.
 */

public class MeasureSpecUtil {

    public static String getModeName(int mode) {
        return mode == View.MeasureSpec.EXACTLY ? "EXACTLY" :
                (mode == View.MeasureSpec.AT_MOST ? "AT_MOST" : "UNSPECIFIED");
    }
}
