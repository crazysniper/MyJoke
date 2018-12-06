package application.systeminfo.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.myjoke.baselibray.util.LogUtil;

import java.lang.reflect.Method;

import application.systeminfo.bean.AppInfo;
import application.systeminfo.bean.PhoneInfo;

/**
 * Created by Gao on 2018/12/6.
 */

public class TelephoneUtil {
    public static PhoneInfo getPhoneInfo(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String deviceBrand = Build.BRAND;
        String deviceModel = Build.MODEL;

        int systemVersionCode = Build.VERSION.SDK_INT;
        String systemVersion = Build.VERSION.RELEASE;

        PhoneInfo phoneInfo = new PhoneInfo(deviceBrand, deviceModel, systemVersionCode, systemVersion);

        String subscriberId = manager.getSubscriberId();
        String deviceId = manager.getDeviceId(); // 这个已经废弃了。
        String num = manager.getLine1Number();

        phoneInfo.setSubscriberId(subscriberId);
        phoneInfo.setDeviceId(deviceId);

        LogUtil.e("手机号码 num=" + num);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            String imei = manager.getImei();
            String meid = manager.getMeid();

            LogUtil.e("imei=" + imei + "   meid=" + meid);
            phoneInfo.setImei(imei);
            phoneInfo.setMeid(meid);


            LogUtil.e("getIMEI=" + getIMEI(context, 0) + "   getIMEI=" + getIMEI(context, 1));
        }
        phoneInfo.setAppInfo(getAppInfo(context));
        return phoneInfo;
    }

    public static String getIMEI(Context context, int slotId) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Method method = manager.getClass().getMethod("getImei", int.class);
            String imei = (String) method.invoke(manager, slotId);
            return imei;
        } catch (Exception e) {
            return "";
        }
    }


    public static AppInfo getAppInfo(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo packageInfo = null;
        String packageName = context.getPackageName();
        try {
            packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        AppInfo appInfo = new AppInfo();
        appInfo.setPackageName(packageName);
        if (packageInfo != null) {
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;

            appInfo.setVersionCode(versionCode);
            appInfo.setVersionName(versionName);
        }
        return appInfo;
    }
}
