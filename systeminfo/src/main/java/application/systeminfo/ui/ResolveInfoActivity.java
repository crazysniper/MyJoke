package application.systeminfo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.systeminfo.R;
import application.systeminfo.util.SystemInfoConstant;

@Route(path = SystemInfoConstant.ResolveInfoActivity)
public class ResolveInfoActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_resolve_info;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {

    }
}
