package application.glidedemo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.glidedemo.R;
import application.glidedemo.util.GlideUtil;
import butterknife.ButterKnife;

@Route(path = GlideUtil.GlideActivity)
public class GlideActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {

    }
}
