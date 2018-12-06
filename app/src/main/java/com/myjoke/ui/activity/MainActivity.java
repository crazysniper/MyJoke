package com.myjoke.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.app.MyApplication;
import com.myjoke.baselibray.NetworkConnectChangedReceiver;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.SpUtil;
import com.myjoke.baselibray.util.ToastUtil;
import com.myjoke.baselibray.util.Util;
import com.myjoke.util.ConstantPath;

import java.io.File;
import java.io.IOException;

import application.recyclerviewdemo.util.RecyclerViewConstant;
import application.supportdesignview.util.SupportDesignConstant;
import application.systeminfo.util.SystemInfoConstant;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ConstantPath.MainActivity)
public class MainActivity extends BaseActivity {

    private int heightPixels = 0;
    private Button btn;
    CardView cardView;
    private long currentTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    NetworkConnectChangedReceiver receiver;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        SpUtil.clear();
        Util.getData(this, "app");

        SpUtil.putBatch("key1", "value1").putBatch("key2", "value2").putBatch("key3", "value3");

        String value1 = SpUtil.get("key1", "");
        String value2 = SpUtil.get("key2", "");
        String value3 = SpUtil.get("key3", "");
        LogUtil.e("value1=" + value1 + "    value2=" + value2 + "   value3=" + value3);

//        LogUtil.e("getFilesDir().getAbsolutePath()=" + getFilesDir().getAbsolutePath()); //     /data/user/0/com.myjoke/files
//        LogUtil.e("getFilesDir().getPath()=" + getFilesDir().getPath()); //     /data/user/0/com.myjoke/files
//        String crashFileName = getSharedPreferences(ExceptionCrashHandler.SHARED_CRASH, Context.MODE_PRIVATE).getString(ExceptionCrashHandler.CRASH_FILE_NAME, "");
//
//        LogUtil.e("crashFileName==" + crashFileName);
//
//        LogUtil.e("getExternalFilesDir().getAbsolutePath()=" + getExternalFilesDir(null).getPath()); //  /storage/emulated/0/Android/data/com.myjoke/files
//        LogUtil.e("getExternalCacheDir().getAbsolutePath()=" + getExternalCacheDir().getAbsolutePath());  //    /storage/emulated/0/Android/data/com.myjoke/cache
//        LogUtil.e("getExternalCacheDir().getPath()=" + getExternalCacheDir().getPath());    //  /storage/emulated/0/Android/data/com.myjoke/cache
//
//
//        LogUtil.e("getCacheDir().getAbsolutePath()=" + getCacheDir().getAbsolutePath()); //  /data/user/0/com.myjoke/cache
//        LogUtil.e("getCacheDir().getPath()=" + getCacheDir().getPath()); //     /data/user/0/com.myjoke/cache
//        LogUtil.e("______________________________");
//
//        LogUtil.e("getPackageResourcePath()=" + getPackageResourcePath()); //   /data/app/com.myjoke-fXntPN77Zv98_7LKRyQEQQ==/base.apk
//        LogUtil.e("getPackageCodePath()=" + getPackageCodePath());      // /data/app/com.myjoke-fXntPN77Zv98_7LKRyQEQQ==/base.apk
//
//        LogUtil.e("______________________________");
//
//        LogUtil.e("Environment.getExternalStorageDirectory().getAbsolutePath()=" + Environment.getExternalStorageDirectory().getAbsolutePath()); //     /storage/emulated/0
//        LogUtil.e("Environment.getExternalStorageDirectory().getPath()=" + Environment.getExternalStorageDirectory().getPath());//     /storage/emulated/0
//
//        LogUtil.e("Environment.getDataDirectory().getPath()=" + Environment.getDataDirectory().getPath());//    /data
//        LogUtil.e("Environment.getDownloadCacheDirectory().getPath()=" + Environment.getDownloadCacheDirectory().getPath()); // /data/cache
//        LogUtil.e("Environment.getRootDirectory().getPath()=" + Environment.getRootDirectory().getPath());  //      /system

//        File file = ExceptionCrashHandler.getInstance().getCrashFile();
//        StringBuffer sb = new StringBuffer();
//
//        try {
//            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
//            char[] buffer = new char[1024];
//            int len = -1;
//
//            while ((len = inputStreamReader.read(buffer)) != -1) {
//                sb.append(buffer, 0, len);
//            }
//            inputStreamReader.close();
////            LogUtil.e(sb.toString());
//        } catch (Exception e) {
//            LogUtil.e("Exception==" + e.getMessage());
//        }
//
//        LogUtil.e("this.toString==" + this.toString());


        File patchDir2 = new File(getFilesDir(), "apatch");
        LogUtil.e("加载补丁之前的");
        if (patchDir2.isDirectory()) {
            File[] files = patchDir2.listFiles();
            for (File file : files) {
                LogUtil.e("fileName=" + file.getName() + "   =" + file.getAbsolutePath());
            }
        }

