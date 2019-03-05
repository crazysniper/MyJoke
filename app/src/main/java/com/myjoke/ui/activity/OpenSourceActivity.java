package com.myjoke.ui.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.util.ConstantPath;

import application.eventbusdemo.util.EventBusUtil;
import application.okhttpdemo.util.OkHttpConstant;
import application.permissiondemo.util.PermissionConstant;
import application.webviewdemo.util.WebViewConstant;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ConstantPath.OpenSourceActivity)
public class OpenSourceActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_open_source;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.permission, R.id.okhttp, R.id.webview, R.id.eventbus})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.permission:
                ARouter.getInstance().build(PermissionConstant.PermissionMainActivity).navigation();
                break;
            case R.id.okhttp:
                ARouter.getInstance().build(OkHttpConstant.OkHttpMainActivity).navigation();
                break;
            case R.id.webview:
                ARouter.getInstance().build(WebViewConstant.WebViewMainActivity).navigation();
                break;
            case R.id.eventbus:
                ARouter.getInstance().build(EventBusUtil.EventBusMainActivity).navigation();
                break;
        }
    }
}
