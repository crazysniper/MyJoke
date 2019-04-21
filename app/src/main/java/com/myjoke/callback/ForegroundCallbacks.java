package com.myjoke.callback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.myjoke.baselibray.util.LogUtil;

public class ForegroundCallbacks implements Application.ActivityLifecycleCallbacks {
    
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtil.e("onActivityCreated    "+activity.getLocalClassName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtil.e("onActivityStarted    "+activity.getLocalClassName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtil.e("onActivityResumed    "+activity.getLocalClassName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtil.e("onActivityPaused    "+activity.getLocalClassName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtil.e("onActivityStopped    "+activity.getLocalClassName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtil.e("onActivitySaveInstanceState    "+activity.getLocalClassName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtil.e("onActivityDestroyed    "+activity.getLocalClassName());
    }
}
