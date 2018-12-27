package application.systeminfo.ui;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import application.systeminfo.R;
import application.systeminfo.util.SystemInfoConstant;

import static application.systeminfo.R.id.activityInfo;

@Route(path = SystemInfoConstant.ApplicationInfoActivity)
public class ApplicationInfoActivity  extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_application_info;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {
        try {
            PackageManager packageManager = getPackageManager();

            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA);
            if (applicationInfo != null) {
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    String value = bundle.getString("key");
                    LogUtil.e("meta  value=" + value);
                }


            }
        } catch (PackageManager.NameNotFoundException e) {

        }
    }
}