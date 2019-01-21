package com.myjoke.ui.activity;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ScreenUtil;
import com.myjoke.util.ConstantPath;

import application.baseview.layout.MyViewGroup;
import application.baseview.view.MyView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ConstantPath.MyActivity)
public class MyActivity extends BaseActivity {

    @BindView(R.id.myView)
    MyView myView;

    @BindView(R.id.myViewGroup)
    MyViewGroup myViewGroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        LogUtil.e("屏幕宽度==" + ScreenUtil.getScreenWidth(this));
        LogUtil.e("屏幕高度==" + ScreenUtil.getScreenHeight(this));
        LogUtil.e("屏幕密度比例==" + ScreenUtil.getDensity(this));
    }

    @OnClick({R.id.myView, R.id.myViewGroup, R.id.flexLayout})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.myView:
                Toast.makeText(MyActivity.this, "MyView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myViewGroup:
                Toast.makeText(MyActivity.this, "MyViewGroup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.flexLayout:
                ARouter.getInstance().build(ConstantPath.FlexLayoutActivity).navigation();
                break;
        }
    }


}
