package com.myjoke.baselibray.util;

/**
 * Created by Gao on 2018/12/19.
 */

/**
 * https://www.jianshu.com/p/579f1f118161
 */
public class DoubleClickHelper {

    static long lastTime = -1;

    public static boolean isOnDoubleClick() {
        // 默认间隔时长
        return isOnDoubleClick(700);
    }

    public static boolean isOnDoubleClick(int time) {
        long nowTime = System.currentTimeMillis();
        LogUtil.e("Activity startActivityForResult  isOnDoubleClick nowTime=" + nowTime + "   lastTime=" + lastTime + "   aa=" + (nowTime - lastTime));
        if (nowTime - lastTime > time) {
            lastTime = nowTime;
            return false;
        }
        return true;
    }
}
