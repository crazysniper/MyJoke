package com.myjoke.baselibray.exception;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.myjoke.baselibray.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Gao on 2018/10/5.
 */


public class ExceptionCrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String CRASH_FILE_NAME = "CRASH_FILE_NAME";
    public static final String SHARED_CRASH = "crash";
    private static ExceptionCrashHandler mInstance;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    public static ExceptionCrashHandler getInstance() {
        if (mInstance == null) {

            synchronized (ExceptionCrashHandler.class) {
                if (mInstance == null) {
                    mInstance = new ExceptionCrashHandler();
                }
            }
        }
        return mInstance;
    }

    private Context context;

    public void init(Context context) {
        this.context = context;
        Thread.currentThread().setUncaughtExceptionHandler(this);
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LogUtil.e(e.getMessage());
        LogUtil.e(e.toString());
        LogUtil.e(e.getLocalizedMessage());

        String crashFileName = saveInfoToSD(e);
        cacheCrashFile(crashFileName);

        // 让系统默认处理，如果注释下面这段语句的话，那么系统报异常的时候，就暂时不会直接跳出app。
        mDefaultExceptionHandler.uncaughtException(t, e);
    }

    public void cacheCrashFile(String fileName) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_CRASH, Context.MODE_PRIVATE);
        sp.edit().putString(CRASH_FILE_NAME, fileName).commit();
    }

    public File getCrashFile() {
        String crashFileName = context.getSharedPreferences(SHARED_CRASH, Context.MODE_PRIVATE).getString(CRASH_FILE_NAME, "");
        return new File(crashFileName);
    }


    private String saveInfoToSD(Throwable throwable) {
        String fileName = null;
        StringBuffer sb = new StringBuffer();

        for (Map.Entry<String, String> entry : obtainSimpleInfo(context).entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append(" =  ").append(value).append("\n");
        }
        sb.append(obtainExceptionInfo(throwable));

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//            File dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "crash" + File.separator);
            File dir = new File(context.getCacheDir() + File.separator + "crash" + File.separator);

            if (dir.exists()) {
                deleteDir(dir);
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fileName = dir.toString() + File.separator + getAssignTime("yyyy_MM_dd HH:mm") + ".txt";
            try {

                FileOutputStream outputStream = new FileOutputStream(fileName);

                outputStream.write(sb.toString().getBytes());

                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {

            }
        }
        return fileName;
    }


    private String getAssignTime(String dateFormatStr) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        long currentTime = System.currentTimeMillis();
        return dateFormat.format(currentTime);
    }

    private HashMap<String, String> obtainSimpleInfo(Context context) {
        HashMap<String, String> map = new HashMap<>();

        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);

            map.put("versionName", info.versionName);
            map.put("versionCode", "" + info.versionCode);
            map.put("MODEL", Build.MODEL);
            map.put("MODEL", Build.MODEL);
            map.put("SDK_INT", "" + Build.VERSION.SDK_INT);
            map.put("PRODUCT", Build.PRODUCT);
            map.put("MOBILE_INFO", getMobileInfo());
        } catch (PackageManager.NameNotFoundException exception) {
        }
        return map;
    }

    public static String getMobileInfo() {
        StringBuffer sb = new StringBuffer();
        Field[] fields = Build.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                String value = null;
                value = field.get(null).toString();
                sb.append(name + "=" + value).append("\n");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String obtainExceptionInfo(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                boolean success = deleteDir(new File(dir, file.getName()));
                if (!success) {
                    return false;
                }
            }
        }
        return true;
    }
}