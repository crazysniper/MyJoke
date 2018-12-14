package application.eventdemo.util;

import android.view.MotionEvent;

import com.myjoke.baselibray.util.LogUtil;

/**
 * Created by Gao on 2018/12/14.
 */

public class EventUtil {

    static String TAG = "MotionEvent";

    public static void showEvent(MotionEvent event, String tag) {
        if (event != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    LogUtil.e(TAG, tag + "    按下");
                    break;
                case MotionEvent.ACTION_MOVE:
                    LogUtil.e(TAG, tag + "    移动");
                    break;
                case MotionEvent.ACTION_UP:
                    LogUtil.e(TAG, tag + "    抬起");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    LogUtil.e(TAG, tag + "    取消");
                    break;
            }
        } else {
            LogUtil.e(TAG, tag + "    点击");
        }

    }
}
