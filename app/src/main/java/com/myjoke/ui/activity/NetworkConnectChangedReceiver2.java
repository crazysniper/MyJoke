package com.myjoke.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.myjoke.baselibray.net.NetworkChangeEvent;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.NetUtils;

import org.greenrobot.eventbus.EventBus;

public class NetworkConnectChangedReceiver2 extends BroadcastReceiver {
    public NetworkConnectChangedReceiver2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //**判断当前的网络连接状态是否可用*/
        LogUtil.e("NetworkConnectChangedReceiver2 value1==" + intent.getStringExtra("key1"));
        boolean isConnected = NetUtils.isConnected(context);
        LogUtil.e("NetworkConnectChangedReceiver2 onReceive: 当前网络 " + isConnected);
        EventBus.getDefault().post(new NetworkChangeEvent(isConnected));
    }
}
