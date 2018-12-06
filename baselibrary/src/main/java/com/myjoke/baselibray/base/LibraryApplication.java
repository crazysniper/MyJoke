package com.myjoke.baselibray.base;

import android.app.Application;

import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.Util;

/**
 * Created by Gao on 2018/11/29.
 */

public class LibraryApplication extends Application implements IComponentApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("BaseActivity", "LibraryApplication   onCreate");
    }

    public LibraryApplication getModuleApp() {
        return this;
    }

    @Override
    public void init(Application application) {
        LogUtil.e("BaseActivity", "LibraryApplication   =" + Util.getData(application, "module"));
    }
}
