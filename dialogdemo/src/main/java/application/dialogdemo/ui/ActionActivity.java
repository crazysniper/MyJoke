package application.dialogdemo.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.OsUtil;
import com.myjoke.baselibray.util.ToastUtil;

import application.dialogdemo.R;
import application.dialogdemo.utils.DialogConstant;

@Route(path = DialogConstant.ActionActivity)
public class ActionActivity extends BaseActivity implements View.OnClickListener {

    private Button openSetting, openSelfSetting, openWifiSetting, ACTION_VIEW, CALL_PHONE, ACTION_DIAL, market,
            openMM, checkApp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_action;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        openSetting = findView(R.id.openSetting);
        openSelfSetting = findView(R.id.openSelfSetting);
        openWifiSetting = findView(R.id.openWifiSetting);
        ACTION_VIEW = findView(R.id.ACTION_VIEW);
        CALL_PHONE = findView(R.id.CALL_PHONE);
        ACTION_DIAL = findView(R.id.ACTION_DIAL);
        market = findView(R.id.market);
        openMM = findView(R.id.openMM);
        checkApp = findView(R.id.checkApp);


        openSetting.setOnClickListener(this);
        openSelfSetting.setOnClickListener(this);
        openWifiSetting.setOnClickListener(this);
        ACTION_VIEW.setOnClickListener(this);
        CALL_PHONE.setOnClickListener(this);
        ACTION_DIAL.setOnClickListener(this);
        market.setOnClickListener(this);
        openMM.setOnClickListener(this);
        checkApp.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.openSetting) { // 打开设置
            intent.setAction(Settings.ACTION_SETTINGS);
        } else if (v.getId() == R.id.openSelfSetting) { // 打开本应用基础信息设置
            getAppDetailSettingIntent(intent);
        } else if (v.getId() == R.id.openSelfPermissionSetting) { // 打开本应用权限设置
            String rom = OsUtil.getMiuiVersion();
            LogUtil.e("goMiaoMiMainager --- rom : " + rom);
            try { // MIUI 8及以上版本
                intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                intent.putExtra("extra_pkgname", getPackageName());
                LogUtil.e("aaaaaaaaaaa");
            } catch (Exception e) {
                try { // MIUI 5/6/7
                    intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                    intent.putExtra("extra_pkgname", getPackageName());
                    LogUtil.e("bbbbbbbb");
                } catch (Exception e1) { // 否则跳转到应用详情
                    LogUtil.e("cccccccccc");
                }
            }
        } else if (v.getId() == R.id.openWifiSetting) { // 打开Wifi设置
            intent.setAction(Settings.ACTION_WIFI_SETTINGS);
        } else if (v.getId() == R.id.ACTION_VIEW) { // 打开浏览器
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.baidu.com"));
        } else if (v.getId() == R.id.CALL_PHONE) { // 直接呼叫指定的电话号码(这个需要权限)
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:123456"));
        } else if (v.getId() == R.id.ACTION_DIAL) { // 调用拨号面板，这个不需要权限
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:123456"));
        } else if (v.getId() == R.id.market) { // 打开妈妈社区应用市场
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://detail?id=com.ci123.pregnancywap"));
        } else if (v.getId() == R.id.openMM) {
            intent = getPackageManager().getLaunchIntentForPackage("com.ci123.pregnancywap");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else if (v.getId() == R.id.checkApp) { // 检测app有没有安装
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo("com.ci123.pregnancywap", 0);

                ToastUtil.getInstance().showToast(ActionActivity.this, "存在code=" + packageInfo.versionCode + "  name=" + packageInfo.versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                ToastUtil.getInstance().showToast(ActionActivity.this, "不存在");
            }
            return;
        }
        startActivity(intent);
    }


    private Intent getAppDetailSettingIntent(Intent localIntent) {
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 要加上这一行
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }
}
