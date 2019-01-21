package com.myjoke.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.util.ConstantPath;

import butterknife.ButterKnife;

@Route(path = ConstantPath.FlexLayoutActivity)
public class FlexLayoutActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_flex_layout;
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
