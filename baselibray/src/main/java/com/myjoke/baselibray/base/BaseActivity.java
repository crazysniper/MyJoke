package com.myjoke.baselibray.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myjoke.baselibray.R;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.viewserver.ViewServer;

/**
 * Created by Gao on 2018/10/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public String clazzName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        clazzName = getClass().getSimpleName();
        LogUtil.e("BaseActivity", clazzName + " onCreate " + "  taskId=" + getTaskId());

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
        LogUtil.e("BaseActivity", clazzName + " onStart " + "  taskId=" + getTaskId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e("BaseActivity", clazzName + " onResume " + "  taskId=" + getTaskId());
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e("BaseActivity", clazzName + " onPause " + "  taskId=" + getTaskId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e("BaseActivity", clazzName + " onStop " + "  taskId=" + getTaskId());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.e("BaseActivity", clazzName + " onNewIntent " + "  taskId=" + getTaskId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.e("BaseActivity", clazzName + " onRestart " + "  taskId=" + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("BaseActivity", clazzName + " onDestroy " + "  taskId=" + getTaskId());
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e("BaseActivity", clazzName + " requestCode=" + requestCode + "  resultCode=" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void finish() {
        super.finish();
        LogUtil.e("BaseActivity", clazzName + " finish " + "  taskId=" + getTaskId());
        overridePendingTransitionExit();
    }

    @Override
    public boolean isFinishing() {
        LogUtil.e("BaseActivity", clazzName + " isFinishing " + "  taskId=" + getTaskId());
        return super.isFinishing();
    }

    @Override
    public boolean isDestroyed() {
        LogUtil.e("BaseActivity", clazzName + " isDestroyed " + "  taskId=" + getTaskId());
        return super.isDestroyed();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
