package application.systeminfo.ui;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import application.systeminfo.R;
import application.systeminfo.util.SystemInfoConstant;

@Route(path = SystemInfoConstant.PackageInfoActivity)
public class PackageInfoActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_package_info;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {
        PackageManager packageManager = getPackageManager();

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);

            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            LogUtil.e("versionCode=" + versionCode + "      versionName=" + versionName);

            getActivityInfo(packageManager);
            LogUtil.e("____________________________");

            getPermissionInfo(packageManager);

            LogUtil.e("____________________________");
            getUsersPermissionInfo(packageManager);

        } catch (PackageManager.NameNotFoundException e) {

        }
    }

    private void getActivityInfo(PackageManager packageManager) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);

        ActivityInfo[] activities = packageInfo.activities;
        if (activities != null) {
            for (ActivityInfo activityInfo : activities) {
                String toString = activityInfo.toString();
                int launchMode = activityInfo.launchMode;
                String packageName = activityInfo.packageName;
                String processName = activityInfo.processName;
                LogUtil.e("toString=" + toString + "    launchMode=" + launchMode + "   packageName=" + packageName + "     processName=" + processName);
            }
        } else {
            LogUtil.e("没有Activity");
        }
    }

    private void getPermissionInfo(PackageManager packageManager) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
        // 获取的是<permission/>标签里面的值，而不是<uses-permission/>里面的

        PermissionInfo[] permissionInfos = packageInfo.permissions;
        if (permissionInfos != null) {
            for (PermissionInfo permissionInfo : permissionInfos) {
                int protectionLevel = permissionInfo.protectionLevel;
                String packageName = permissionInfo.packageName;
                String toString = permissionInfo.toString();
                String group = permissionInfo.group;
                String name = permissionInfo.name; // 返回<permission/>标签里面的值
                LogUtil.e("protectionLevel=" + protectionLevel + "    packageName=" + packageName + "   toString=" + toString
                        + "     group=" + group + "     name=" + name);

            }
        } else {
            LogUtil.e("没有Permission");
        }
    }

    private void getUsersPermissionInfo(PackageManager packageManager) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
        // 获取的是<permission/>标签里面的值，而不是<uses-permission/>里面的

        String[] permissionInfos = packageInfo.requestedPermissions; // 不能有没有授权，都能获取到
        if (permissionInfos != null) {
            for (String permissionInfo : permissionInfos) {
                LogUtil.e("UsersPermissionInfo=" + permissionInfo);
            }
        } else {
            LogUtil.e("没有Permission");
        }
    }


}
