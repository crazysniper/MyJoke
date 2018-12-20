package application.permissiondemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.permissiondemo.R;
import application.permissiondemo.util.PermissionConstant;

@Route(path = PermissionConstant.PermissionMainActivity)
public class PermissionMainActivity extends BaseActivity implements View.OnClickListener {

    private Button button, button2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_permission_main;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        button = (Button) findView(R.id.button);
        button2 = (Button) findView(R.id.button2);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            ARouter.getInstance().build(PermissionConstant.YuanShengPermissionActivity).navigation();
        } else if (v.getId() == R.id.button2) {
            ARouter.getInstance().build(PermissionConstant.ThridPermissionActivity).navigation();
        }
    }
}
