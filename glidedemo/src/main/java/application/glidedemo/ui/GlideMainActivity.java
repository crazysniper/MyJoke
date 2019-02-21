package application.glidedemo.ui;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.glidedemo.R2;
import application.glidedemo.R;
import application.glidedemo.util.GlideUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = GlideUtil.GlideMainActivity)
public class GlideMainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_glide_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
//        Glide.with(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.glide})
    public void onClick(View view) {
        if (view.getId() == R.id.glide) {
            ARouter.getInstance().build(GlideUtil.GlideActivity).navigation();
        }
    }
}
