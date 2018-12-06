package application.supportdesignview.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.supportdesignview.R;
import application.supportdesignview.util.SupportDesignConstant;

@Route(path = SupportDesignConstant.ConstraintLayoutActivity)
public class ConstraintLayoutActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_constraint_layout;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {

    }
}
