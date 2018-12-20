package application.permissiondemo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.permissiondemo.R;
import application.permissiondemo.util.PermissionConstant;

@Route(path = PermissionConstant.ThridPermissionActivity)
public class ThridPermissionActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_thrid_permission;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {

    }
}
