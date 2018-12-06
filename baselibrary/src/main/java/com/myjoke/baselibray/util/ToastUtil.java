package com.myjoke.baselibray.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Gao on 2018/11/28.
 */

public class ToastUtil {

    private Toast toast = null;
    private static ToastUtil toastUtil = null;

    private ToastUtil() {

    }

    public static ToastUtil getInstance() {
        if (toastUtil == null) {
            synchronized (ToastUtil.class) {
                if (toastUtil == null) {
                    toastUtil = new ToastUtil();
                }
            }
        }
        return toastUtil;
    }

    public void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

}
