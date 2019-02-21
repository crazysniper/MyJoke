package com.myjoke.baselibray.json;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

public class JsonUtil<T> {

    private static volatile JsonUtil jsonUtil = null;
    private static Gson gson = null;

    private JsonUtil() {

    }

    public static JsonUtil getInstance() {
        if (jsonUtil == null) {
            synchronized (JsonUtil.class) {
                if (jsonUtil == null) {
                    jsonUtil = new JsonUtil();
                    gson = new Gson();
                }
            }
        }
        return jsonUtil;
    }


    public String toJson(T t) {
        return gson.toJson(t);
    }


    public T fromJson(String json) {
        return gson.fromJson(json, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

}
