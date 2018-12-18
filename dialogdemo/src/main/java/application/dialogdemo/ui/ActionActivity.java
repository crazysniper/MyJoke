package application.dialogdemo.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import application.dialogdemo.R;
import application.dialogdemo.utils.DialogConstant;

@Route(path = DialogConstant.ActionActivity)
public class ActionActivity extends BaseActivity implements View.OnClickListener {

    private Button openSetting, openWifiSetting, ACTION_VIEW, CALL_PHONE, ACTION_DIAL, market,
            openMM, checkApp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_action;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        openSetting = findView(R.id.openSetting);
        openWifiSetting = findView(R.id.openWifiSetting);
        ACTION_VIEW = findView(R.id.ACTION_VIEW);
        CALL_PHONE = findView(R.id.CALL_PHONE);
        ACTION_DIAL = findView(R.id.ACTION_DIAL);
        market = findView(R.id.market);
        openMM = findView(R.id.openMM);
        checkApp = findView(R.id.checkApp);


        openSetting.setOnClickListener(this);
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
}
