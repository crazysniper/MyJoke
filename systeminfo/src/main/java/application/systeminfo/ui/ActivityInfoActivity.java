package application.systeminfo.ui;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import application.systeminfo.R;
import application.systeminfo.util.SystemInfoConstant;

@Route(path = SystemInfoConstant.ActivityInfoActivity)
public class ActivityInfoActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        LogUtil.e("getComponentName=" + getComponentName());
        LogUtil.e("getPackageName=" + getPackageName());
    }

    @Override
    public void initData() {

        try {
            PackageManager packageManager = getPackageManager();

            ActivityInfo activityInfo = packageManager.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            if (activityInfo != null) {
                Bundle bundle = activityInfo.metaData;
                if (bundle != null) {
                    String value = bundle.getString("key");
                    LogUtil.e("meta  value=" + value);
                }


            }
        } catch (PackageManager.NameNotFoundException e) {

        }
    }
}