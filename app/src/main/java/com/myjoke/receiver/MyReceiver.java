package com.myjoke.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.myjoke.baselibray.util.LogUtil;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
        LogUtil.e("MyReceiver.toString==" + this.toString());
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String key = intent.getStringExtra("key");
        LogUtil.e("MyReceiver onReceive.toString==" + context.toString());
//        Toast.makeText(context, key, Toast.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(context, null, Toast.LENGTH_LONG);
        toast.setText(key);
        toast.show();

    }
}
