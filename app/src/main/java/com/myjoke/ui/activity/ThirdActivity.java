package com.myjoke.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.util.ConstantPath;

@Route(path = ConstantPath.ThirdActivity)
public class ThirdActivity extends BaseActivity implements View.OnClickListener {

    Button toSecond, toSecond2;


    @Override
    public int getLayoutId() {
        return R.layout.activity_third;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        toSecond = findView(R.id.toSecond);
        toSecond2 = findView(R.id.toSecond2);

        toSecond.setOnClickListener(this);
        toSecond2.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e("BaseActivity", clazzName + " requestCode=" + requestCode + "  resultCode=" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toSecond:
                ARouter.getInstance().build(ConstantPath.BitmapActivity).withString("key1", "value1")
                        .withInt("intKey", 1).navigation(this, 100);
                break;
            case R.id.toSecond2:
                ARouter.getInstance().build(ConstantPath.ThirdActivity).withString("key1", "value1")
                        .withInt("intKey", 1).navigation(this);
                break;
        }
    }
}
