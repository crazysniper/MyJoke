package com.myjoke.baselibray.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.myjoke.baselibray.NetworkConnectChangedReceiver;
import com.myjoke.baselibray.net.NetworkChangeEvent;
import com.myjoke.baselibray.util.DoubleClickHelper;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.viewserver.ViewServer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import application.baselibrary.R;

/**
 * Created by Gao on 2018/10/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public String clazzName = "";
    private static final String TAG = "LogUtil: BaseActivity";
    private NetworkConnectChangedReceiver receiver = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        clazzName = getClass().getSimpleName();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " onCreate " + "  taskId=" + getTaskId());
        }

        EventBus.getDefault().register(this);
        ViewServer.get(this).addWindow(this);
        initView();
        initData();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    protected <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " onStart " + "  taskId=" + getTaskId());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " onResume " + "  taskId=" + getTaskId());
        }
        ViewServer.get(this).setFocusedWindow(this);
        if (receiver == null) {
            receiver = new NetworkConnectChangedReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " onPause " + "  taskId=" + getTaskId());
        }
        unregisterReceiver(receiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " onStop " + "  taskId=" + getTaskId());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " onNewIntent " + "  taskId=" + getTaskId());
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " onRestart " + "  taskId=" + getTaskId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " onDestroy " + "  taskId=" + getTaskId());
        }
        ViewServer.get(this).removeWindow(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e(TAG, clazzName + " requestCode=" + requestCode + "  resultCode=" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void finish() {
        super.finish();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " finish " + "  taskId=" + getTaskId());
        }
        if (!"SplashActivity".equals(getClass().getSimpleName())) {
            overridePendingTransitionExit();
        }
    }

    @Override
    public boolean isFinishing() {
        boolean result = super.isFinishing();
        if (isShowCycler()) {
            LogUtil.e(TAG, clazzName + " isFinishing result=" + result + "  taskId=" + getTaskId());
        }
        return result;
    }

    @Override
    public boolean isDestroyed() {
        boolean result = super.isDestroyed();
        if (isShowCycler()) {
            LogUtil.e("BaseActivity", clazzName + " isDestroyed result=" + result + "  taskId=" + getTaskId());
        }
        return result;
    }

//    @Override
//    public void startActivity(Intent intent) {
//        LogUtil.e(TAG, "Activity startActivity=" + clazzName);
//        if (isCheckActivityJump() && DoubleClickHelper.isOnDoubleClick(700)) {
//            LogUtil.e(TAG, "startActivity=" + clazzName);
//            return;
//        }
//        super.startActivity(intent);
//        overridePendingTransitionEnter();
//    }

    // 查看源码得知 startActivity 最终也会调用 startActivityForResult
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (isShowCycler()) {
            LogUtil.e(TAG, "Activity startActivityForResult=" + clazzName);
        }
        if (isCheckActivityJump() && DoubleClickHelper.isOnDoubleClick(700)) {
            LogUtil.e(TAG, "startActivityForResult 2次点击间隔太短，不跳转" + clazzName);
            return;
        }
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransitionEnter();
    }

    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkChangeEvent(NetworkChangeEvent event) {
        hasNetWork(event.isConnected);
    }

    protected boolean mCheckNetWork = false; //默认检查网络状态

    private void hasNetWork(boolean has) {
        if (isCheckNetWork()) {
            if (has) {
                Toast.makeText(this, "网络打开", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "网络关闭", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isCheckNetWork() {
        return mCheckNetWork;
    }

    /**
     * 是否检查 Activity 跳转频率，避免重复跳转
     */
    protected boolean isCheckActivityJump() {
        // 默认需要检查和判断
        return true;
    }

    protected boolean isShowCycler() {
        return false;
    }
}