        File apatch = new File(getExternalFilesDir(null), "fix.apatch");
        if (apatch.exists()) {
            LogUtil.e("有要修复的补丁");
            try {
                MyApplication.patchManager.addPatch(apatch.getAbsolutePath());
                Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();

                File patchDir = new File(getFilesDir(), "apatch");
                if (patchDir.isDirectory()) {
                    File[] files = patchDir.listFiles();
                    for (File file : files) {
                        LogUtil.e("fileName=" + file.getName() + "   =" + file.getAbsolutePath());
                    }
                }

                //加载完补丁后删除补丁，否则有新补丁将不会生效哦
//                MyApplication.patchManager.removeAllPatch();
                File[] files = patchDir.listFiles();
                LogUtil.e("删除补丁之后的个数==" + files.length);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "修复失败", Toast.LENGTH_SHORT).show();
            }
//            apatch.delete();
        }


        btn = findView(R.id.btn1);

        heightPixels = getResources().getDisplayMetrics().heightPixels;


        int pid = android.os.Process.myPid(); // 获取该进程的id
        int tid = android.os.Process.myTid(); // 获取调用进程的id
        int uid = android.os.Process.myUid(); // 获取该进程的用户id
//        android.os.Process.killProcess(pid); // 删掉指定pid的进程


        long id = Thread.currentThread().getId();

        int taskId = getTaskId(); // 这个是Activity中的方法。

        LogUtil.e("pid==" + pid);
        LogUtil.e("tid==" + tid);
        LogUtil.e("uid==" + uid);

        LogUtil.e("id==" + id);
        LogUtil.e("taskId==" + taskId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtil.e("id2==" + Thread.currentThread().getId());
//                Looper.prepare();
//                Toast.makeText(MainActivity.this, "id2==" + Thread.currentThread().getId(), Toast.LENGTH_SHORT).show();
//                Looper.prepare();
            }
        }).start();

//        receiver=  new NetworkConnectChangedReceiver();
////
//        IntentFilter intent = new IntentFilter();
//        intent.addAction("hhhh");
//        registerReceiver(receiver,intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e("BaseActivity", clazzName + " requestCode=" + requestCode + "  resultCode=" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            LogUtil.e("result==" + data.getStringExtra("result"));
        }
    }

    @OnClick({R.id.view, R.id.recyclerModule, R.id.supportDesignModule, R.id.sysModule})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.view:
//                ARouter.getInstance().build(ConstantPath.MyActivity).navigation(this);
                LogUtil.e("111111111111111");
                Intent intent1 = new Intent();
                intent1.setAction("hhhh2");
                intent1.setComponent(new ComponentName(getPackageName(), NetworkConnectChangedReceiver.class.getName()));
                intent1.putExtra("key1", "value1");
                sendBroadcast(intent1);
                break;
            case R.id.recyclerModule:
//                startActivity(new Intent(MainActivity.this, RecyclerMainActivity.class));
                ARouter.getInstance().build(RecyclerViewConstant.RecyclerMainActivity).navigation(this);
                break;
            case R.id.supportDesignModule:
                ARouter.getInstance().build(SupportDesignConstant.SupportDesignActivity).navigation(this);
                break;
            case R.id.sysModule:
                ARouter.getInstance().build(SystemInfoConstant.SystemInfoActivity).navigation();
                break;
        }
    }


    public void showToast(View v) {
        ARouter.getInstance().build(ConstantPath.BitmapActivity).withString("key1", "value1")
                .withInt("intKey", 1).navigation(this, 100);
//        Intent intent = new Intent(this, MyReceiver.class);
//        intent.putExtra("key", "value");
//        sendBroadcast(intent);
//        Toast.makeText(MainActivity.this, "2/1=" + 2 / 1, Toast.LENGTH_SHORT).show();

//        startActivity(new Intent(MainActivity.this,BitmapActivity.class));
//
//
//        AnimatorSet set = new AnimatorSet();
//        //包含平移、缩放和透明度动画
//        set.playTogether(
//                ObjectAnimator.ofFloat(btn, "translationX", 0, 100),
//                ObjectAnimator.ofFloat(btn, "translationY", 0, 1000),
//                ObjectAnimator.ofFloat(btn, "scaleX", 1f, 0f),
//                ObjectAnimator.ofFloat(btn, "scaleY", 1f, 0f),
//                ObjectAnimator.ofFloat(btn, "alpha", 1f, 0f));
//        set.setTarget(btn);
//
//        set.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                btn.setScaleX(1.0f);
//                btn.setScaleY(1.0f);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        set.setDuration(5000);
//        set.start();

//        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
//
//        animator.setStartDelay(1000);
//        animator.setDuration(2000);
//
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                int top = (int) (heightPixels * value);
//
//                btn.layout(btn.getLeft(), top, btn.getRight(), top + btn.getHeight());
//
////                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btn.getLayoutParams();
////                int topMargin = (int) (heightPixels * value);
////                if (topMargin < heightPixels - btn.getHeight()) {
////                    layoutParams.topMargin = topMargin;
////
////                    btn.setLayoutParams(layoutParams);
////                }
////                LogUtil.e("value==" + value + "   topMargin==" + topMargin + "   height==" + btn.getHeight());
//            }
//        });
//
//        animator.removeAllListeners();
//        animator.setTarget(btn);
//        animator.start();

//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn, "translationY", 0f, 1000f);
//        objectAnimator.setDuration(2000);
//        objectAnimator.setStartDelay(1000);
//        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                float fraction = animation.getAnimatedFraction();
//                LogUtil.e("value==" + value + "     fraction==" + fraction);
//            }
//        });
//        objectAnimator.start();

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentTime > 1000) {
            ToastUtil.getInstance().showToast(this, "连续点2次退出");
            currentTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }

    }
}
