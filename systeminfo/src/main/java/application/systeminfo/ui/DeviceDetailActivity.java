package application.systeminfo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.myjoke.baselibray.base.BaseActivity;

import application.systeminfo.R;
import application.systeminfo.util.SystemInfoConstant;

@Route(path = SystemInfoConstant.DeviceDetailActivity)
public class DeviceDetailActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_device_detail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
