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

@Route(path = SystemInfoConstant.ApplicationInfoActivity)
public class ApplicationInfoActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_application_info;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        ApplicationInfo applicationInfo = getApplicationInfo();
        LogUtil.e("applicationInfo.name==" + applicationInfo.name);
        LogUtil.e("applicationInfo.packageName==" + applicationInfo.packageName);// com.myjoke
        LogUtil.e("applicationInfo.dataDir==" + applicationInfo.dataDir); // /data/user/0/com.myjoke
        LogUtil.e("applicationInfo.minSdkVersion==" + applicationInfo.minSdkVersion);// 23
        LogUtil.e("applicationInfo.targetSdkVersion==" + applicationInfo.targetSdkVersion); // 26
    }

    @Override
    public void initData() {
        try {
            PackageManager packageManager = getPackageManager();

            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
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