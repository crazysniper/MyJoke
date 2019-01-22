package application.webviewdemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.webviewdemo.R2;
import application.webviewdemo.R;
import application.webviewdemo.util.WebViewConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@Route(path = WebViewConstant.WebViewMainActivity)
public class WebViewMainActivity extends BaseActivity {

    @BindView(R2.id.x5)
    Button x5;

    Unbinder unbinder = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view_main;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.x5, R2.id.x52})
    public void onClick(View view) {
        if (view.getId() == R.id.x5) {
            ARouter.getInstance().build(WebViewConstant.MainActivity).navigation();
        } else if (view.getId() == R.id.x52) {
            ARouter.getInstance().build(WebViewConstant.X5Activity).navigation();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbinder = null;
    }
}
