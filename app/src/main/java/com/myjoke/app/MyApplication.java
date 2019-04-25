package com.myjoke.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.euler.andfix.patch.PatchManager;
import com.facebook.stetho.Stetho;
import com.myjoke.baselibray.base.LibraryApplication;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.SpUtil;
import com.myjoke.baselibray.util.ToastUtil;
import com.myjoke.bean.PackageInfoBean;
import com.myjoke.callback.ForegroundCallbacks;
import com.myjoke.util.PackageUtil;
import com.squareup.leakcanary.LeakCanary;

import application.webviewdemo.util.X5Util;

/**
 * Created by Gao on 2018/10/5.
 */

public class MyApplication extends Application {

    public static PatchManager patchManager = null;

    private static final String[] MODULESLIST = {"com.myjoke.baselibray.base.LibraryApplication"};

    @Override
    public void onCreate() {
        super.onCreate();

        ToastUtil.getInstance().setAppContext(this);
        X5Util.initX5(this);

        Stetho.initializeWithDefaults(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        LogUtil.e("BaseActivity", "MyApplication onCreate=" + this);

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        SpUtil.init(getApplicationContext());

//        ExceptionCrashHandler.getInstance().init(this);

        initPatch();
        try {
            modulesApplicationInit();
        } catch (Exception e) {
            LogUtil.e("BaseActivity", "Exception  " + e.getMessage());
            e.printStackTrace();
        }

//        registerActivityLifecycleCallbacks(new ForegroundCallbacks());
    }

    private void modulesApplicationInit() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (String className : MODULESLIST) {
            Class clazz = Class.forName(className);
            Object object = clazz.newInstance();
            if (object instanceof LibraryApplication) {
                LibraryApplication app = ((LibraryApplication) object).getModuleApp();
                LogUtil.e("BaseActivity", "1111 app=" + app);
                app.init(this);
            }
        }
    }

    private void initPatch() {
        PackageInfoBean bean = PackageUtil.getPackageInfo(this);

        patchManager = new PatchManager(this);
        // 每次appVersion变更都会导致所有补丁被删除,如果appversion没有改变，则会加载已经保存的所有补丁。
        patchManager.init(bean.getVersionName());//current version
        patchManager.loadPatch();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
