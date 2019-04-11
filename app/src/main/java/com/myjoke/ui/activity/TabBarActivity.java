package com.myjoke.ui.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;
import com.myjoke.util.ConstantPath;

import application.baseview.bar.OnTitleBarListener;
import application.baseview.bar.TitleBar;
import application.baseview.bar.style.TitleBarLightStyle;
import butterknife.ButterKnife;

@Route(path = ConstantPath.TabBarActivity)
public class TabBarActivity extends BaseActivity {
    private TitleBar mTitleBar;

    @Override
    public int getLayoutId() {
        // 在这里可以初始化样式
        TitleBar.initStyle(new TitleBarLightStyle(this));
        return R.layout.activity_tab_bar;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        mTitleBar = (TitleBar) findViewById(R.id.tb_main_title_bar);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                ToastUtil.getInstance().showToast("左项View被点击");
            }

            @Override
            public void onTitleClick(View v) {
                ToastUtil.getInstance().showToast("中间View被点击");
            }

            @Override
            public void onRightClick(View v) {
                ToastUtil.getInstance().showToast("右项View被点击");
            }
        });
    }

}
