package com.myjoke.baselibray.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gao on 2019/1/25.
 */

public class TimeUtil {
    private static final long MINUTE = 60 * 1000;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;
    private static final long MONTH = 30 * DAY;
    private static final long YEAR = 12 * MONTH;

    public static String setTimeDesc(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
        long timeStamp = date1.getTime();
        long timeGap = System.currentTimeMillis() - timeStamp;
        LogUtil.e("timeGap=" + timeGap);
        String timeStr = null;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年前";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月前";
        } else if (timeGap > DAY) {
            timeStr = timeGap / DAY + "天前";
        } else if (timeGap > HOUR) {
            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {
            timeStr = timeGap / MINUTE + "分钟前";
        } else {
            timeStr = "刚刚";
        }
        return timeStr;
    }

}
