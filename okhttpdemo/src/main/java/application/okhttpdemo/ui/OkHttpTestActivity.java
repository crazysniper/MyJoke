package application.okhttpdemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import java.io.IOException;

import application.okhttpdemo.R;
import application.okhttpdemo.util.OkHttpConstant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Route(path = OkHttpConstant.OkHttpTestActivity)
public class OkHttpTestActivity extends BaseActivity implements View.OnClickListener {

    private Button get, post;

    // 1、创建OkHttpClient对象
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public int getLayoutId() {
        return R.layout.activity_ok_http_test;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        get = findView(R.id.get);
        post = findView(R.id.post);

        get.setOnClickListener(this);
        post.setOnClickListener(this);
        LogUtil.e("当前线程==" + Thread.currentThread().getId());
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.get) {
            try {
                get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (v.getId() == R.id.post) {
            post();
        }
    }

    public void get() throws IOException {
        // 2、构建Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://www.baidu.com").build();

        // 3、将Request封装成Call
        Call call = okHttpClient.newCall(request);

        // 4、执行Call
        // Response response =  call.execute(); // 同步

        call.enqueue(new Callback() { // 异步
            @Override
            public void onFailure(Call call, IOException e) { // 子线程
                LogUtil.e("onFailure 当前线程==" + Thread.currentThread().getId());
                LogUtil.e("onFailure    e==" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException { // 子线程
                LogUtil.e("onResponse 当前线程==" + Thread.currentThread().getId());//
                LogUtil.e("onResponse   response=" + response.toString()); // Response{protocol=http/1.1, code=200, message=OK, url=http://www.baidu.com/}
                LogUtil.e("onResponse   response.protocol=" + response.protocol()); // http/1.1
                LogUtil.e("onResponse   response.code=" + response.code()); // 200
                LogUtil.e("onResponse   response.message=" + response.message()); // OK
                LogUtil.e("onResponse   response.request=" + response.request()); // Request{method=GET, url=http://www.baidu.com/, tags={}}
                LogUtil.e("onResponse   response.request().url()=" + response.request().url()); // http://www.baidu.com/
                LogUtil.e("onResponse   response.request().method()=" + response.request().method()); // GET
                LogUtil.e("onResponse body=" + response.body().string());

            }
        });

    }

    public void post() {
        Request.Builder builder = new Request.Builder();

        RequestBody body = new FormBody.Builder().build();

        Request request = builder.url("").post(body).build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}

