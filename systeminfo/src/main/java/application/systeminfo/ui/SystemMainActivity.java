package application.systeminfo.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import java.util.Collections;
import java.util.List;

import application.systeminfo.R;
import application.systeminfo.R2;
import application.systeminfo.util.SystemInfoConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = SystemInfoConstant.SystemMainActivity)
public class SystemMainActivity extends BaseActivity {
    @BindView(R2.id.runtime)
    TextView runtime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        long freeMemory = Runtime.getRuntime().freeMemory(); // 返回的单位是字节
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        int largeMemoryClass = activityManager.getLargeMemoryClass();

        runtime.setText("freeMemory=" + 1.00 * freeMemory / 1024 / 1024 + "M\n"
                + "totalMemory=" + 1.00 * totalMemory / 1024 / 1024 + "M\n"
                + "maxMemory=" + 1.00 * maxMemory / 1024 / 1024 + "M\n"
                + "memoryClass=" + memoryClass + "M\n"
                + "largeMemoryClass=" + largeMemoryClass + "M\n");


        PackageManager packageManager = getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);

        LogUtil.e("resolveInfoList.size=" + resolveInfoList.size());


        Collections.sort(resolveInfoList, new ResolveInfo.DisplayNameComparator(packageManager));

        for (ResolveInfo resolveInfo : resolveInfoList) {
            String appName = resolveInfo.loadLabel(packageManager).toString();// 获取应用名称
            String packageName = resolveInfo.activityInfo.packageName;// 包名
            String className = resolveInfo.activityInfo.name;// 入口类名
            LogUtil.e("程序名：" + appName + " 包名:" + packageName + " 入口类名：" + className);
        }
    }

    @OnClick({R2.id.textView, R2.id.packageInfo, R2.id.applicationInfo, R2.id.activityInfo, R2.id.serviceInfo, R2.id.resolveInfo})
    public void onClick(View v) {

        if (v.getId() == R.id.textView) {
            ARouter.getInstance().build(SystemInfoConstant.SystemInfoActivity).navigation();
        } else if (v.getId() == R.id.packageInfo) {
            ARouter.getInstance().build(SystemInfoConstant.PackageInfoActivity).navigation();
        } else if (v.getId() == R.id.applicationInfo) {
            ARouter.getInstance().build(SystemInfoConstant.ApplicationInfoActivity).navigation();
        } else if (v.getId() == R.id.activityInfo) {
            ARouter.getInstance().build(SystemInfoConstant.ActivityInfoActivity).navigation();
        } else if (v.getId() == R.id.serviceInfo) {
            ARouter.getInstance().build(SystemInfoConstant.ServiceInfoActivity).navigation();
        } else if (v.getId() == R.id.resolveInfo) {
            ARouter.getInstance().build(SystemInfoConstant.ResolveInfoActivity).navigation();
        } else if (v.getId() == R.id.deviceInfo) {
            ARouter.getInstance().build(SystemInfoConstant.DeviceDetailActivity).navigation();
        }
    }
}
