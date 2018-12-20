package application.okhttpdemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.okhttpdemo.R;
import application.okhttpdemo.util.OkHttpConstant;

@Route(path = OkHttpConstant.OkHttpMainActivity)
public class OkHttpMainActivity extends BaseActivity implements View.OnClickListener {

    private Button button;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ok_http_main;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        button = findView(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            ARouter.getInstance().build(OkHttpConstant.OkHttpTestActivity).navigation();
        }
    }
}
