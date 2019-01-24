package application.okhttpdemo.util;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.myjoke.baselibray.util.LogUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import application.okhttpdemo.bean.ResultBean;
import application.okhttpdemo.callback.ResultCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gao on 2019/1/23.
 */

public class HttpUtil {

    private static OkHttpClient client = null;
    private static HttpUtil httpUtil = null;
    private Handler handler = null;

    private HttpUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
        ;
        client = builder.build();

        handler = new Handler(Looper.getMainLooper());
    }


    public static HttpUtil getInstance() {
        synchronized (HttpUtil.class) {
            if (httpUtil == null) {
                synchronized (HttpUtil.class) {
                    if (httpUtil == null) {
                        httpUtil = new HttpUtil();
                    }
                }
            }
        }
        return httpUtil;
    }

    public void doGet(String host, String path, Map<String, String> headers,
                      Map<String, String> queries, final ResultCallBack callBack) {
        StringBuilder url = new StringBuilder();
        url.append(host);

        if (!TextUtils.isEmpty(path)) {
            url.append(path);
        }

        if (queries != null) {
            StringBuilder sbQuery = new StringBuilder();

            for (Map.Entry<String, String> query : queries.entrySet()) {
                if (sbQuery.length() > 0) {
                    sbQuery.append("&");
                }
                if (!TextUtils.isEmpty(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!TextUtils.isEmpty(query.getValue())) {
                        sbQuery.append("=").append(query.getValue());
                    }
                }
            }
            if (sbQuery.length() > 0) {
                url.append("?").append(sbQuery);
            }
        }
        LogUtil.e("url==" + url.toString());

        Request.Builder builder = new Request.Builder().url(url.toString());
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                builder.addHeader(header.getKey(), header.getValue());
                LogUtil.e("key=" + header.getKey() + "   value=" + header.getValue());
            }
        }

        Request request = builder.build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getFailure(call, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getSuccess(call, response, callBack);
            }
        });
    }

    private void getFailure(final Call call, final IOException e, final ResultCallBack callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.error(e);
                }
            }
        });
    }

    private void getSuccess(final Call call, final Response response, final ResultCallBack callBack) {
        if (response.isSuccessful()) {
            final ResultBean bean = new ResultBean();
            bean.setCode(response.code());
            bean.setMessage(response.message());
            try {
                bean.setBody(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            bean.setContentLength(response.body().contentLength());
            bean.setByteStream(response.body().byteStream());
            try {
                bean.setBytes(response.body().bytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (callBack != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.success(bean);
                    }
                });
            }
        }
    }
}