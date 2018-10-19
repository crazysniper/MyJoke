package com.myjoke.app;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;
import com.myjoke.baselibray.util.SpUtil;
import com.myjoke.bean.PackageInfoBean;
import com.myjoke.util.PackageUtil;

/**
 * Created by Gao on 2018/10/5.
 */

public class MyApplication extends Application {

    public static PatchManager patchManager = null;

    @Override
    public void onCreate() {
        super.onCreate();

        SpUtil.init(getApplicationContext());

//        ExceptionCrashHandler.getInstance().init(this);

        PackageInfoBean bean = PackageUtil.getPackageInfo(this);


        patchManager = new PatchManager(this);
        // 每次appVersion变更都会导致所有补丁被删除,如果appversion没有改变，则会加载已经保存的所有补丁。
        patchManager.init(bean.getVersionName());//current version

        patchManager.loadPatch();
    }

}
