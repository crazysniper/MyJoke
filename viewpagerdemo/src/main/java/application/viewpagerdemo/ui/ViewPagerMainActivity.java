package application.viewpagerdemo.ui;

import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.viewpagerdemo.R;
import application.viewpagerdemo.util.ViewPagerConstant;

@Route(path = ViewPagerConstant.ViewPagerMainActivity)
public class ViewPagerMainActivity extends BaseActivity {

    private Button btn1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_pager_main;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        btn1 = findView(R.id.btn1);
    }

    @Override
    public void initData() {

    }
}
