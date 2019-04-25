package application.basicdemo.ui;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.basicdemo.R;
import application.basicdemo.R2;
import application.basicdemo.util.BasicUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = BasicUtil.BasicMainActivity)
public class BasicMainActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_basic_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.spannable})
    public void onClick(View view) {
        if (view.getId() == R.id.spannable) {
            ARouter.getInstance().build(BasicUtil.SpannableActivity).navigation();
        }
    }
}
