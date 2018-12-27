package application.common.util;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gao on 2018/11/30.
 */

public class OkHttpUtil {

    public void get(String url, Map<String, Object> params) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            response.body().toString();
        } else {

        }
    }

}
