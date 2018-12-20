package com.myjoke.ui.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.app.MyApplication;
import com.myjoke.baselibray.NetworkConnectChangedReceiver;
import com.myjoke.baselibray.base.BaseFragment;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.SpUtil;
import com.myjoke.baselibray.util.Util;
import com.myjoke.util.ConstantPath;

import java.io.File;
import java.io.IOException;

import application.dialogdemo.utils.DialogConstant;
import application.eventdemo.util.EventConstant;
import application.materialdemo.util.MaterialConstant;
import application.okhttpdemo.util.OkHttpConstant;
import application.permissiondemo.util.PermissionConstant;
import application.recyclerviewdemo.util.RecyclerViewConstant;
import application.scrollerdemo.util.ScrollerConstant;
import application.supportdesignview.util.SupportDesignConstant;
import application.systeminfo.util.SystemInfoConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.ed_test)
    EditText ed_test;

    private int heightPixels = 0;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, view);
//        SpUtil.clear();
        Util.getData(activity, "app");

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


        File patchDir2 = new File(activity.getFilesDir(), "apatch");
        LogUtil.e("加载补丁之前的");
        if (patchDir2.isDirectory()) {
            File[] files = patchDir2.listFiles();
            for (File file : files) {
                LogUtil.e("fileName=" + file.getName() + "   =" + file.getAbsolutePath());
            }
        }

        File apatch = new File(activity.getExternalFilesDir(null), "fix.apatch");
        if (apatch.exists()) {
            LogUtil.e("有要修复的补丁");
            try {
                MyApplication.patchManager.addPatch(apatch.getAbsolutePath());
                Toast.makeText(activity, "修复成功", Toast.LENGTH_SHORT).show();

                File patchDir = new File(activity.getFilesDir(), "apatch");
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
                Toast.makeText(activity, "修复失败", Toast.LENGTH_SHORT).show();
            }
//            apatch.delete();
        }

        heightPixels = getResources().getDisplayMetrics().heightPixels;


        int pid = android.os.Process.myPid(); // 获取该进程的id
        int tid = android.os.Process.myTid(); // 获取调用进程的id
        int uid = android.os.Process.myUid(); // 获取该进程的用户id
//        android.os.Process.killProcess(pid); // 删掉指定pid的进程


        long id = Thread.currentThread().getId();

        int taskId = activity.getTaskId(); // 这个是Activity中的方法。

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.e("Fragment requestCode=" + requestCode + "   resultCode=" + resultCode + "   data=" + data.getExtras().toString());
        if (requestCode == 100 && resultCode == 200) {
            Toast.makeText(activity, "Fragment result==" + data.getStringExtra("resultKey"), Toast.LENGTH_SHORT).show();
            ed_test.setText(data.getStringExtra("resultKey"));
            ed_test.setSelection(ed_test.getText().toString().length());
        }
    }

    @OnClick({R.id.btn1, R.id.view, R.id.fragmentAroute, R.id.recyclerModule, R.id.supportDesignModule, R.id.sysModule,
            R.id.dialogModule, R.id.scrollerModule, R.id.eventModule, R.id.material, R.id.permission,R.id.okhttp})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                ARouter.getInstance().build(ConstantPath.BitmapActivity).withString("key1", "value1")
                        .withInt("intKey", 1).navigation(activity, 100);
                break;
            case R.id.view:
//                ARouter.getInstance().build(ConstantPath.MyActivity).navigation(this);
                LogUtil.e("111111111111111");
                Intent intent1 = new Intent();
                intent1.setAction("hhhh2");
                intent1.setComponent(new ComponentName(activity.getPackageName(), NetworkConnectChangedReceiver.class.getName()));
                intent1.putExtra("key1", "value1");
                activity.sendBroadcast(intent1);
                break;
            case R.id.fragmentAroute:
//                ARouter.getInstance().build(ConstantPath.ArouteActivity).withString("key1", "value1")
//                        .withInt("intKey", 1).navigation();

                Postcard postcard = ARouter.getInstance().build(ConstantPath.ArouteActivity);
                LogisticsCenter.completion(postcard);
                Class<?> destination = postcard.getDestination();

                Intent intent = new Intent(activity, destination);
                intent.putExtra("key1", "value1").putExtra("intKey", 1);
                startActivityForResult(intent, 100);
                break;
            case R.id.recyclerModule:
//                startActivity(new Intent(MainActivity.this, RecyclerMainActivity.class));
                ARouter.getInstance().build(RecyclerViewConstant.RecyclerMainActivity).navigation();
                break;
            case R.id.supportDesignModule:
                ARouter.getInstance().build(SupportDesignConstant.SupportDesignActivity).navigation();
                break;
            case R.id.sysModule:
                ARouter.getInstance().build(SystemInfoConstant.SystemInfoActivity).navigation();
                break;
            case R.id.dialogModule:
                ARouter.getInstance().build(DialogConstant.DialogMainActivity).navigation();
                break;
            case R.id.scrollerModule:
                ARouter.getInstance().build(ScrollerConstant.ScrollerMainActivity).navigation();
                break;
            case R.id.eventModule:
                Toast.makeText(activity, " 事件分发页面", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build(EventConstant.EventMainActivity).navigation();
                break;
            case R.id.material:
                ARouter.getInstance().build(MaterialConstant.MaterialMainActivity).navigation();
                break;
            case R.id.permission:
                ARouter.getInstance().build(PermissionConstant.PermissionMainActivity).navigation();
                break;
            case R.id.okhttp:
                ARouter.getInstance().build(OkHttpConstant.OkHttpMainActivity).navigation();
                break;
        }
    }

}
