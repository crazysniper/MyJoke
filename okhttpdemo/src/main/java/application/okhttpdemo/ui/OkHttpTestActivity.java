package application.okhttpdemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.okhttpdemo.R;
import application.okhttpdemo.util.OkHttpConstant;

@Route(path = OkHttpConstant.OkHttpTestActivity)
public class OkHttpTestActivity extends BaseActivity implements View.OnClickListener {

    private Button get, post;

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
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.get) {

        } else if (v.getId() == R.id.post) {

        }
    }
}
