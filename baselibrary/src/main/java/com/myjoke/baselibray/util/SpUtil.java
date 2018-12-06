package com.myjoke.baselibray.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by Gao on 2018/10/18.
 */

public class SpUtil {

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static final String name = "myjoke";


    public static void init(Context context) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private static void getSp() {
        if (sp == null || editor == null) {
            throw new IllegalArgumentException("先初始化SP");
        }
    }

    public static <T> void put(String key, T t) {
        getSp();
        if (t instanceof Integer) {
            editor.putInt(key, (Integer) t);
        } else if (t instanceof String) {
            editor.putString(key, (String) t);
        } else if (t instanceof Long) {
            editor.putLong(key, (Long) t);
        } else if (t instanceof Float) {
            editor.putFloat(key, (Float) t);
        } else if (t instanceof Boolean) {
            editor.putBoolean(key, (Boolean) t);
        } else if (t instanceof Set) {
            editor.putStringSet(key, (Set<String>) t);
        } else {
            throw new IllegalArgumentException("value格式异常");
        }
        editor.commit();
    }

    public static <T> SpUtil putBatch(String key, T t) {
        getSp();
        if (t instanceof Integer) {
            editor.putInt(key, (Integer) t);
        } else if (t instanceof String) {
            editor.putString(key, (String) t);
        } else if (t instanceof Long) {
            editor.putLong(key, (Long) t);
        } else if (t instanceof Float) {
            editor.putFloat(key, (Float) t);
        } else if (t instanceof Boolean) {
            editor.putBoolean(key, (Boolean) t);
        } else if (t instanceof Set) {
            editor.putStringSet(key, (Set<String>) t);
        } else {
            throw new IllegalArgumentException("value格式异常");
        }
        return new SpUtil();
    }

    public static void commit() {
        editor.commit();
    }

    public static void clear() {
        editor.clear();
    }

    public static <T> T get(String key, T t) {
        getSp();
        if (t instanceof Integer) {
            Integer value = sp.getInt(key, (Integer) t);
            t = (T) value;
        } else if (t instanceof String) {
            String value = sp.getString(key, (String) t);
            t = (T) value;
        } else if (t instanceof Long) {
            Long value = sp.getLong(key, (Long) t);
            t = (T) value;
        } else if (t instanceof Float) {
            Float value = sp.getFloat(key, (Float) t);
            t = (T) value;
        } else if (t instanceof Boolean) {
            Boolean value = sp.getBoolean(key, (Boolean) t);
            t = (T) value;
        } else if (t instanceof Set) {
            Set value = sp.getStringSet(key, (Set) t);
            t = (T) value;
        } else {
            throw new IllegalArgumentException("value格式异常");
        }
        return t;
    }
}
