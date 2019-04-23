package application.fourmodule.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.myjoke.baselibray.util.LogUtil;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("MyService    onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.e("MyService    onBind");
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.e("MyService    onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.e("MyService    onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        LogUtil.e("MyService    onDestroy");
        super.onDestroy();
    }

    class MyBinder extends Binder {

    }

}
