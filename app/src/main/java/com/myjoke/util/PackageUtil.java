package com.myjoke.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.bean.PackageInfoBean;

/**
 * Created by Gao on 2018/10/5.
 */

public class PackageUtil {

    public static PackageInfoBean getPackageInfo(Context context) {

        PackageManager manager = context.getPackageManager();
        PackageInfoBean bean = new PackageInfoBean();

        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);

            String versionName = info.versionName;
            int versionCode = info.versionCode;

            bean.setVersionCode(versionCode);
            bean.setVersionName(versionName);
            bean.setPackageName(context.getPackageName());

            LogUtil.e("bean=" + bean);
        } catch (PackageManager.NameNotFoundException exception) {

        }
        return bean;
    }


}
