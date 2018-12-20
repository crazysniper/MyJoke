package com.myjoke.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.util.ConstantPath;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ConstantPath.ArouteActivity)
public class ArouteActivity extends BaseActivity {

    @BindView(R.id.textView4)
    TextView tv;

    public String key1;
    public int intValue;

    @Override
    public int getLayoutId() {
        return R.layout.activity_aroute;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        key1 = getIntent().getStringExtra("key1");
        intValue = getIntent().getIntExtra("intKey", 0);

        LogUtil.e("key1=" + key1 + "   intvalue=" + intValue);
    }

    @Override
    public void initData() {

        tv.setText("key1=" + key1 + "   intvalue=" + intValue);
    }

    @OnClick({R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("resultKey", "resultValue");
        setResult(200, intent);
        super.onBackPressed();
    }
}
